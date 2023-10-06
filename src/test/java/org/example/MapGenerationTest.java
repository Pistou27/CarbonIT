package org.example;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class MapGenerationTest {
    private MapGeneration mapGenerationTest;
    private static Instant startedAt;
    @BeforeAll
    public static void initStartingTime() {
        System.out.println("Appel avant tous les tests de MainTest");
        startedAt = Instant.now();
    }
    @AfterAll
    public static void showTestDuration() {
        System.out.println("Appel apres tous les tests de MainTest");
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Duree des tests = {0} ms", duration));
    }
    @BeforeEach
    public void initMapGenerationTest(){
        mapGenerationTest = new MapGeneration();
    }

    @AfterEach
    public void undefMapGenerationTest(){
        mapGenerationTest = null;
    }
    @Test
    public void theMapGenerationReturnMapWithoutTreatment() {
        // Given
        String input = """
                C - 3 - 4
                M - 1 - 0
                M - 2 - 1
                T - 0 - 3 - 2
                T - 1 - 3 - 3
                A - Lara - 1 - 1 - S - AADADAGGA
                """;

        // When
        // Then
        assertEquals("""
                . M .
                . A(Lara) M
                . . .
                T(2) T(3) .
                """, mapGenerationTest.simpleGeneration(input));

    }

    @Test
    public void testMapGenerationIfNoInput(){
        // Given
        String input = "";
        // When
        String output = mapGenerationTest.simpleGeneration(input);
        //Then
        assertEquals("Pas de donnees d'entrees", output);
    }

    @Test
    public void applicationShouldReturnSimpleFlatMap() {
        // Given
        String[] input = {"C", "7", "4"};
        // Where
        String output = mapGenerationTest.creationDeFlatMap(input);
        // Then
        assertEquals("""
                . . . . . . .
                . . . . . . .
                . . . . . . .
                . . . . . . .""", output);


    }

    @Test
    public void applicationShouldReturnFlatMapWith1MountMore() {
        // Given
        Montagne montagne = new Montagne("M", 1, 1);
        String map = """
. . .
. . .
. . .
. . .""";
        // Where
        String output = MapGeneration.ajoutElementMap(map,montagne);
        // Then
        assertEquals("""
. . .
. M .
. . .
. . .""", output);
    }

    @Test
    public void applicationShouldReturnFlatMapWithOneTresure(){
        // Given
        Tresor treasure = new Tresor("T", 1, 1, 1);
        String map = """
M . M
T(2) . .
. . .
. . .""";
        String output = MapGeneration.ajoutElementMap(map, treasure);
        assertEquals("""
M . M
T(2) T(1) .
. . .
. . .""", output);
    }

    @Test
    public void applicationShouldReturnFlatMapWithOneAdventurer(){
        // Given
        Adventurer adventurer = new Adventurer("A", "Indianna", 2, 1, "S", "AADADA", 0);
        String map = """
M . M
T(2) . .
. . .
. . .""";
        String output = MapGeneration.ajoutElementMap(map, adventurer);
        assertEquals("""
M . M
T(2) . A(Indianna)
. . .
. . .""", output);
    }
    @Test
    public void applicationShouldReturnFlatMapWithMountAventurerAndTresure() {
        // Given
        String input = """
C - 3 - 4
M - 1 - 1
M - 2 - 2
T - 0 - 3 - 2
A - Lara - 0 - 1 - S - AADADAGGA""";
        // Where
        String output = MapGeneration.creationDeCarte(input);
        // Then
        assertEquals("""
. . .
A(Lara) M .
. . M
T(2) . .""", output);
    }
}