package co.grandcircus.EventsAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.EventsAPI.Model.Embedded1;
import co.grandcircus.EventsAPI.Model.Event;
import co.grandcircus.EventsAPI.Model.TMResponse;

@Component
public class ApiService2 {

	@Value("${TM-api}")
	private String apiKey;
	
	@Autowired
	private ApiService apiServ;

	private RestTemplate rt;

	{
		ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
			request.getHeaders().add(HttpHeaders.USER_AGENT, "ooeeee!");
			return execution.execute(request, body);
		};
		rt = new RestTemplateBuilder().additionalInterceptors(interceptor).build();
	}
	
	
	public Event getEventById(String id) {
		
		String url = "https://app.ticketmaster.com/discovery/v2/events.json?Id=" + id + "&apikey=" + apiKey;
		
		TMResponse response = rt.getForObject(url, TMResponse.class);
		
		//I grab the event at index 0 because the .getEvents method returns a list but there should only be one in the list. 
		return response.get_embedded().getEvents().get(0);
	}
}