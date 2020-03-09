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

		if (message == null) { // ensures that if there isn't a message, there is no null pointer exception
			return new ModelAndView("index");

		}

		return new ModelAndView("index", "message", message); // sends a message if redirected from search
	}

	@RequestMapping("/search")
	public ModelAndView search(@RequestParam("zipCode") String zipCode,
			@RequestParam(name = "message", required = false) String message) {
		// I can't get this to work yet:
		// https://www.baeldung.com/spring-data-jpa-pagination-sorting
		// Pageable firstPageWithTwentyElements = PageRequest.of(0, 20);

		Embedded1 embedded = new Embedded1();
		embedded = apiServ.getEvent(zipCode); // gets events using the zipcode

		if (embedded == null) { // if the entered zip code returns no events, this redirects back to main page

			return new ModelAndView("redirect:/", "message", "That zip code did not return any events.");
		} else if (message != null) {
			ModelAndView mav = new ModelAndView("/search");
			mav.addObject("message", "That search not return any events.");
			mav.addObject("zipCode", zipCode);

			return mav;
		}

		List<Event> events = embedded.getEvents(); // creates a list of events

		sesh.setAttribute("zipCode", zipCode); // sets zip code in the session so that we don't need to send it
												// everywhere

		return new ModelAndView("search", "events", events);

	}

	@PostMapping("/search")
	public ModelAndView search(@RequestParam(name = "venue", required = false) String venue,
			@RequestParam(name = "keyword", required = false) String keyword,
			@SessionAttribute("zipCode") String zipCode,
			@RequestParam(name = "date", required = false) String startDate,
			@RequestParam(name = "endDate", required = false) String endDate,
			@RequestParam(name = "venuename", required = false) String venueName,
			@RequestParam(name = "genre", required = false) String genre,
			@RequestParam(name = "pageNum", required = false) String pageNum) {
// pulls in a lot of potential variables

		Embedded1 embedded = new Embedded1();

		if (keyword != null && !keyword.isEmpty()) { // used if there is a search keyword

			embedded = apiServ.byKeyword(keyword, zipCode);

			if (embedded == null) { // if the entered variable returns no events, this redirects back to the search
									// page
				ModelAndView mav = new ModelAndView("redirect:/search");
				mav.addObject("message", "That search not return any events.");
				mav.addObject("zipCode", zipCode);

				return mav;
			}

			List<Event> events = embedded.getEvents();

			return new ModelAndView("search", "events", events);

		} else if (venue != null && !venue.isEmpty()) { // used if there is a venue id

			embedded = apiServ.byVenue(venue, zipCode);

			if (embedded == null) { // if the entered variable returns no events, this redirects back to the search
									// page
				ModelAndView mav = new ModelAndView("redirect:/search");
				mav.addObject("message", "That search not return any events.");
				mav.addObject("zipCode", zipCode);

				return mav;
			}

			List<Event> events = embedded.getEvents();

			return new ModelAndView("search", "events", events);

			// used if there is an start date but no end date
		} else if ((startDate != null && !startDate.isEmpty()) && (endDate == null || endDate.isEmpty())) {

			embedded = apiServ.byDate(startDate, zipCode);

			if (embedded == null) { // if the entered variable returns no events, this redirects back to the search
									// page
				ModelAndView mav = new ModelAndView("redirect:/search");
				mav.addObject("message", "That search not return any events.");
				mav.addObject("zipCode", zipCode);

				return mav;
			}

			List<Event> events = embedded.getEvents();

			return new ModelAndView("search", "events", events);

			// used if there is an end date but no start date
		} else if ((endDate != null && !endDate.isEmpty()) && (startDate == null || startDate.isEmpty())) {

			embedded = apiServ.byEndDate(endDate, zipCode);

			if (embedded == null) { // if the entered variable returns no events, this redirects back to the search
									// page
				ModelAndView mav = new ModelAndView("redirect:/search");
				mav.addObject("message", "That search not return any events.");
				mav.addObject("zipCode", zipCode);

				return mav;
			}

			List<Event> events = embedded.getEvents();

			return new ModelAndView("search", "events", events);

			// used to search by start and end date
		} else if ((endDate != null && !endDate.isEmpty()) && (startDate != null && !startDate.isEmpty())) {

			embedded = apiServ.byDates(startDate, endDate, zipCode);

			if (embedded == null) { // if the entered variable returns no events, this redirects back to the search
									// page
				ModelAndView mav = new ModelAndView("redirect:/search");
				mav.addObject("message", "That search not return any events.");
				mav.addObject("zipCode", zipCode);

				return mav;
			}

			List<Event> events = embedded.getEvents();

			return new ModelAndView("search", "events", events);

			// used to get a list of venues if venue is searched by keyword not by id
		} else if (venueName != null && !venueName.isEmpty()) {

			List<Venue> venues = apiServ.searchVenues(venueName);

			if (venues == null) { // if the entered variable returns no events, this redirects back to the search
									// page
				ModelAndView mav = new ModelAndView("redirect:/search");
				mav.addObject("message", "That search not return any venues.");
				mav.addObject("zipCode", zipCode);

				return mav;
			}

			return new ModelAndView("venues", "venues", venues);

		} else if (genre != null && !genre.isEmpty()) {

			embedded = apiServ.byGenre(genre, zipCode);

			if (embedded == null) { // if the entered variable returns no events, this redirects back to the search
									// page
				ModelAndView mav = new ModelAndView("redirect:/search");
				mav.addObject("message", "That search not return any events.");
				mav.addObject("zipCode", zipCode);

				return mav;
			}

			List<Event> events = embedded.getEvents();
			return new ModelAndView("search", "events", events);

		} else if (pageNum != null && !pageNum.isEmpty()) { // used if there is a pagenumber

			if (Integer.parseInt(pageNum) < 1) {

				ModelAndView mav = new ModelAndView("redirect:/search");
				mav.addObject("message", "There were no more events.");
				mav.addObject("zipCode", zipCode);

				return mav;

			}

			embedded = apiServ.getEvent(zipCode, pageNum);

			if (embedded == null) { // if the entered variable returns no events, this redirects back to the search
									// page
				ModelAndView mav = new ModelAndView("redirect:/search");
				mav.addObject("message", "There were no more events.");
				mav.addObject("zipCode", zipCode);

				return mav;
			}

			List<Event> events = embedded.getEvents();

			ModelAndView mav = new ModelAndView("search");
			mav.addObject("events", events);
			mav.addObject("pageNum", pageNum);

			return mav;
		} else {

			return new ModelAndView("redirect:/search", "zipCode", zipCode);
		} // if we are sent to search without parameters to search by, returns a search by
			// zip code

	}

	@RequestMapping("/bucket-list")
	public ModelAndView seeBucketList() {

		ModelAndView mav = new ModelAndView("bucket-list");
		List<FavEvent> bucketList = eDao.findAll();
		mav.addObject("bucketList", bucketList);
		return mav;
	}

	@PostMapping("/bucket-list")
	public ModelAndView deleteFave(@RequestParam("id") String Id) {
		Long deleteId = eDao.findByEventId(Id).getId();
		eDao.deleteById(deleteId);
		return new ModelAndView("redirect:/bucket-list");
	}

}
