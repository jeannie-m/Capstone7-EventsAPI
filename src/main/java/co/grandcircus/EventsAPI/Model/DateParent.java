package co.grandcircus.EventsAPI.Model;

public class DateParent {
	
	private DateChild start;

	public DateChild getStart() {
		return start;
	}

	public void setStart(DateChild start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return "DateParent [start=" + start + "]";
	}

}
