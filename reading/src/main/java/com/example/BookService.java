package com.example;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class BookService {

    private final RestTemplate template;

    public BookService(RestTemplate template) {
        this.template = template;
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public String readingList() {
        URI uri = URI.create("http://localhost:8081/recommended");
        return this.template.getForObject(uri, String.class);
    }

    public String reliable() {
        return "Cloud Native Java (O'Reilly)";
    }
}
