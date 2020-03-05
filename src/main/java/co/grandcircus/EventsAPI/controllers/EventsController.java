package co.grandcircus.EventsAPI.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.EventsAPI.ApiService;
import co.grandcircus.EventsAPI.Model.Embedded1;
import co.grandcircus.EventsAPI.Model.Event;

@Controller
public class EventsController {

	@Autowired
	private ApiService apiServ;

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

		return new ModelAndView("search", "events", events);
	}

}
