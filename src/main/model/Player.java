package model;

import ui.APGame;

public class Player {
    // Constants: player's y coordinates = 0
    // Changing information: player's x coordinates
    private int playerX;
    private int playerY;

    // Constructs a player
    // EFFECTS: a player is positioned at coordinates (x, y)
    public Player() {
        this.playerX = 4;
        this.playerY = 9;
    }

    // EFFECTS: returns player's x coordinates
    public int getPlayerX() {
        return playerX;
    }

    // EFFECTS: returns player's y coordinates
    public int getPlayerY() {
        return playerY;
    }

    // MODIFIES: this
    // EFFECTS: sets player's x coordinates
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
        if (playerX >= APGame.WIDTH) {
            playerX = APGame.WIDTH;
        } else {
            this.setPlayerX(this.playerX + 1);
        }
    }

    // EFFECTS: returns player's coordinates
    public String getLocation() {
        return "(" + this.getPlayerX() + ", " + this.getPlayerY() + ")";
    }
}