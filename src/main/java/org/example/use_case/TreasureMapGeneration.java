package org.example.use_case;

import org.example.entity.Adventurer;
import org.example.entity.ElementMap;
import org.example.entity.Mountain;
import org.example.entity.Treasure;

import java.util.List;


public class TreasureMapGeneration {
    public static String creationOfPlainMap(String[] tokens){
            int larger = Integer.parseInt(tokens[1].trim());
            int longueur = Integer.parseInt(tokens[2].trim());

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < longueur; i++) {
                for (int j = 0; j < larger; j++) {
                    if (j != larger - 1) {
                        result.append(".").append(" ");
                    }
                    else {result.append(".");}
                }
                if (i != longueur - 1) {
                    result.append("\n");
                }
            }
            return result.toString().trim();
    }

    public static String concatenationOfLines(String[][] lines) {
        StringBuilder result = new StringBuilder();

        for (String[] array : lines) {
            result.append(String.join(" ", array)).append("\n");
        }
        if (!result.isEmpty()) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    public static String createMapWithElements(String input) {
        String resultMap = "";
        String[] lines = input.split("\n");
        for (String line : lines){
            String[] part = line.split("-");
            String icon = part[0].trim();
            switch (icon){
                case "C":
                    resultMap = creationOfPlainMap(part);
                    break;
                case "M":
                    Mountain mountain = new Mountain(icon, Integer.parseInt(part[1].trim()), Integer.parseInt(part[2].trim()));
                    resultMap = addElementMap(resultMap, mountain);
                    break;
                case "T":
                    Treasure tresor = new Treasure(icon , Integer.parseInt(part[1].trim()), Integer.parseInt(part[2].trim()), Integer.parseInt(part[3].trim()));
                    resultMap = addElementMap(resultMap, tresor);
                    break;
                case "A":
                    Adventurer adventurer = new Adventurer(icon, part[1].trim(), Integer.parseInt(part[2].trim()), Integer.parseInt(part[3].trim()), part[4].trim(), part[5].trim(), 0);
                    resultMap = addElementMap(resultMap, adventurer);
                    break;
                default:
                    System.out.println("Option non reconnue");
                    break;
            }
        }
        return resultMap;
    }

    public static String addElementMap(String map, ElementMap elementMap) {
        String[][] lines = readLineByLine(map);
        int horizontalAxe = elementMap.getHorizontalAxe();
        int verticalAxe = elementMap.getVerticalAxe();

        if(horizontalAxe >= 0 && horizontalAxe < lines[0].length && verticalAxe < lines.length){
            if (elementMap instanceof Mountain){
                lines[verticalAxe][horizontalAxe] = elementMap.getIcon();
            } else if (elementMap instanceof Treasure) {
                int nbreTreasure = ((Treasure) elementMap).getNbreTresor();
                if (nbreTreasure > 0) {
                    lines[verticalAxe][horizontalAxe] = "T(" + nbreTreasure + ")";
                }
            } else if (elementMap instanceof Adventurer) {
                lines[verticalAxe][horizontalAxe] = "A(" + ((Adventurer) elementMap).getName() + ")";
            }
        }
       return concatenationOfLines(lines);
    }

    public static String[][] readLineByLine(String map) {
        String[] lineOfMap = map.split("\n");
        String[][] lines = new String[lineOfMap.length][];
        for (int i = 0; i < lineOfMap.length; i++) {
            String[] elementLine = lineOfMap[i].split(" ");
            lines[i] = elementLine;
        }
        return lines;
    }

    public static String movementSequenceAdventurer(List<Adventurer> adventurers, List<Treasure> treasures, String map) {
        String[][] lines = readLineByLine(map);
        int biggestMovementSequenceLength = Adventurer.adventurersBiggestMovementSequenceNumber(adventurers);
        int itMvtSequence = 0;
        for (int i = 0; i < biggestMovementSequenceLength ; i++) {
            for (Adventurer adventurer : adventurers) {
                if (adventurer.getMovementSequence().length() > itMvtSequence) {
                    char instruction = adventurer.getMovementSequence().charAt(itMvtSequence);
                    int x = adventurer.getHorizontalAxe();
                    int y = adventurer.getVerticalAxe();
                    int nbreTresorposseder = adventurer.getNbrTreasuresOwned();
                    if (x >= 0 && x < lines[0].length && y >= 0 && y < lines.length) {
                        switch (instruction) {
                            case 'A':
                                adventurer.moveForward();
                                break;
                            case 'D':
                                adventurer.turnRight();
                                break;
                            case 'G':
                                adventurer.turnLeft();
                                break;
                        }
                        int newX = adventurer.getHorizontalAxe();
                        int newY = adventurer.getVerticalAxe();
                        if (newX >= 0 && newX < lines[0].length && newY >= 0 && newY < lines.length && (x != newX || y != newY)) {
                            switch (lines[newY][newX].charAt(0)) {
                                case 'M', 'A':
                                    lines[y][x] = "A(" + adventurer.getName() + ")";
                                    adventurer.setHorizontalAxe(x);
                                    adventurer.setVerticalAxe(y);
                                    break;
                                case 'T':
                                    int nbreTresor = Integer.parseInt(lines[newY][newX].substring(2, lines[newY][newX].length() - 1));
                                    for (Treasure treasure : treasures){
                                        if (treasure.getHorizontalAxe() == newX && treasure.getVerticalAxe() == newY && treasure.getNbreTresor() == nbreTresor){
                                            nbreTresor --;
                                            treasure.setNbreTresor(nbreTresor);
                                            nbreTresorposseder ++;
                                            adventurer.setNbrTreasuresOwned(nbreTresorposseder);
                                        }
                                    }
                                    break;
                            }
                        }else {
                            adventurer.setHorizontalAxe(x);
                            adventurer.setVerticalAxe(y);
                        }
                        lines[y][x] = ".";
                        x = newX;
                        y = newY;
                    }
                    updateMapWithTreasuresAndAdventurers(treasures, adventurers, lines);
                }
            }
            itMvtSequence ++;
        }
        return concatenationOfLines(lines);
    }

    public static String[][] updateMapWithTreasuresAndAdventurers(List<Treasure> treasures, List<Adventurer> adventurers, String[][] lines) {
        for (Treasure treasure : treasures){
            if (treasure.getNbreTresor() > 0) {
                lines[treasure.getVerticalAxe()][treasure.getHorizontalAxe()] = "T(" + treasure.getNbreTresor() + ")";
            }
            else {
                lines[treasure.getVerticalAxe()][treasure.getHorizontalAxe()] = ".";
            }
        }
        for (Adventurer adventurer : adventurers){
            if(lines[adventurer.getVerticalAxe()][adventurer.getHorizontalAxe()].startsWith("T")){
                lines[adventurer.getVerticalAxe()][adventurer.getHorizontalAxe()] = "A(" + adventurer.getName() + ")" + lines[adventurer.getVerticalAxe()][adventurer.getHorizontalAxe()];
            }
            else {
                lines[adventurer.getVerticalAxe()][adventurer.getHorizontalAxe()] = "A(" + adventurer.getName() + ")";
            }
        }
        return lines;
    }
}
