package org.example;

import org.example.entity.Adventurer;
import org.example.entity.Mountain;
import org.example.entity.Treasure;
import org.example.use_case.TreasureMapGeneration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.regex.Pattern;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        String filePath = "fichierEntree.txt";
        String filePathOutput = "fichierSortie.txt";
        StringBuilder entry = new StringBuilder();
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                entry.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Erreur d'accès au fichier d'entrée : " + e.getMessage());
        }

        String input = entry.toString();
        String validateInput = validateInput(input);
        String cartography = cartographyExtract(validateInput);
        String resultMap;

        if (cartography.isEmpty()){
            resultMap = "Cartographie non présente erreur dans les données d'entrées :(";
        }
        else {
            List<Adventurer> adventurers = Adventurer.creationListAdventurers(validateInput);
            List<Treasure> treasures = Treasure.creationListTreasure(validateInput);
            List<Mountain> mountains = Mountain.creationListMountain(validateInput);

            String map = TreasureMapGeneration.createMapWithElements(validateInput);

            resultMap = TreasureMapGeneration.movementSequenceAdventurer(adventurers, treasures, map);

            resultMap = addCartographyMountainAdventurerAndTreasureIntoOutput(adventurers, treasures, mountains, cartography, resultMap);
        }

        try {
            Path file = Path.of(filePathOutput);
            Files.write(file, resultMap.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            System.out.println("Écriture dans le fichier terminée.");
        } catch (IOException e) {
            System.err.println("Erreur d'accès au fichier de sortie : " + e.getMessage());
        }
    }

    public static String cartographyExtract(String validateInput) {
        String[] lines = validateInput.split("\n");
        if (lines[0].trim().startsWith("C")) {
            return lines[0];
        } else {
            return "";
        }
    }

    public static String validateInput(String input) {
        StringBuilder validationResult = new StringBuilder();
        String cartographyLine = "";
        boolean cartographyUnique = false;
        String cartographyPattern = "^C - \\d+ - \\d+$";
        String mountainPattern = "^M - \\d+ - \\d+$";
        String treasurePattern = "^T - \\d+ - \\d+ - \\d+$";
        String adventurerPattern = "^A - [a-zA-Z]+ - \\d+ - \\d+ - [NSEO] - [ADG]+$";

        String[] lines = input.split("\n");

        for (String line : lines) {
            if (Pattern.matches(cartographyPattern, line) && !cartographyUnique) {
                cartographyLine = line;
                cartographyUnique = true;
            } else if (Pattern.matches(mountainPattern, line)) {
                validationResult.append(line).append("\n");
            } else if (Pattern.matches(treasurePattern, line)) {
                validationResult.append(line).append("\n");
            } else if (Pattern.matches(adventurerPattern, line)) {
                validationResult.append(line).append("\n");
            } else {
                System.out.println("Ligne non valide : " + line);
            }
        }
        validationResult.insert(0, cartographyLine + "\n");
        return validationResult.toString().trim();
    }

    public static String addCartographyMountainAdventurerAndTreasureIntoOutput(List<Adventurer> adventurers, List<Treasure> treasures, List<Mountain> mountains, String cartography, String resultMap) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append(cartography).append("\n");
        for (Mountain mountain : mountains){
            outputBuilder.append(mountain).append("\n");
        }
        String explainationTreasure = "# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}";
        String explainationAdventurer =  "{A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}";
        outputBuilder.append(explainationTreasure).append("\n");
        for (Treasure treasure : treasures){
            outputBuilder.append(treasure).append("\n");
        }
        outputBuilder.append(explainationAdventurer).append("\n");
        for (Adventurer adventurer : adventurers){
            outputBuilder.append(adventurer).append("\n");
        }
        outputBuilder.append("\n").append(resultMap);
        return outputBuilder.toString();
    }
}