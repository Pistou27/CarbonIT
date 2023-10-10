package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Mountain extends ElementMap{

    public Mountain(String icon, int horizontalAxe, int verticalAxe){
        super(icon, horizontalAxe, verticalAxe);
    }

    @Override
    public String toString(){
        return this.getIcon() + " - " + this.getHorizontalAxe() + " - " + this.getVerticalAxe();
    }

    public static List<Mountain> creationListMountain(String input) {
        List<Mountain> mountains = new ArrayList<>();
        String[] lines = input.split("\n");
        String[] partCartography = lines[0].trim().split(" - ");
        int xMap = Integer.parseInt(partCartography[1].trim());
        int yMap = Integer.parseInt(partCartography[2].trim());

        for (String line : lines) {
            String[] part = line.trim().split(" - ");
            if (part[0].startsWith("M") && part.length == 3 && (Integer.parseInt(part[1].trim()) < xMap && Integer.parseInt(part[2].trim()) < yMap)) {
                Mountain mountain = new Mountain(part[0].trim(), Integer.parseInt(part[1].trim()), Integer.parseInt(part[2].trim()));
                mountains.add(mountain);
            }
        }
        return mountains;
    }

}
