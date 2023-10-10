package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Treasure extends ElementMap{

    private int nbreTresor;

    public Treasure(){

    }
    public Treasure(String icon, int horizontalAxe, int verticalAxe, int nbreTreasor){
        super(icon, horizontalAxe, verticalAxe);
        this.nbreTresor = nbreTreasor;
    }

    @Override
    public String toString(){
        return this.getIcon() + " - " + this.getHorizontalAxe() + " - " + this.getVerticalAxe() + " - " + this.nbreTresor;
    }

    public static List<Treasure> creationListTreasure(String input) {
        List<Treasure> treasures = new ArrayList<>();
        String[] lines = input.split("\n");
        String[] partCartography = lines[0].trim().split(" - ");
        int xMap = Integer.parseInt(partCartography[1].trim());
        int yMap = Integer.parseInt(partCartography[2].trim());

        for (String line : lines) {
            String[] part = line.trim().split(" - ");
            if (part[0].startsWith("T") && part.length == 4 && (Integer.parseInt(part[1].trim()) < xMap && Integer.parseInt(part[2].trim()) < yMap)) {
                Treasure treasure = new Treasure(part[0].trim(), Integer.parseInt(part[1].trim()), Integer.parseInt(part[2].trim()),Integer.parseInt(part[3].trim()));
                treasures.add(treasure);
            }
        }
        return treasures;
    }
    public int getNbreTresor() {
        return nbreTresor;
    }

    public void setNbreTresor(int nbreTresor) {
        this.nbreTresor = nbreTresor;
    }
}
