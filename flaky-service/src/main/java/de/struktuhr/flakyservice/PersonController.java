package de.struktuhr.flakyservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@RestController
public class PersonController {

    private final static Logger log = LoggerFactory.getLogger(PersonController.class);

    @GetMapping("/")
    public Person getPerson() {
        final Person p = new Person("John Doe", LocalDate.of(1995, Month.MAY, 23), LocalDateTime.now());
        log.info("Get Person {}", p);
        return p;
    }
}
