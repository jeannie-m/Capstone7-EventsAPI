package co.grandcircus.EventsAPI.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.EventsAPI.ApiService;
import co.grandcircus.EventsAPI.Model.Embedded1;
import co.grandcircus.EventsAPI.Model.Event;


@Controller
public class EventsController {
	
	@Autowired
	private ApiService apiServ;
	
	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	
	@RequestMapping("/search")
	public ModelAndView search() {
		
		Embedded1 embedded = new Embedded1();
		embedded = apiServ.getEvent();
		List<String> names = new ArrayList<>();
		List<Event> events = embedded.getEvents();
		
		for (Event event : events) {
			
			names.add(event.getName());
		}
				
		return new ModelAndView("search", "names", names);
	}
	
	

}
