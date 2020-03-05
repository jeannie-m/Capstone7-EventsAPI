package co.grandcircus.EventsAPI.Model;

public class TMResponse {
	
	private Embedded1 _embedded;

	public Embedded1 get_embedded() {
		return _embedded;
	}

	public void set_embedded(Embedded1 _embedded) {
		this._embedded = _embedded;
	}

	@Override
	public String toString() {
		return "TMResponse [_embedded=" + _embedded + "]";
	}
	
	
}
