package co.grandcircus.EventsAPI.Model;

public class Image {
	
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Image [url=" + url + "]";
	}

	
}
