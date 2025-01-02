package com.journalapp.journalApp.service;

import com.journalapp.journalApp.api.response.WeatherResponse;
import com.journalapp.journalApp.cache.AppCache;
import com.journalapp.journalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse != null) {
            return weatherResponse;
        }else {
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body != null) {
                redisService.set("weather_of_" + city, body, 300l);
            }
            return body;
        }


//        String requestBody = "  {\n" +
//                "   \"userName\":\"saurabh\",\n" +
//                "   \"password\":\"saurabh\", \n" +
//                "   }   ";
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("key", "value");
//        User user = User.builder().userName("Saurabh").password("Saurabh").build();
//        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody);
//        HttpEntity<User> httpEntity = new HttpEntity<>(user);
//        HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);

//        ResponseEntity<WeatherResponse> response1 = restTemplate.exchange(finalAPI, HttpMethod.POST, httpEntity, WeatherResponse.class);
    }

}
