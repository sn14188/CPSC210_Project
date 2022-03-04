package model;

import ui.AvoidPoopGame;

public class Player {
    // Constants: player's y coordinates = 0
    // Changing information: player's x coordinates
    private int playerX;
    private int playerY;

    // Constructs a player
    // EFFECTS: a player is positioned at coordinates (4, 9)
    public Player() {
        this.playerX = 4;
        this.playerY = 9;
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
            this.setPlayerX(this.playerX - 1);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the player's x position to the right
    public void moveRight() {
        if (playerX >= AvoidPoopGame.WIDTH) {
            playerX = AvoidPoopGame.WIDTH;
        } else {
            this.setPlayerX(this.playerX + 1);
        }
    }

    // EFFECTS: returns string representation of player's coordinates
    public String getStringLocation() {
        return "(" + this.getPlayerX() + ", " + this.getPlayerY() + ")";
    }
}