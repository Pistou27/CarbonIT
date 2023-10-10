package org.example;

import org.example.entity.Adventurer;
import org.example.entity.Mountain;
import org.example.entity.Treasure;
import org.junit.jupiter.api.*;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
    }

    @Test
    void givenValidateInput_whenCartographyExtract_returnCartography(){
        String validateInput ="""
C - 3 - 4
M - 1 - 0
M - 2 - 1
T - 0 - 3 - 2
T - 1 - 3 - 3
A - Lara - 1 - 1 - S - AADADAGGA
A - Jhon - 0 - 2 - E - AADA""";
        String output = Main.cartographyExtract(validateInput);

        assertEquals("C - 3 - 4", output);
    }

    @Test
    void testvalidateInput(){
        String input = """
C - 3 - 4
M - 1 - 0
M - 2 - 1
#Commentaire inutile qui doit disparaitre
T - 0 - 3 - 2
T - 1 - 3 - 3
A - Lara - 1 - 1 - S - AADADAGGA
A - Jhon - 0 - 2 - E - AADA
#Test filtre
MNEEEEEEEUUUU""";
        String output = Main.validateInput(input);

        assertEquals("""
C - 3 - 4
M - 1 - 0
M - 2 - 1
T - 0 - 3 - 2
T - 1 - 3 - 3
A - Lara - 1 - 1 - S - AADADAGGA
A - Jhon - 0 - 2 - E - AADA""", output);
    }

    @Test
    void givenListAdventurerTreasureMountainCartographyAndResultMap_whenAddOutput_returnFinalOutputWithAllElements(){
        String cartography = "C - 3 - 4";
        List<Mountain> mountains = new ArrayList<>();
        Mountain mountain = new Mountain("M", 1, 0);
        mountains.add(mountain);
        List<Treasure> treasures = new ArrayList<>();
        Treasure treasure = new Treasure("T", 1, 3, 2);
        treasures.add(treasure);
        List<Adventurer> adventurers = new ArrayList<>();
        Adventurer adventurer = new Adventurer("A", "Lara", 0, 3, "S", "AGDAA",1);
        adventurers.add(adventurer);
        String resultMap = """
                . M .
                . . .
                . . .
                A(Lara) T(2) .""";

        String output = Main.addCartographyMountainAdventurerAndTreasureIntoOutput(adventurers, treasures, mountains, cartography, resultMap);
        assertEquals("""
                C - 3 - 4
                M - 1 - 0
                # {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}
                T - 1 - 3 - 2
                {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}
                A - Lara - 0 - 3 - S - 1
                                
                . M .
                . . .
                . . .
                A(Lara) T(2) .""", output);
    }
}