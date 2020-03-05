package co.grandcircus.EventsAPI.Model;

import java.util.List;

public class Embedded2 {
	
	private List<Attraction> attractions;

	public List<Attraction> getAttractions() {
		return attractions;
	}

	public void setAttractions(List<Attraction> attractions) {
		this.attractions = attractions;
	}

	@Override
	public String toString() {
		return "Embedded2 [attractions=" + attractions + "]";
	}
	
	
}
