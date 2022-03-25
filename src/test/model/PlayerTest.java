package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    public Player player;

    @BeforeEach
    void setup() {
        player = new Player(250);
    }

    @Test
    void testMoveLeft() {
        // updates the player's x position to the left
        player.moveLeft();
        assertEquals(240, player.getPlayerX());

        // move 2 to the left
        player.moveLeft();
        player.moveLeft();
        assertEquals(220, player.getPlayerX());

        // boundary case: 1 to 0
        player.setPlayerX(10);
        player.moveLeft();
        assertEquals(0, player.getPlayerX());

        // when x = 0, cannot go further to the left
        player.moveLeft();
        assertEquals(0, player.getPlayerX());
    }

    @Test
    void testMoveRight() {
        // updates the player's x position to the right
        player.moveRight();
        assertEquals(260, player.getPlayerX());

        // move 2 to the right
        player.moveRight();
        player.moveRight();
        assertEquals(280, player.getPlayerX());

        // boundary case: 290 to 300
        player.setPlayerX(290);
        player.moveRight();
        assertEquals(300, player.getPlayerX());

        // when x = 9, cannot go further to the right
        player.moveRight();
        assertEquals(300, player.getPlayerX());
    }

    @Test
    void testGetStringLocation() {
        // returns player's coordinates
        assertEquals("(250, 470)", player.getStringLocation());
    }
}