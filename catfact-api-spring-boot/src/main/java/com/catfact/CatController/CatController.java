package com.catfact.CatController;

import com.catfact.model.CatFactResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CatController {

    private static final String BASE_URL = "https://catfact.ninja";
    private static final String FACT_ENDPOINT = "/fact";

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/random-fact") //http://localhost:8080/random-fact
    public String getRandomFact(@RequestParam(required = false) Integer max_length) {
        String url = BASE_URL + FACT_ENDPOINT;

        if (max_length != null) {
            url += "?max_length=" + max_length;
        }

        CatFactResponse response = restTemplate.getForObject(url, CatFactResponse.class);

        if (response != null) {
            return "It's Work! " + response.getFact();
        } else {
            return "Failed to fetch cat fact.";
        }
    }
}