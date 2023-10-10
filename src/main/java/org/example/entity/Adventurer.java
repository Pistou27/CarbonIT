package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Adventurer extends ElementMap{
    private String name, orientation ,movementSequence;
    private int nbrTreasuresOwned;

    public static List<Adventurer> creationListAdventurers(String input) {
        List<Adventurer> adventurers = new ArrayList<>();

        String[] lines = input.split("\n");
        String[] partCartography = lines[0].trim().split(" - ");
        int xMap = Integer.parseInt(partCartography[1].trim());
        int yMap = Integer.parseInt(partCartography[2].trim());

        for (String line : lines) {
            String[] part = line.trim().split(" - ");
            if (part[0].startsWith("A") && part.length == 6 && (Integer.parseInt(part[2].trim()) < xMap && Integer.parseInt(part[3].trim()) < yMap)) {
                Adventurer adventurer = new Adventurer(part[0].trim(), part[1].trim(), Integer.parseInt(part[2].trim()), Integer.parseInt(part[3].trim()), part[4].trim(), part[5].trim(), 0);
                adventurers.add(adventurer);
            }
        }
        return adventurers;
    }

    public static String loopAdventurersInOrderOfTheirMovementSequence(List<Adventurer> adventurers) {
        int itMvtSequence = 0;
        int biggestMovementSequence = adventurersBiggestMovementSequenceNumber(adventurers);
        char mv = 0;
        for (int i = 0; i < biggestMovementSequence ; i++){
            for (Adventurer adventurer : adventurers) {
                if (adventurer.getMovementSequence().length() > itMvtSequence) {
                    mv = adventurer.getMovementSequence().charAt(itMvtSequence);
                }
            }
            itMvtSequence ++;
        }
        return String.valueOf(mv);
    }

    public static int adventurersBiggestMovementSequenceNumber(List<Adventurer> adventurers) {
        int biggestMovementSequence = 0;
        for (Adventurer adventurer : adventurers) {
            int newBiggestMovementSequence = adventurer.getMovementSequence().length();
            if (newBiggestMovementSequence > biggestMovementSequence){
                biggestMovementSequence = newBiggestMovementSequence;
            }
        }
        return biggestMovementSequence;
    }

    public String getOrientation() {
        return this.orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getNbrTreasuresOwned() {
        return this.nbrTreasuresOwned;
    }
    public void setNbrTreasuresOwned(int montresorRamses) {
        this.nbrTreasuresOwned = montresorRamses;
    }

    public String getMovementSequence() {
        return this.movementSequence;
    }

    public void setMovementSequence(String movementSequence) {
        this.movementSequence = movementSequence;
    }

    public Adventurer() {

    }
    public Adventurer(String icon, String name, int horizontalAxe, int verticalAxe, String orientation, String movementSequence, int nbrTreasuresOwned){
        super(icon, horizontalAxe, verticalAxe);
        this.name = name;
        this.orientation = orientation;
        this.movementSequence = movementSequence;
        this.nbrTreasuresOwned = nbrTreasuresOwned;
    }

    @Override
    public String toString(){
        return this.getIcon() + " - " + this.name + " - " + this.getHorizontalAxe() + " - " + this.getVerticalAxe() + " - " + this.orientation + " - " + this.nbrTreasuresOwned;
    }

    public void turnRight() {
        switch (orientation) {
            case "N":
                orientation = "E";
                break;
            case "E":
                orientation = "S";
                break;
            case "S":
                orientation = "O";
                break;
            case "O":
                orientation = "N";
                break;
        }
    }

    public void turnLeft() {
        switch (orientation) {
            case "N":
                orientation = "O";
                break;
            case "O":
                orientation = "S";
                break;
            case "S":
                orientation = "E";
                break;
            case "E":
                orientation = "N";
                break;
        }
    }
    public void moveForward() {
        int x = getVerticalAxe();
        int y = getHorizontalAxe();
        switch (orientation) {
            case "N":
                x--;
                setVerticalAxe(x);
                break;
            case "S":
                x++;
                setVerticalAxe(x);
                break;
            case "O":
                y--;
                setHorizontalAxe(y);
                break;
            case "E":
                y++;
                setHorizontalAxe(y);
                break;
        }
    }
}
