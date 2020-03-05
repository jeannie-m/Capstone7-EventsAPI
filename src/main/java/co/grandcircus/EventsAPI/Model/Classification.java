package co.grandcircus.EventsAPI.Model;

public class Classification {

	private Segment segment;
	private Genre genre;
	
	
	public Segment getSegment() {
		return segment;
	}
	public void setSegment(Segment segment) {
		this.segment = segment;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	@Override
	public String toString() {
		return "Classification [segment=" + segment + ", genre=" + genre + "]";
	}
	
	
}
