package net.AyushPrakash.journalApp.cache;
import lombok.Getter;
import lombok.Setter;
import net.AyushPrakash.journalApp.Entity.Configure;
import net.AyushPrakash.journalApp.Repository.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
@Setter
public class AppCache {
    @Autowired
    public Config configureRepo;
    public Map<String,String> appCache=new HashMap<>();

    @PostConstruct
    public void init() {
        List<Configure> all = configureRepo.findAll();

        for (Configure configure : all) {
            String key = configure.getKey();
            String value = configure.getValue();

            if (key.contains("<API_KEY>") && key.contains("<CITY>")) {
                appCache.put("WeatherApiTemp", key);
                appCache.put("WeatherApiKeyTemp", value);
                break; // stop after finding the correct one
            }
        }

        // Optional: Log loaded values for debugging
        System.out.println("WeatherApiTemp loaded: " + appCache.get("WeatherApiTemp"));
        System.out.println("WeatherApiKeyTemp loaded: " + appCache.get("WeatherApiKeyTemp"));
    }


}
