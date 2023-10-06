package org.example;

public class Montagne extends ElementMap{

    public Montagne(String icon, int horizontalAxe, int verticalAxe){
        super(icon, horizontalAxe, verticalAxe);
    }

    @Override
    public String toString(){
        return this.getIcon() + " " + this.getHorizontalAxe() + " " + this.getVerticalAxe();
    }

}
