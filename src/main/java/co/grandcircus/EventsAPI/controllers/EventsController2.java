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
		System.out.println(event.get_embedded().getAttractions());
		mav.addObject("segment", event.getClassifications().get(0).getSegment());
		mav.addObject("genre", event.getClassifications().get(0).getGenre());
		mav.addObject("fave", event.getFave());

		// then grab weather, which requires grabbing the required deets from event
		String lat = event.get_embedded().getVenues().get(0).getLocation().getLatitude();
		String lon = event.get_embedded().getVenues().get(0).getLocation().getLongitude();
		String localDate = event.getDates().getStart().getLocalDate();
		String localTime = event.getDates().getStart().getLocalTime();
		Currently weather = apiServ2.getWeather(lat, lon, localDate, localTime);
		// transform the icon string to the format needed for icon canvas function
		System.out.println(weather);

		if (weather.getIcon() != null) {
			String icon = weather.getIcon().toUpperCase().replace('-', '_');
			mav.addObject("icon", icon);
			System.out.println(icon);
		}
		mav.addObject("weather", weather);

		return mav;

	}

	@PostMapping("/event-details/{fave}/{id}")
	public ModelAndView favorite(@PathVariable("fave") Boolean fave, @PathVariable("id") String id) {

		ModelAndView mav = new ModelAndView("redirect:/event-details/" + id);
		Event event = apiServ2.getEventById(id);

		if (fave == false) { // favorites an event and adds it to SQL database if not already favorited

			FavEvent fEvent = new FavEvent();
			// On the bucket list, the events are showing up with the images(?) but none of
			// the rest of the details
			// It doesn't appear to be running the following code:
			fEvent.setEventId(event.getId());
			fEvent.setName(event.getName());

			String lat = event.get_embedded().getVenues().get(0).getLocation().getLatitude();
			String lon = event.get_embedded().getVenues().get(0).getLocation().getLongitude();
			String localDate = event.getDates().getStart().getLocalDate();
			String localTime = event.getDates().getStart().getLocalTime();
			Currently weather = apiServ2.getWeather(lat, lon, localDate, localTime);

			fEvent.setWeather(weather);

			fEvent.setAttractions(event.get_embedded().getAttractions());
			fEvent.setImage(event.getImages().get(0).getUrl());
			fEvent.setDate(event.getDates().getStart().getLocalDate());
			fEvent.setLink(event.getUrl());
			fEvent.setSegment(event.getClassifications().get(0).getSegment().getName());
			fEvent.setGenre(event.getClassifications().get(0).getGenre().getName());
			fEvent.setAttractions(event.get_embedded().getAttractions());

			mav.addObject("event", fEvent);

			eventsDao.save(fEvent);
		} else if (fave == true) { // deletes an event from the database if already favorited

			eventsDao.deleteById((eventsDao.findByEventId(id).getId()));
		}

		return mav;
	}

}
