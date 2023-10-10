package org.example.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdventurerTest {

    private Adventurer testAdventurer;

    @BeforeEach
    public void setAdventurer(){
        testAdventurer = new Adventurer("A", "Lara", 1, 2, "N", "ADDG", 0);
    }

    @AfterEach
    public void undefAdventurer(){
        testAdventurer = null;
    }

    @Test
    void testToString() {
        assertEquals("A - Lara - 1 - 2 - N - 0", testAdventurer.toString());
    }

    @Test
    void testTurnRightFromN() {
        testAdventurer.setOrientation("N");
        testAdventurer.turnRight();
        assertEquals("E", testAdventurer.getOrientation());
    }
    @Test
    void testTurnRightFromE() {
        testAdventurer.setOrientation("E");
        testAdventurer.turnRight();
        assertEquals("S", testAdventurer.getOrientation());
    }
    @Test
    void testTurnRightFromS() {
        testAdventurer.setOrientation("S");
        testAdventurer.turnRight();
        assertEquals("O", testAdventurer.getOrientation());
    }
    @Test
    void testTurnRightFromO() {
        testAdventurer.setOrientation("O");
        testAdventurer.turnRight();
        assertEquals("N", testAdventurer.getOrientation());
    }
    @Test
    void testTurnLeftFromN() {
        testAdventurer.setOrientation("N");
        testAdventurer.turnLeft();
        assertEquals("O", testAdventurer.getOrientation());
    }
    @Test
    void testTurnLeftFromO() {
        testAdventurer.setOrientation("O");
        testAdventurer.turnLeft();
        assertEquals("S", testAdventurer.getOrientation());
    }
    @Test
    void testTurnLeftFromS() {
        testAdventurer.setOrientation("S");
        testAdventurer.turnLeft();
        assertEquals("E", testAdventurer.getOrientation());
    }
    @Test
    void testTurnLeftFromE() {
        testAdventurer.setOrientation("E");
        testAdventurer.turnLeft();
        assertEquals("N", testAdventurer.getOrientation());
    }
    @Test
    public void testAdventurersMustBeToBeInsideAList(){
        String input = """
C - 3 - 4
T - 0 - 3 - 2
T - 1 - 3 - 3
A - Lara - 1 - 1 - S - AADADAGGA
A - Alex - 2 - 2 - S - AADADAGGA
A - India - 29 - 32 - S - AADADAGGA""";
        List<Adventurer> adventurerListOut = new ArrayList<>();
        adventurerListOut.add(new Adventurer("A","Lara", 1, 1, "S", "AADADAGGA", 0));
        adventurerListOut.add(new Adventurer("A","Alex", 2, 2, "S", "AADADAGGA", 0));

        List<Adventurer> adventurersList = Adventurer.creationListAdventurers(input);
        assertEquals(adventurerListOut.toString(),adventurersList.toString());
    }
    @Test
    public void testAdventurerLoopMovementSequence(){
        List<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(new Adventurer("A","Lara", 1, 1, "S", "AAA", 0));
        adventurers.add(new Adventurer("A","Alex", 2, 2, "S", "GGGGGGGGGG", 0));
        adventurers.add(new Adventurer("A","India", 3, 3, "S", "DDDDD", 0));
        String result = Adventurer.loopAdventurersInOrderOfTheirMovementSequence(adventurers);

        assertEquals("G", result);
    }

    @Test
    public void testAdventurerFindBiggestMovementSequenceInNumber(){
        List<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(new Adventurer("A","Lara", 1, 1, "S", "AAA", 0));
        adventurers.add(new Adventurer("A","Alex", 2, 2, "S", "GGGG", 0));
        adventurers.add(new Adventurer("A","India", 3, 3, "S", "DDDDD", 0));
        int result = Adventurer.adventurersBiggestMovementSequenceNumber(adventurers);

        assertEquals(5, result);
    }

    @Test
    public void testMoveForwardFromN(){
        testAdventurer.setOrientation("N");
        testAdventurer.moveForward();
        assertEquals(1, testAdventurer.getVerticalAxe());

    }

    @Test
    public void testMoveForwardFromS(){
        testAdventurer.setOrientation("S");
        testAdventurer.moveForward();
        assertEquals(3, testAdventurer.getVerticalAxe());

    }

    @Test
    public void testMoveForwardFromE(){
        testAdventurer.setOrientation("E");
        testAdventurer.moveForward();
        assertEquals(2, testAdventurer.getHorizontalAxe());

    }

    @Test
    public void testMoveForwardFromO(){
        testAdventurer.setOrientation("O");
        testAdventurer.moveForward();
        assertEquals(0, testAdventurer.getHorizontalAxe());

    }
}