package co.grandcircus.EventsAPI.Model.DarkSky;

public class Currently {

	private String summary;
	private String icon;
	private Double temperature;
	
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	
	@Override
	public String toString() {
		return "Currently [summary=" + summary + ", icon=" + icon + ", temperature=" + temperature + "]";
	}

	
	
}
