package co.grandcircus.EventsAPI.Model;

import java.util.List;

public class Event {

		private String name;
		private String type;
		private String id;
		private List<Image> images;
		private DateParent dates;
		private Link _links;
		private Embedded2 _embedded;
		private Classification classifications;
		
		
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
		public List<Image> getImages() {
			return images;
		}
		public void setImages(List<Image> images) {
			this.images = images;
		}
		public DateParent getDates() {
			return dates;
		}
		public void setDates(DateParent dates) {
			this.dates = dates;
		}
		
		public Link get_links() {
			return _links;
		}
		public void set_links(Link _links) {
			this._links = _links;
		}
		public Embedded2 get_embedded() {
			return _embedded;
		}
		public void set_embedded(Embedded2 _embedded) {
			this._embedded = _embedded;
		}

		public Classification getClassifications() {
			return classifications;
		}
		public void setClassifications(Classification classifications) {
			this.classifications = classifications;
		}
		@Override
		public String toString() {
			return "Event [name=" + name + ", type=" + type + ", id=" + id + ", images=" + images + ", dates=" + dates
					+ ", _links=" + _links + ", _embedded=" + _embedded + ", classifications=" + classifications + "]";
		}

		
		
}

