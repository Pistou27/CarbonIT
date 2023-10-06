package org.example;

import java.lang.annotation.ElementType;

public class ElementMap {

    private String icon;
    private int verticalAxe, horizontalAxe;

    public ElementMap(String icon, int horizontalAxe, int verticalAxe) {
        this.icon = icon;
        this.horizontalAxe = horizontalAxe;
        this.verticalAxe = verticalAxe;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getHorizontalAxe() {
        return this.horizontalAxe;
    }

    public void setHorizontalAxe(int horizontalAxe) {
        this.horizontalAxe = horizontalAxe;
    }

    public int getVerticalAxe() {
        return this.verticalAxe;
    }

    public void setVerticalAxe(int verticalAxe) {
        this.verticalAxe = verticalAxe;
    }

}
