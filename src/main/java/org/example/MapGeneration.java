package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MapGeneration {

    public static String simpleGeneration(String input) {
        if(input.isEmpty()){
            return "Pas de donnees d'entrees";
        }
        else{
            return """
                . M .
                . A(Lara) M
                . . .
                T(2) T(3) .
                """;
        }
    }

    public static String creationDeFlatMap(String[] tokens){
        if (tokens.length == 3) {
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
            return result.toString();
        } else {
            return "Format d'entrée invalide";
        }
    }

    public static String creationDeCarte(String input) {
        String resultMap = "";
        String[] lines = input.split("\n");
        for (String line : lines){
            String[] part = line.split("-");
            String icon = part[0].trim();
            switch (icon){
                case "C":
                    resultMap = creationDeFlatMap(part);
                    break;
                case "M":
                    Montagne montagne = new Montagne(icon, Integer.parseInt(part[1].trim()), Integer.parseInt(part[2].trim()));
                    resultMap = ajoutElementMap(resultMap, montagne);
                    break;
                case "T":
                    Tresor tresor = new Tresor(icon , Integer.parseInt(part[1].trim()), Integer.parseInt(part[2].trim()), Integer.parseInt(part[3].trim()));
                    resultMap = ajoutElementMap(resultMap, tresor);
                    break;
                case "A":
                    Adventurer adventurer = new Adventurer(icon, part[1].trim(), Integer.parseInt(part[2].trim()), Integer.parseInt(part[3].trim()), part[4].trim(), part[5].trim(), 0);
                    System.out.println(adventurer.getOrientation());
                    resultMap = ajoutElementMap(resultMap, adventurer);
                    break;
                default:
                    System.out.println("Option non reconnue");
                    break;
            }
        }
        return resultMap;
    }

    public static String ajoutElementMap(String map, ElementMap elementMap) {
        List<String[]>lines = modificationMapEnListeSansEspace(map);
        int horizontalAxe = elementMap.getHorizontalAxe();
        int verticalAxe = elementMap.getVerticalAxe();

        if(horizontalAxe >= 0 && horizontalAxe < lines.get(0).length && verticalAxe < lines.size()){
            String[] modificationLine = lines.get(verticalAxe);
            if (elementMap instanceof Montagne){
                modificationLine[horizontalAxe] = elementMap.getIcon();
            } else if (elementMap instanceof Tresor) {
                int nbreTresor = ((Tresor) elementMap).getNbreTresor();
                if (nbreTresor > 0) {
                    modificationLine[horizontalAxe] = "T(" + nbreTresor + ")";
                }
            } else if (elementMap instanceof Adventurer) {
                modificationLine[horizontalAxe] = "A(" + ((Adventurer) elementMap).getName() + ")";
            }
            lines.set(verticalAxe, modificationLine);
        }
        return lines.stream()
                .map(array -> String.join(" ", array))
                .collect(Collectors.joining("\n"));
    }

    private static List<String[]> modificationMapEnListeSansEspace(String map) {
        String[] ligneDeCarte = map.split("\n");
        List<String[]> lines = new ArrayList<>();
        for (String e : ligneDeCarte){
            String[] elementLine = e.split(" ");
            lines.add(elementLine);
        }
        return lines;
    }
}
