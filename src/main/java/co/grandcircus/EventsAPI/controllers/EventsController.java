package co.grandcircus.EventsAPI.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.EventsAPI.ApiService;
import co.grandcircus.EventsAPI.Entities.ZipCode;
import co.grandcircus.EventsAPI.Model.Embedded1;
import co.grandcircus.EventsAPI.Model.Event;

@Controller
public class EventsController {
	
	@Autowired
	private HttpSession sesh;

	@Autowired
	private ApiService apiServ;

	@RequestMapping("/")
	public ModelAndView index(@RequestParam(name = "message", required = false) String message) {

		if (message == null) {
			return new ModelAndView("index");

		}

		return new ModelAndView("index", "message", message);
	}

	@RequestMapping("/search")
	public ModelAndView search(ZipCode zipCode) {

		Embedded1 embedded = new Embedded1();
		
		
		embedded = apiServ.getEvent(zipCode.getZipCode());

		if (embedded == null) {

			return new ModelAndView("redirect:/", "message", "That zip code did not return any events.");
		}

		List<Event> events = embedded.getEvents();
		ModelAndView mav = new ModelAndView();
		mav.addObject("events", events);
		
		sesh.setAttribute("zipCode", zipCode);

		return mav;
	}

	@PostMapping("/search")
	public ModelAndView search(@RequestParam(name = "venue", required = false) String venue,
			@RequestParam(name = "keyword", required = false) String keyword, @SessionAttribute("zipCode") ZipCode zipCode2,
			@RequestParam(name = "date", required = false) String startDate,
			@RequestParam(name = "endDate", required = false) String endDate) {

		Embedded1 embedded = new Embedded1();
		String zipCode = zipCode2.getZipCode();
		
		if (keyword != null && !keyword.isEmpty()) {

			embedded = apiServ.byKeyword(keyword, zipCode);
			List<Event> events = embedded.getEvents();

			return new ModelAndView("search", "events", events);
		} else if (venue != null && !venue.isEmpty()) {

			embedded = apiServ.byVenue(venue, zipCode);
			List<Event> events = embedded.getEvents();

			return new ModelAndView("search", "events", events);
		} else if ((startDate != null && !startDate.isEmpty()) && (endDate == null || endDate.isEmpty())) {

			embedded = apiServ.byDate(startDate, zipCode);
			List<Event> events = embedded.getEvents();

			return new ModelAndView("search", "events", events);
		} else if ((endDate != null && !endDate.isEmpty()) && (startDate == null || startDate.isEmpty())) {

			embedded = apiServ.byEndDate(endDate, zipCode);
			List<Event> events = embedded.getEvents();

			return new ModelAndView("search", "events", events);
		} else if ((endDate != null && !endDate.isEmpty()) && (startDate != null && !startDate.isEmpty())) {

			embedded = apiServ.byDates(startDate, endDate, zipCode);
			List<Event> events = embedded.getEvents();

			return new ModelAndView("search", "events", events);
		} else {

			return new ModelAndView("search");
		}

	}
}
