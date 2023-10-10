package org.example.entity;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MountainTest {

    @Test
    void testToString() {
        Mountain mountain = new Mountain("M", 2, 3);
        assertEquals("M - 2 - 3", mountain.toString());
    }

    @Test
    public void testMountainMustBeToBeInsideAList() {
        String input = """
                C - 3 - 4
                M - 1 - 0
                M - 2 - 1
                M - 12 - 1
                T - 0 - 3 - 2
                T - 1 - 3 - 3
                A - Lara - 1 - 1 - S - AADADAGGA
                A - Alex - 2 - 2 - S - AADADAGGA
                A - India - 3 - 3 - S - AADADAGGA""";
        List<Mountain> mountainsListOut = new ArrayList<>();
        mountainsListOut.add(new Mountain("M", 1, 0));
        mountainsListOut.add(new Mountain("M", 2, 1));
        List<Mountain> mountainList = Mountain.creationListMountain(input);
        assertEquals(mountainList.toString(),mountainsListOut.toString());
    }
}
