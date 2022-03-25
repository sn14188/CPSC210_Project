package model;

import ui.AvoidPoopGame;

import java.awt.*;
import java.util.Random;

public class Poop {
    // Constants: DY
    // Changing information: poop's coordinates
    public static final int POOP_DY = 20;
    private int poopX;
    private int poopY;

    public static final int POOP_SIZE_X = 10;
    public static final int POOP_SIZE_Y = 20;
    public static final Color COLOR = new Color(150, 50, 0);

    // Constructs a poop
    // EFFECTS: a poop is positioned at coordinates (x, 10)
    public Poop() {
        Random rand = new Random();
        this.poopX = rand.nextInt(AvoidPoopGame.WIDTH / POOP_SIZE_X) * POOP_SIZE_X;
        this.poopY = 10;
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
        this.setPoopY(this.getPoopY() + POOP_DY);
    }

    // EFFECTS: returns string representation of poop's coordinates
    public String getStringLocation() {
        return "(" + this.getPoopX() + ", " + this.getPoopY() + ")";
    }
}