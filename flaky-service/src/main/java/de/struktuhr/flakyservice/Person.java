package de.struktuhr.flakyservice;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Person(String name, LocalDate birthday, LocalDateTime lastAccess) {
}
