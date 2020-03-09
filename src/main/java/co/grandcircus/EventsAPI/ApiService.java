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

	public Embedded1 getEvent(String zipCode) { //creates list of events by zip code
		
		String pageNum = "1";

		String url = "https://app.ticketmaster.com/discovery/v2/events?apikey=tRJz11yleZhHxrcDsW5h4UrvpvuoMzsy&postalCode="+
		zipCode+"&radius=75&unit=miles&locale=*&size=40&page="+ pageNum;
		
		TMResponse response = rt.getForObject(url, TMResponse.class);

		Embedded1 events = response.get_embedded();

		return events;
	}
	
public Embedded1 getEvent(String zipCode, String pageNum) { //creates list of events by zip code
		

		String url = "https://app.ticketmaster.com/discovery/v2/events?apikey=tRJz11yleZhHxrcDsW5h4UrvpvuoMzsy&postalCode="+
		zipCode+"&radius=75&unit=miles&locale=*&size=40&page="+ pageNum;
		
		TMResponse response = rt.getForObject(url, TMResponse.class);
		Embedded1 events = response.get_embedded();

		return events;
	}

	public Embedded1 byVenue(String venue, String zipCode) { // creates a list of events by venue id

		String url = "https://app.ticketmaster.com/discovery/v2/events?apikey=7elxdku9GGG5k8j0Xm8KWdANDgecHMV0&venueId="
				+ venue + "&postalCode=" + zipCode + "&radius=75&unit=miles&locale=*&size=100";

		TMResponse response = rt.getForObject(url, TMResponse.class);
		Embedded1 events = response.get_embedded();

		return events;
	}

	public Embedded1 byKeyword(String keyword, String zipCode) { // creates a list of events by keyword

		String url = "https://app.ticketmaster.com/discovery/v2/events?apikey=7elxdku9GGG5k8j0Xm8KWdANDgecHMV0&keyword="
				+ keyword + "&postalCode=" + zipCode + "&radius=75&unit=miles&size=100";

		TMResponse response = rt.getForObject(url, TMResponse.class);

		Embedded1 events = response.get_embedded();
		return events;
	}

	public Embedded1 byDate(String date, String zipCode) { // creates a list of events by start date

		String url = "https://app.ticketmaster.com/discovery/v2/events?apikey=7elxdku9GGG5k8j0Xm8KWdANDgecHMV0&postalCode="
				+ zipCode + "&radius=75&unit=miles&locale=*&startDateTime=" + date + "T00:00:00Z";

		TMResponse response = rt.getForObject(url, TMResponse.class);

		Embedded1 events = response.get_embedded();

		return events;
	}

	public Embedded1 byEndDate(String date, String zipCode) { // creates a list of event by end date

		String url = "https://app.ticketmaster.com/discovery/v2/events?apikey=7elxdku9GGG5k8j0Xm8KWdANDgecHMV0&postalCode="
				+ zipCode + "&radius=75&unit=miles&locale=*&endDateTime=" + date + "T00:00:00Z";

		TMResponse response = rt.getForObject(url, TMResponse.class);

		Embedded1 events = response.get_embedded();

		return events;
	}

	public Embedded1 byDates(String date, String endDate, String zipCode) { // creates a list of events with start and
																			// end date

		String url = "https://app.ticketmaster.com/discovery/v2/events?apikey=7elxdku9GGG5k8j0Xm8KWdANDgecHMV0&postalCode="
				+ zipCode + "&radius=75&unit=miles&locale=*&startDateTime=" + date + "T00:00:00Z&endDateTime=" + endDate
				+ "T00:00:00Z";

		TMResponse response = rt.getForObject(url, TMResponse.class);

		Embedded1 events = response.get_embedded();

		return events;
	}

	public List<Venue> searchVenues(String venue) { // creates a list of venues by keyword

		String url = "https://app.ticketmaster.com/discovery/v2/venues?apikey=7elxdku9GGG5k8j0Xm8KWdANDgecHMV0&keyword="
				+ venue + "&locale=*&countryCode=US";

		Event response = rt.getForObject(url, Event.class);

		if (response != null) {

			List<Venue> venues = response.get_embedded().getVenues();

			return venues;

		}

		return null;
	}

	public Embedded1 byGenre(String genre, String zipCode) { // creates a list of events with start and end date

		String url = "https://app.ticketmaster.com/discovery/v2/events?apikey=tRJz11yleZhHxrcDsW5h4UrvpvuoMzsy&postalCode="
				+ zipCode + "&radius=75&unit=miles&locale=*&genreId=" + genre;

		TMResponse response = rt.getForObject(url, TMResponse.class);

		Embedded1 events = response.get_embedded();

		return events;
	}

}
