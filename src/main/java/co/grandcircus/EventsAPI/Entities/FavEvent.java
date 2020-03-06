package co.grandcircus.EventsAPI.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import co.grandcircus.EventsAPI.Model.Attraction;
import co.grandcircus.EventsAPI.Model.DarkSky.Currently;

@Entity
public class FavEvent {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String eventId;
	private String image;
	private String date;
	private String link;
	private String segment;
	private String genre;
	
	@OneToOne
	private Currently weather;
	
	@ManyToMany   
	private List<Attraction> attractions;
//	(cascade = { CascadeType.ALL })
//	 @JoinTable(
//		        name = "Event_Attraction", 
//		        joinColumns = { @JoinColumn(name = "eventId") }, 
//		        inverseJoinColumns = { @JoinColumn(name = "id") }
//		    )	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Currently getWeather() {
		return weather;
	}

	public void setWeather(Currently weather) {
		this.weather = weather;
	}

	public List<Attraction> getAttractions() {
		return attractions;
	}

	public void setAttractions(List<Attraction> attractions) {
		this.attractions = attractions;
	}
	

	

	

}
