package net.AyushPrakash.journalApp.service;

import net.AyushPrakash.journalApp.Entity.WeatherResponse;
import net.AyushPrakash.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class weatherService {
    @Value("${weather_api_key}")
    private  String ApiKey;
    private final  String Api="https://api.weatherstack.com/current?access_key=API_KEY&query=CITY;";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    AppCache app;
    public WeatherResponse GetWeather(String City) {
        String template = app.appCache.get("WeatherApiTemp");
        String apiKey = app.appCache.get("WeatherApiKeyTemp");

        if (template == null || apiKey == null || City == null) {
            throw new RuntimeException("Weather API config missing in AppCache.");
        }

        String FinalApi = template
                .replace("<API_KEY>", apiKey)
                .replace("<CITY>", City);

        ResponseEntity<WeatherResponse> response = restTemplate.exchange(
                FinalApi,
                HttpMethod.GET,
                null,
                WeatherResponse.class
        );

        return response.getBody();
    }


}
