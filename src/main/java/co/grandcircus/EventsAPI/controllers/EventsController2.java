package co.grandcircus.EventsAPI.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.EventsAPI.ApiService2;
import co.grandcircus.EventsAPI.Dao.EventsDao;
import co.grandcircus.EventsAPI.Entities.FavEvent;
import co.grandcircus.EventsAPI.Model.Event;
import co.grandcircus.EventsAPI.Model.DarkSky.Currently;

@Controller
public class EventsController2 {
	
	@Autowired
	private HttpSession sesh;

	@Autowired
	private ApiService2 apiServ2;

	@Autowired
	private EventsDao eventsDao;

	@RequestMapping("/event-details/{id}")
	public ModelAndView eventDetails(@PathVariable("id") String id) {
		ModelAndView mav = new ModelAndView("event-details");
		// First get event and event details
		Event event = apiServ2.getEventById(id);

		List<FavEvent> fEvents = eventsDao.findAll();

		for (FavEvent fEvent : fEvents) {
			if (event.getId().equals(fEvent.getEventId())) {
				event.setFave(true);
			}
		}

		mav.addObject("event", event);
		// images are all the same so just grabbing first one
		mav.addObject("image", event.getImages().get(0));
		mav.addObject("date", event.getDates().getStart().getLocalDate());
		mav.addObject("link", event.getUrl());
		mav.addObject("attractions", event.get_embedded().getAttractions());
		mav.addObject("segment", event.getClassifications().get(0).getSegment());
		mav.addObject("genre", event.getClassifications().get(0).getGenre());
		mav.addObject("fave", event.getFave());

		// then grab weather, which requires grabbing the required deets from event
		String lat = event.get_embedded().getVenues().get(0).getLocation().getLatitude();
		String lon = event.get_embedded().getVenues().get(0).getLocation().getLongitude();
		String localDate = event.getDates().getStart().getLocalDate();
		String localTime = event.getDates().getStart().getLocalTime();
		Currently weather = apiServ2.getWeather(lat, lon, localDate, localTime);
		//transform the icon string to the format needed for icon canvas function
		String icon = weather.getIcon().toUpperCase().replace('-', '_');
		mav.addObject("weather", weather);
		mav.addObject(icon);
		System.out.println(icon);
		
		return mav;
		
	}

	@PostMapping("/event-details/{fave}/{id}")
	public ModelAndView favorite(@PathVariable("fave") Boolean fave, @PathVariable("id") String id) {

		Event event = apiServ2.getEventById(id);

		if (fave == false) {
		
		FavEvent fEvent = new FavEvent();
				
				fEvent.setEventId(event.getId());
				fEvent.setImage(event.getImages().get(0).getUrl());
				fEvent.setDate(event.getDates().getStart().getLocalDate());
				fEvent.setLink(event.get_links().getSelf().getHref());
				fEvent.setSegment(event.getClassifications().get(0).getSegment().getName());;
				fEvent.setGenre(event.getClassifications().get(0).getGenre().getName());
	

		eventsDao.save(fEvent);
		} else if (fave == true) {
			eventsDao.deleteById((eventsDao.findByEventId(id).getId()));
		}

		return new ModelAndView("redirect:/event-details/"+id);
	}

}
