package co.grandcircus.EventsAPI.Model;

import java.util.List;

public class Embedded1 {
	
	private List<Event> events;

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "Embedded1 [events=" + events + "]";
	}
	
	
}
