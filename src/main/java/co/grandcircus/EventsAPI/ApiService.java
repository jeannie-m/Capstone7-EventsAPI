package co.grandcircus.EventsAPI;


import org.springframework.beans.factory.annotation.Value;
import java.util.List;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.EventsAPI.Model.Embedded1;


@Component
public class ApiService {
	
	@Value("${TM-api")
	private String apiKey;
	
	private RestTemplate rt;

	{
		ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
			request.getHeaders().add(HttpHeaders.USER_AGENT, "ooeeee!");
			return execution.execute(request, body);
		};
		rt = new RestTemplateBuilder().additionalInterceptors(interceptor).build();
	}
	
	public Embedded1 getEvent() {

		String url = "https://app.ticketmaster.com/discovery/v2/events.json?classificationName=music&dmaId=324&apikey=tRJz11yleZhHxrcDsW5h4UrvpvuoMzsy";

		Embedded1 events = rt.getForObject(url, Embedded1.class);
		
		return events;
	}
}