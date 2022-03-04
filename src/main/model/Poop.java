package model;

import java.util.Random;

public class Poop {
    // Constants: DY
    // Changing information: poop's coordinates
    private static final int DY = 1;
    private int poopX;
    private int poopY;

    // Constructs a poop
    // EFFECTS: a poop is positioned at coordinates (x, y)
    public Poop() {
        Random rand = new Random();
        this.poopX = rand.nextInt(10);
        this.poopY = 0;
    }

    public int getPoopX() {
        return poopX;
    }

    public int getPoopY() {
        return poopY;
    }

    public void setPoopY(int poopY) {
        this.poopY = poopY;
    }

    // MODIFIES: this
    // EFFECTS: move poop's position by DY units
    public void movePoop() {
        this.setPoopY(this.getPoopY() + DY);
    }

    // EFFECTS: returns string representation of poop's coordinates
    public String getStringLocation() {
        return "(" + this.getPoopX() + ", " + this.getPoopY() + ")";
    }
}