package co.grandcircus.EventsAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.EventsAPI.ApiService;
import co.grandcircus.EventsAPI.ApiService2;
import co.grandcircus.EventsAPI.Model.Event;
import co.grandcircus.EventsAPI.Model.DarkSky.Currently;


@Controller
public class EventsController2 {
	
	@Autowired
	private ApiService apiServ;
	@Autowired
	private ApiService2 apiServ2;
	
//	@RequestMapping("/")
//	public ModelAndView index() {
//		return new ModelAndView("index");
//	}
//	
//	@RequestMapping("/search")
//	public ModelAndView search() {
//		
//		Embedded1 embedded = new Embedded1();
//		embedded = apiServ.getEvent();
//		List<String> names = new ArrayList<>();
//		List<Event> events = embedded.getEvents();
//		
//		for (Event event : events) {
//			
//			names.add(event.getName());
//		}
//				
//		return new ModelAndView("search", "names", names);
//	}
	
	
	@RequestMapping("/event-details/{id}")
	public ModelAndView eventDetails(@PathVariable("id") String id) {
		ModelAndView mav = new ModelAndView("event-details");
		//First get event and event details
		Event event = apiServ2.getEventById(id);
	
		mav.addObject("event", event);
		//images are all the same so just grabbing first one
		mav.addObject("image", event.getImages().get(0));
		mav.addObject("date", event.getDates().getStart().getLocalDate());
		mav.addObject("link", event.get_links().getSelf().getHref());
		mav.addObject("attractions", event.get_embedded().getAttractions());
		mav.addObject("segment", event.getClassifications().get(0).getSegment());
		mav.addObject("genre", event.getClassifications().get(0).getGenre());
		
		//then grab weather, which requires grabbing the required deets from event
		String lat = event.get_embedded().getVenues().get(0).getLocation().getLatitude();
		String lon = event.get_embedded().getVenues().get(0).getLocation().getLongitude();
		String localDate = event.getDates().getStart().getLocalDate();
		String localTime = event.getDates().getStart().getLocalTime();
		Currently weather = apiServ2.getWeather(lat, lon, localDate, localTime);
		mav.addObject("weather", weather);
		return mav;
	}

	

}
