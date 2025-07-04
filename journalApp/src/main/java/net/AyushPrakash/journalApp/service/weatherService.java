package net.AyushPrakash.journalApp.service;

import net.AyushPrakash.journalApp.Entity.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class weatherService {
    private final String ApiKey="17fa299c98cbf1762aebe3e5cf9a316d";
    private final  String Api="https://api.weatherstack.com/current?access_key=API_KEY&query=CITY;";
    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse GetWeather(String City)
    {
        String FinalApi=Api.replace("API_KEY",ApiKey).replace("CITY",City);
        ResponseEntity<WeatherResponse> response= (restTemplate.exchange(FinalApi, HttpMethod.GET,null, WeatherResponse.class));
        return response.getBody();
    }

}
