package de.struktuhr.orchestratingservice;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Person(String name, LocalDate birthday, LocalDateTime lastAccess) {
}
