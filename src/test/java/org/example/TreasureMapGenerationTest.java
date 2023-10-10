package org.example;


import org.example.entity.Adventurer;
import org.example.entity.Mountain;
import org.example.entity.Treasure;
import org.example.use_case.TreasureMapGeneration;
import org.junit.jupiter.api.*;


import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TreasureMapGenerationTest {
    private TreasureMapGeneration treasureMapGenerationTest;
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
        treasureMapGenerationTest = new TreasureMapGeneration();
    }

    @AfterEach
    public void undefMapGenerationTest(){
        treasureMapGenerationTest = null;
    }

    @Test
    public void testEntry_whenCreationOfPlainMap_generateAMapWithoutElement() {
        // Given
        String[] input = {"C", "7", "4"};
        // Where
        String output = treasureMapGenerationTest.creationOfPlainMap(input);
        // Then
        assertEquals("""
                . . . . . . .
                . . . . . . .
                . . . . . . .
                . . . . . . .""", output);
    }
    @Test
    public void testMountain_whenAddElementMap_generateAMapWithOneMountain() {
        // Given
        Mountain mountain = new Mountain("M", 2, 1);
        String map = """
. . .
. . .
. . .
. . .""";
        // Where
        String output = TreasureMapGeneration.addElementMap(map,mountain);
        // Then
        assertEquals("""
. . .
. . M
. . .
. . .""", output);
    }

    @Test
    public void testTreasure_whenAddElementMap_generateAMapWithTwoTreasure(){
        // Given
        Treasure treasure = new Treasure("T", 2, 1, 1);
        String map = """
M . M
T(2) . .
. . .
. . .""";
        String output = TreasureMapGeneration.addElementMap(map, treasure);
        assertEquals("""
M . M
T(2) . T(1)
. . .
. . .""", output);
    }

    @Test
    public void testAdventurer_whenAddElementMap_generateAMapWithOneAdventurer(){
        // Given
        Adventurer adventurer = new Adventurer("A", "Indianna", 2, 1, "S", "AADADA", 0);
        String map = """
M . M
T(2) . .
. . .
. . .""";
        String output = TreasureMapGeneration.addElementMap(map, adventurer);
        assertEquals("""
M . M
T(2) . A(Indianna)
. . .
. . .""", output);
    }
    @Test
    public void testEntry_whenCreateMapWithElements_generateAMapWithElementsEntry() {
        // Given
        String input = """
C - 3 - 4
M - 1 - 1
M - 2 - 2
T - 0 - 3 - 2
A - Lara - 0 - 1 - S - AADADAGGA""";
        // Where
        String output = TreasureMapGeneration.createMapWithElements(input);
        // Then
        assertEquals("""
. . .
A(Lara) M .
. . M
T(2) . .""", output);
    }

    @Test
    public void testAdventurer_whenMovementSequenceAdventurer_followHisMovementSequenceAndBlockedByAMont() {
        // Given
        List<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(new Adventurer("A", "Lara", 1, 1, "S", "AAAAA", 0));
        List<Treasure> treasures = new ArrayList<>();
        String map = """
. . .
. A(Lara) .
. . .
. M .""";

        // Where
        String output = TreasureMapGeneration.movementSequenceAdventurer(adventurers, treasures, map);
        // Then
        assertEquals("""
. . .
. . .
. A(Lara) .
. M .""", output);
    }
    @Test
    public void testAdventurer_whenMovementSequenceAdventurer_goToSouthEst() {
        // Given
        List<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(new Adventurer("A", "Lara", 0, 0, "E", "AAAAAGGGAAAAADDDAAAAA", 0));
        List<Treasure> treasures = new ArrayList<>();
        String map = """
A(Lara) . .
. . .
. . .
. . .""";

        // Where
        String output = TreasureMapGeneration.movementSequenceAdventurer(adventurers, treasures, map);
        // Then
        assertEquals("""
. . .
. . .
. . .
. . A(Lara)""", output);
    }
    @Test
    public void testAdventurerGoToSouthEstAndTryToEscapeTheMap() {
        // Given
        List<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(new Adventurer("A", "Lara", 1, 1, "N", "GGGADAAAAA", 0));
        List<Treasure> treasures = new ArrayList<>();
        String map = """
. . .
. A(Lara) .
. . .
. . .""";

        // Where
        String output = TreasureMapGeneration.movementSequenceAdventurer(adventurers, treasures, map);
        // Then
        assertEquals("""
. . .
. . .
. . .
. . A(Lara)""", output);
    }

    @Test
    public void testAdventurer_whenMovementSequenceAdventurer_OneAdventurerIsBlockByAAnotherAdventurer (){
        // Given
        List<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(new Adventurer("A", "Lara", 2, 1, "O", "AGA", 0));
        adventurers.add(new Adventurer("A", "John", 0, 1, "E", "AA", 0));
        List<Treasure> treasures = new ArrayList<>();
        String map = """
. . .
A(John) . A(Lara)
. . .
. . .""";

        // Where
        String output = TreasureMapGeneration.movementSequenceAdventurer(adventurers, treasures, map);
        // Then
        assertEquals("""
. . .
A(John) . .
. A(Lara) .
. . .""", output);
    }
    @Test
    public void testAdventurer_whenMovementSequenceAdventurer_followHisMovementSequenceAndTakeTreasure() {
        // Given
        List<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(new Adventurer("A", "Lara", 1, 1, "S", "AA", 0));
        List<Treasure> treasures = new ArrayList<>();
        treasures.add(new Treasure("T", 1, 2, 3));
        String map = """
. . .
. A(Lara) .
. T(3) .
. . .""";

        // Where
        String output = TreasureMapGeneration.movementSequenceAdventurer(adventurers, treasures, map);
        // Then
        assertEquals("""
. . .
. . .
. T(2) .
. A(Lara) .""", output);
    }
    @Test
    public void testAdventurer_whenMovementSequenceAdventurer_followHisMovementSequenceAndTakeTreasures() {
        // Given
        List<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(new Adventurer("A", "Lara", 1, 1, "S", "AADADA", 0));
        List<Treasure> treasures = new ArrayList<>();
        treasures.add(new Treasure("T", 1, 2, 2));
        treasures.add(new Treasure("T", 0, 3, 1));
        String map = """
. . .
. A(Lara) .
M T(2) .
T(1) . .""";

        // Where
        String output = TreasureMapGeneration.movementSequenceAdventurer(adventurers, treasures, map);
        // Then
        assertEquals("""
. . .
. . .
M T(1) .
A(Lara) . .""", output);
    }

    @Test
    public void givenListAdventurersListTreasures_whenAddedAMap_returnMapWithAdventurersAndTreasures(){
        List<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(new Adventurer("A", "Lara", 2, 2, "S", "AADADA", 0));
        adventurers.add(new Adventurer("A", "Jonh", 0, 3, "S", "AADADA", 1));
        List<Treasure> treasures = new ArrayList<>();
        treasures.add(new Treasure("T", 1, 2, 2));
        treasures.add(new Treasure("T", 0, 3, 1));
        String map = """
. . .
. . .
M . .
. . .""";
        String[][] lines = TreasureMapGeneration.updateMapWithTreasuresAndAdventurers(treasures, adventurers, TreasureMapGeneration.readLineByLine(map));
        String output = TreasureMapGeneration.concatenationOfLines(lines);

        assertEquals("""
. . .
. . .
M T(2) A(Lara)
A(Jonh)T(1) . .""",output);

    }
}