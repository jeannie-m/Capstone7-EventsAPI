package co.grandcircus.EventsAPI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.EventsAPI.Model.Embedded1;
import co.grandcircus.EventsAPI.Model.TMResponse;

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

		String url = "https://app.ticketmaster.com/discovery/v2/events?apikey=7elxdku9GGG5k8j0Xm8KWdANDgecHMV0&postalCode=" + zipCode + "&radius=75&unit=miles&apikey=tRJz11yleZhHxrcDsW5h4UrvpvuoMzsy";

		TMResponse response = rt.getForObject(url, TMResponse.class);
	
		Embedded1 events = response.get_embedded();
		
		return events;
	}
}