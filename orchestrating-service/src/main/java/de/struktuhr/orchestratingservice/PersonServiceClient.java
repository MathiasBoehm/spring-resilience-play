package de.struktuhr.orchestratingservice;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@Service
public class PersonServiceClient {

    private final Logger log = LoggerFactory.getLogger(PersonServiceClient.class);

    private final String serviceUrl;

    private final RestTemplate restTemplate;

    public PersonServiceClient(@Value("${personservice.url:http://localhost:9080}") String serviceUrl,
                               @Value("${personservice.connectTimeoutMillis:100}") int connectTimeout,
                               @Value("${personservice.readTimeoutMillis:500}") int readTimeout,
                              RestTemplateBuilder restTemplateBuilder) {
        this.serviceUrl = serviceUrl;

        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(connectTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }


    @CircuitBreaker(name = "personBackend", fallbackMethod = "fallbackGetPerson")
    @Retry(name = "personBackend")
    public Person getPerson() {
        log.info("Try read person from {} at {}", serviceUrl, LocalDateTime.now());
        return restTemplate.getForObject(serviceUrl, Person.class);
    }

    public Person fallbackGetPerson(Exception e) {
        final Person p = new Person("Fanny Fallback", LocalDate.of(1980, Month.JUNE, 15), LocalDateTime.now());
        log.info("Fallback get Person {}", p);
        return p;
    }
}
