package co.grandcircus.EventsAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.EventsAPI.Model.Embedded1;
import co.grandcircus.EventsAPI.Model.Event;
import co.grandcircus.EventsAPI.Model.TMResponse;
import co.grandcircus.EventsAPI.Model.Venue;

@Component
public class ApiService {

	@Value("${TM-api}")
	private String apiKey;

	private RestTemplate rt;

	{
		ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
			request.getHeaders().add(HttpHeaders.USER_AGENT, "ooeeee!");
			return execution.execute(request, body);
		};
		rt = new RestTemplateBuilder().additionalInterceptors(interceptor).build();
	}

	public Embedded1 getEvent(String zipCode) {

		String url = "https://app.ticketmaster.com/discovery/v2/events?apikey=7elxdku9GGG5k8j0Xm8KWdANDgecHMV0&postalCode="
				+ zipCode + "&radius=75&unit=miles&size=400";

		TMResponse response = rt.getForObject(url, TMResponse.class);

		Embedded1 events = response.get_embedded();

		return events;
	}

	public Embedded1 byVenue(String venue, String zipCode) {

		String url = "https://app.ticketmaster.com/discovery/v2/events?apikey=7elxdku9GGG5k8j0Xm8KWdANDgecHMV0&venueId="
				+ venue + "&postalCode=" + zipCode + "&radius=75&unit=miles&locale=*&size=100";

		TMResponse response = rt.getForObject(url, TMResponse.class);

		Embedded1 events = response.get_embedded();

		return events;
	}

	public Embedded1 byKeyword(String keyword, String zipCode) {

		String url = "https://app.ticketmaster.com/discovery/v2/events?apikey=7elxdku9GGG5k8j0Xm8KWdANDgecHMV0&keyword="
				+ keyword + "&postalCode=" + zipCode + "&radius=75&unit=miles&size=100";

		TMResponse response = rt.getForObject(url, TMResponse.class);

		Embedded1 events = response.get_embedded();
		return events;
	}

	public Embedded1 byDate(String date, String zipCode) {

		String url = "https://app.ticketmaster.com/discovery/v2/events?apikey=7elxdku9GGG5k8j0Xm8KWdANDgecHMV0&postalCode="
				+ zipCode + "&radius=75&unit=miles&locale=*&startDateTime=" + date + "T00:00:00Z";

		TMResponse response = rt.getForObject(url, TMResponse.class);

		Embedded1 events = response.get_embedded();

		return events;
	}

	public Embedded1 byEndDate(String date, String zipCode) {

		String url = "https://app.ticketmaster.com/discovery/v2/events?apikey=7elxdku9GGG5k8j0Xm8KWdANDgecHMV0&postalCode="
				+ zipCode + "&radius=75&unit=miles&locale=*&endDateTime=" + date + "T00:00:00Z";

		TMResponse response = rt.getForObject(url, TMResponse.class);

		Embedded1 events = response.get_embedded();

		return events;
	}

	public Embedded1 byDates(String date, String endDate, String zipCode) {

		String url = "https://app.ticketmaster.com/discovery/v2/events?apikey=7elxdku9GGG5k8j0Xm8KWdANDgecHMV0&postalCode="
				+ zipCode + "&radius=75&unit=miles&locale=*&startDateTime=" + date + "T00:00:00Z&endDateTime=" + endDate
				+ "T00:00:00Z";

		TMResponse response = rt.getForObject(url, TMResponse.class);

		Embedded1 events = response.get_embedded();

		return events;
	}

	public List<Venue> searchVenues(String venue) {

		String url = "https://app.ticketmaster.com/discovery/v2/venues?apikey=7elxdku9GGG5k8j0Xm8KWdANDgecHMV0&keyword="
				+ venue + "&locale=*&countryCode=US";
		
		Event response = rt.getForObject(url, Event.class);
		
		List<Venue> venues = response.get_embedded().getVenues();
		
		return venues;
	}
	public String url(String eventId) {

		String url = "https://app.ticketmaster.com/discovery/v2/events/"+eventId+"?apikey=7elxdku9GGG5k8j0Xm8KWdANDgecHMV0&locale=*";
		
		String newUrl = rt.getForObject(url, String.class);
		System.out.println(newUrl);
		return newUrl;
	}
}



