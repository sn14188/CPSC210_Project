package model;

import ui.AvoidPoopGame;

import java.awt.*;

public class Player {
    // Constants: player's y coordinates
    // Changing information: player's x coordinates
    private int playerX;
    private int playerY;

    public static final int PLAYER_SIZE_X = 20;
    public static final int PLAYER_SIZE_Y = 20;
    public static final Color COLOR = new Color(0, 100, 200);

    // Constructs a player
    // EFFECTS: a player is positioned at coordinates
    public Player(int playerX) {
        this.playerX = playerX;
        this.playerY = AvoidPoopGame.HEIGHT - 30;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    // MODIFIES: this
    // EFFECTS: updates the player's x position to the left
    public void moveLeft() {
        if (playerX <= 0) {
            playerX = 0;
        } else {
            EventLog.getInstance().logEvent(new Event("Player moved left."));
            this.setPlayerX(this.playerX - 10);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the player's x position to the right
    public void moveRight() {
        if (playerX >= AvoidPoopGame.WIDTH) {
            playerX = AvoidPoopGame.WIDTH;
        } else {
            EventLog.getInstance().logEvent(new Event("Player moved right."));
            this.setPlayerX(this.playerX + 10);
        }
    }

    // EFFECTS: returns string representation of player's coordinates
    public String getStringLocation() {
        return "(" + this.getPlayerX() + ", " + this.getPlayerY() + ")";
    }
}