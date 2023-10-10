package org.example.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TreasureTest {

    @Test
    void testToString() {
        Treasure treasure = new Treasure("T", 2, 3, 12);
        assertEquals("T - 2 - 3 - 12", treasure.toString());
    }

    @Test
    public void testTreasuresMustBeToBeInsideAList() {
        String input = """
                C - 3 - 4
                T - 0 - 3 - 2
                T - 1 - 3 - 3
                T - 1 - 40000000 - 3
                A - Lara - 1 - 1 - S - AADADAGGA
                A - Alex - 2 - 2 - S - AADADAGGA
                A - India - 3 - 3 - S - AADADAGGA""";
        List<Treasure> treasuresListOut = new ArrayList<>();
        treasuresListOut.add(new Treasure("T", 0, 3, 2));
        treasuresListOut.add(new Treasure("T", 1, 3, 3));
        List<Treasure> treasureList = Treasure.creationListTreasure(input);
        assertEquals(treasureList.toString(),treasuresListOut.toString());
    }

}
