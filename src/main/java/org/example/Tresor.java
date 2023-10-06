package org.example;

public class Tresor extends ElementMap{

    private int nbreTresor;

    public Tresor(String icon, int horizontalAxe, int verticalAxe, int nbreTreasor){
        super(icon, horizontalAxe, verticalAxe);
        this.nbreTresor = nbreTreasor;
    }

    @Override
    public String toString(){
        return this.getIcon() + " " + this.getHorizontalAxe() + " " + this.getVerticalAxe() + " " + this.nbreTresor;
    }

    public int getNbreTresor() {
        return nbreTresor;
    }

    public void setNbreTresor(int nbreTresor) {
        this.nbreTresor = nbreTresor;
    }
}
