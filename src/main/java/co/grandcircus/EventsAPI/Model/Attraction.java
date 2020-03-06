package co.grandcircus.EventsAPI.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import co.grandcircus.EventsAPI.Entities.FavEvent;

@Entity
public class Attraction {
	
	private String name;
	private String type;
	private String id;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long attractionId;
	
	@ManyToMany(mappedBy = "attractions") 
	private List<FavEvent> favEvents;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	public List<FavEvent> getFavEvents() {
		return favEvents;
	}
	public void setFavEvents(List<FavEvent> favEvents) {
		this.favEvents = favEvents;
	}
	@Override
	public String toString() {
		return "Attraction [name=" + name + ", type=" + type + ", id=" + id + "]";
	}
	
	

}
