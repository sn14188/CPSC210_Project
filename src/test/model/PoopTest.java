package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PoopTest {
    public Poop poop;
    public int poopX;
    public int poopY;

    @BeforeEach
    void setup() {
        poop = new Poop();
        poopX = poop.getPoopX();
        poopY = poop.getPoopY();
    }

    @Test
    void movePoop() {
        // move poop's position by DY units
        poop.movePoop();
        assertEquals(poopX, poop.getPoopX());
        assertEquals(poopY, poop.getPoopY() - 1);

        // move 2 times
        poop.movePoop();
        poop.movePoop();
        assertEquals(poopX, poop.getPoopX());
        assertEquals(poopY, poop.getPoopY() - 3);
    }

    @Test
    void getLocation() {
        assertEquals("(" + poop.getPoopX() + ", " + poop.getPoopY() + ")",
                poop.getLocation());
    }
}