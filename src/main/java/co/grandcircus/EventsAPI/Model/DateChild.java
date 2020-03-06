package co.grandcircus.EventsAPI.Model;

public class DateChild {
	private String localDate;
	private String localTime;

	public String getLocalDate() {
		return localDate;
	}

	public void setLocalDate(String localDate) {
		this.localDate = localDate;
	}

	public String getLocalTime() {
		return localTime;
	}

	public void setLocalTime(String localTime) {
		this.localTime = localTime;
	}

	@Override
	public String toString() {
		return "DateChild [localDate=" + localDate + ", localTime=" + localTime + "]";
	}
	
	
}
