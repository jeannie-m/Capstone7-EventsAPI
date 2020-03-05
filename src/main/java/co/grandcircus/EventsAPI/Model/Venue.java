package co.grandcircus.EventsAPI.Model;

public class Venue {

		private String name;
		private String PostalCode;
		private Location location;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPostalCode() {
			return PostalCode;
		}
		public void setPostalCode(String postalCode) {
			PostalCode = postalCode;
		}
		public Location getLocation() {
			return location;
		}
		public void setLocation(Location location) {
			this.location = location;
		}
		@Override
		public String toString() {
			return "Venue [name=" + name + ", PostalCode=" + PostalCode + ", location=" + location + "]";
		}
		
		
	
}
