package org.example;

import org.junit.jupiter.api.*;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private static Instant startedAt;
    @BeforeAll
    public static void initStartingTime() {
        System.out.println("Appel avant tous les tests de MainTest");
        startedAt = Instant.now();
    }
    @AfterAll
    public static void showTestDuration() {
        System.out.println("Appel après tous les tests de MainTest");
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Durée des tests = {0} ms", duration));
    }

    @Test
    void applicationShouldStartWithoutFail() {
        // Given
        String[] input = new String[]{};
        // When
        Main.main(input);
        // Then
        return;
    }
}