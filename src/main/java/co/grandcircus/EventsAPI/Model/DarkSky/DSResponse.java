package co.grandcircus.EventsAPI.Model.DarkSky;

public class DSResponse {
	
	//even though this class and its datapoint are called "currently", it is actually the requested time, 
	//as per Dark Sky's Time Machine request documentation
	private Currently currently;

	public Currently getCurrently() {
		return currently;
	}

	public void setCurrently(Currently currently) {
		this.currently = currently;
	}

	@Override
	public String toString() {
		return "DSResponse [currently=" + currently + "]";
	}
	
	
}
