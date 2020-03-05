package co.grandcircus.EventsAPI.Model;

public class Link {
	private LinkSelf self;

	public LinkSelf getSelf() {
		return self;
	}

	public void setSelf(LinkSelf self) {
		this.self = self;
	}

	@Override
	public String toString() {
		return "Link [self=" + self + "]";
	}
	
}
