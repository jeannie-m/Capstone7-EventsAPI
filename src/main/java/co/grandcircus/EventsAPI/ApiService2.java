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
import co.grandcircus.EventsAPI.Model.DarkSky.Currently;
import co.grandcircus.EventsAPI.Model.DarkSky.DSResponse;

@Component
public class ApiService2 {

	@Value("${TM-api}")
	private String apiKey;
	@Value("${DS-api}")
	private String apiKeyDS;
	

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
		//I grab the event at index 0 because the .getEvents method returns a list but there should only be one in the list anyway. 
		return response.get_embedded().getEvents().get(0);
	}
	
	//ok, the date format of the request url has to be: [YYYY]-[MM]-[DD]T[HH]:[MM]:[SS]
	//get the lat and long from the location of the venue of the embedded2 of the event of the embedded1 of the TMResponse
	//get the date from the datechild of the dateparent of the event of the embedded1 of the TMResponse
	//the date is in format 2020-03-08 so we just need to get it to 2020-03-08THH:MM:SS which can be expressed through variables like so:
	// localDate + "T" + localTime
	//https://api.darksky.net/forecast/8c6b3b6c4597c6e4cc66baf1236c704c/42.33,-83.33,2020-03-06T12:00:00
	
	public Currently getWeather(String lat, String lon, String localDate, String localTime) {
		
		String url = "https://api.darksky.net/forecast/" + apiKeyDS + "/" + lat + "," + lon + "," + 
		localDate + "T" + localTime;
		DSResponse response = rt.getForObject(url, DSResponse.class);
		return response.getCurrently();
	}
	
}