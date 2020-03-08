package co.grandcircus.EventsAPI.controllers;

import java.util.ArrayList;
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
import co.grandcircus.EventsAPI.Dao.EventsDao;
import co.grandcircus.EventsAPI.Entities.FavEvent;
import co.grandcircus.EventsAPI.Model.Embedded1;
import co.grandcircus.EventsAPI.Model.Event;
import co.grandcircus.EventsAPI.Model.Venue;

@Controller
public class EventsController {

	@Autowired
	private HttpSession sesh;

	@Autowired
	private ApiService apiServ;
	
	@Autowired 
	private EventsDao eDao;
	
	@RequestMapping("/")
	public ModelAndView index(@RequestParam(name = "message", required = false) String message) {

		if (message == null) {
			return new ModelAndView("index");

		}

		return new ModelAndView("index", "message" , message);
	}

	@RequestMapping("/search")
	public ModelAndView search(@RequestParam("zipCode") String zipCode) {

		Embedded1 embedded = new Embedded1();
		embedded = apiServ.getEvent(zipCode);


		if (embedded == null) {

			return new ModelAndView("redirect:/", "message", "That zip code did not return any events.");
		}

		List<Event> events = embedded.getEvents();

		
		sesh.setAttribute("zipCode", zipCode);


		return new ModelAndView("search", "events", events);

	}

	@PostMapping("/search")
	public ModelAndView search(@RequestParam(name = "venue", required = false) String venue,
			@RequestParam(name = "keyword", required = false) String keyword, 
			@SessionAttribute("zipCode") String zipCode,
			@RequestParam(name = "date", required = false) String startDate,
			@RequestParam(name = "endDate", required = false) String endDate,
			@RequestParam(name = "venuename", required = false) String venueName) {

		Embedded1 embedded = new Embedded1();

		
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
		} else if (venueName != null && !venueName.isEmpty()) {

			List<Venue> venues = apiServ.searchVenues(venueName);
			return new ModelAndView("venues", "venues", venues);
			
		} else{

			return new ModelAndView("redirect:/search", "zipCode", zipCode);
		}

	}
	
	@RequestMapping("/bucket-list")
	public ModelAndView seeBucketList() {
		
		ModelAndView mav = new ModelAndView("bucket-list");
		List<FavEvent> bucketList = eDao.findAll();
		mav.addObject("bucketList", bucketList);
		return mav;
	}
}
