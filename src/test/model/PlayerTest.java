package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    public Player player;

    @BeforeEach
    void setup() {
        player = new Player();
    }

    @Test
    void testMoveLeft() {
        // updates the player's x position to the left
        player.moveLeft();
        assertEquals(3, player.getPlayerX());

        // move 2 to the left
        player.moveLeft();
        player.moveLeft();
        assertEquals(1, player.getPlayerX());

        // boundary case: 1 to 0
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
        assertEquals(5, player.getPlayerX());

        // move 2 to the right
        player.moveRight();
        player.moveRight();
        assertEquals(7, player.getPlayerX());

        // boundary case: 8 to 9
        player.setPlayerX(8);
        player.moveRight();
        assertEquals(9, player.getPlayerX());

        // when x = 9, cannot go further to the right
        player.moveRight();
        assertEquals(9, player.getPlayerX());
    }

    @Test
    void testGetLocation() {
        assertEquals("(4, 9)", player.getLocation());
    }
}