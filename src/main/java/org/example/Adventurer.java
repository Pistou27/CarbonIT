package org.example;

public class Adventurer extends ElementMap{
    private String name, orientation ,movementSequence;
    private int nbrTreasuresOwned;

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

    public Adventurer(String icon, String name, int horizontalAxe, int verticalAxe, String orientation, String movementSequence, int nbrTreasuresOwned){
        super(icon, horizontalAxe, verticalAxe);
        this.name = name;
        this.orientation = orientation;
        this.movementSequence = movementSequence;
        this.nbrTreasuresOwned = nbrTreasuresOwned;
    }

    @Override
    public String toString(){
        return this.getIcon() + " " + this.name + " " + this.getHorizontalAxe() + " " + this.getVerticalAxe() + " " + this.orientation + " " + this.movementSequence + " " + this.nbrTreasuresOwned;
    }

}
