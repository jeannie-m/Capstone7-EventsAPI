package co.grandcircus.EventsAPI.Model;

import java.util.List;

public class Embedded2 {
	
	private List<Attraction> attractions;
	private List<Venue> venues;

	public List<Attraction> getAttractions() {
		return attractions;
	}

	public void setAttractions(List<Attraction> attractions) {
		this.attractions = attractions;
	}



	public List<Venue> getVenues() {
		return venues;
	}

	public void setVenues(List<Venue> venues) {
		this.venues = venues;
	}

	@Override
	public String toString() {
		return "Embedded2 [attractions=" + attractions + ", venues=" + venues + "]";
	}
	
	
}
