package de.struktuhr.orchestratingservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final PersonServiceClient personServiceClient;

    public MainController(PersonServiceClient personServiceClient) {
        this.personServiceClient = personServiceClient;
    }

    @GetMapping("/person")
    public Person getPerson() {
        return personServiceClient.getPerson();
    }
}
