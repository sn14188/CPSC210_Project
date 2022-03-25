package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Represents avoid poop game
public class AvoidPoopGame {
    public static final int WIDTH = 300; // horizontal size of field
    public static final int HEIGHT = 500; // vertical size of field

    private Player player;
    private ArrayList<Poop> poops;
    private int turnCount; // turn count
    private boolean gameOver = false; // false until the game is over
    private Scanner input;

    // Data persistence
    private ScoreRecord scoreRecord;
    private static final String JSON_STORE = "./data/record.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs a game
    public AvoidPoopGame() {
        scoreRecord = new ScoreRecord();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        player = new Player(250);
        poops = new ArrayList<>();
        turnCount = 0;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Poop> getPoops() {
        return poops;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    // MODIFIES: this
    // EFFECTS: updates the game state
    public void nextGameState() {
        movePoops();
        addPoop();
        turnCount++;
    }

    // MODIFIES: this, Poop
    // EFFECTS: moves poop
    //          if one of poop's coordinates are same as players', the game is over
    private void movePoops() {
        for (Poop poop : poops) {
            poop.movePoop();
            if (poop.getPoopX() == player.getPlayerX() && poop.getPoopY() == player.getPlayerY()) {
                gameOver = true;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a poop to the list
    private void addPoop() {
        poops.add(new Poop());
    }

    // MODIFIES: this
    // EFFECTS: take the key events and accepts accordingly
    public void keyEventHandling(int keyCode) {
        if (keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_LEFT) {
            player.moveLeft();
        } else if (keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_RIGHT) {
            player.moveRight();
        } else if (keyCode == KeyEvent.VK_S) {
            saveScoreRecord();
        } else if (keyCode == KeyEvent.VK_X) {
            System.exit(0);
        }
    }

    // EFFECTS: saves the score record to file
    public void saveScoreRecord() {
        saveScoreWithName();
        try {
            jsonWriter.open();
            jsonWriter.write(scoreRecord);
            jsonWriter.close();
            System.out.println("Saved to: " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        System.exit(0);
    }

    // EFFECTS: prompts user to input name and saves the score
    public void saveScoreWithName() {
        input = new Scanner(System.in);
        System.out.println("Input your name: ");
        String name = input.next();
        scoreRecord.addScore(new Score(name, turnCount));
    }

    // MODIFIES: this
    // EFFECTS: loads score record from file
    public void loadScoreRecord() {
        try {
            scoreRecord = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}