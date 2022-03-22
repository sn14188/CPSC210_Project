// Work cited: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Represents avoid poop game
public class AvoidPoopGame {
    public static final int WIDTH = 9; // horizontal size of field
    public static final int HEIGHT = 9; // vertical size of field
    private int turn = 0; // turn count
    private boolean gameOver = false; // false until the game is over

    private Scanner input; // for user input

    private Player player; // variable declaration
    private ArrayList<Poop> poops; // list storing poop instances

    // Data persistence
    private static final String JSON_STORE = "./data/record.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private ScoreRecord scoreRecord;

    // EFFECTS: constructs a game
    public AvoidPoopGame() {
        scoreRecord = new ScoreRecord();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        startGame();
        while (!(gameOver)) {
            turnIncreaseAndPrint();
            addPoop();
            printPoops();
            printPlayer();
            movePlayer();
            movePoops();
        }

        printResults();
        saveScoreWithName();
        displayGameEndMenu();

        String command = input.next();
        command = command.toLowerCase();
        processCommand(command);
    }

    // EFFECTS: starts a game and instantiates player and poop list when pressing s key, otherwise terminates
    private void startGame() {
        boolean gameStarted = false;
        displayGameStartMenu();

        String command = null;
        input = new Scanner(System.in);

        while (gameStarted == false) {
            command = input.next();
            if (command.equals("s") || command.equals("S")) {
                gameStarted = true;
                player = new Player();
                poops = new ArrayList<>();
            } else if (command.equals("l") || command.equals("L")) {
                loadScoreRecord();
                for (Score s : scoreRecord.getScoreRecord()) {
                    System.out.println(s);
                }
                displayGameStartMenu();
            } else {
                System.out.println("Invalid key please select s or l");
            }
        }
    }

    // EFFECTS: displays options to user upon start
    private void displayGameStartMenu() {
        System.out.println("\nSelect:");
        System.out.println("\ts -> Start a game");
        System.out.println("\tl -> Load the score record from file");
    }

    // EFFECTS: displays options to user upon end
    private void displayGameEndMenu() {
        System.out.println("\nSelect:");
        System.out.println("\ts -> Save score to file and quit");
        System.out.println("\tAny other keys -> Quit without saving");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("s") || command.equals("S")) { // saves the records
            saveScoreRecord();
        }
        gameOver = true; // game ends
    }

    // MODIFIES: this
    // EFFECTS: increases and prints the turn count
    private void turnIncreaseAndPrint() {
        turn++;
        System.out.println(" *** Turn " + turn + " ***");
    }

    // MODIFIES: this
    // EFFECTS: adds a poop to the list
    private void addPoop() {
        poops.add(new Poop());
    }

    // EFFECTS: prints the list of poops' locations
    private void printPoops() {
        System.out.print("\tPoop: ");
        for (Poop poop : poops) {
            System.out.print(poop.getStringLocation() + " ");
        }
        System.out.println(); // for space
    }

    // EFFECTS: prints the player's location
    private void printPlayer() {
        System.out.println("\tPlayer: " + player.getStringLocation());
    }

    // MODIFIES: this, Poop
    // EFFECTS: moves poop
    //          if out of field, it's eliminated
    //          if one of poop's coordinates are same as players', the game is over
    private void movePoops() {
        Poop poopToRemove = null;
        for (Poop poop : poops) {
            poop.movePoop();
            if (poop.getPoopY() > HEIGHT) {
                poopToRemove = poop;
            }
            if (poop.getStringLocation().equals(player.getStringLocation())) {
                this.gameOver = true;
            }
        }
        poops.remove(poopToRemove);
    }

    // MODIFIES: this, Player
    // EFFECTS: asks the user for the next move
    //          moves accordingly or stays otherwise
    private void movePlayer() {
        System.out.println("\nPress L to move left, or R to move right.");
        String move = input.next();
        if (move.equals("L") || move.equals("l")) {
            player.moveLeft();
        } else if (move.equals("R") || move.equals("r")) {
            player.moveRight();
        }
    }

    // EFFECTS: prints the game result
    private void printResults() {
        System.out.println("Game over. You've been pooped.");
        System.out.println("- Your score: " + turn);
    }

    // EFFECTS: prompts user to input name and saves the score
    private void saveScoreWithName() {
        input = new Scanner(System.in);
        System.out.println("- Input your name: ");
        String name = input.next();
        scoreRecord.addScore(new Score(name, turn));
    }

    // EFFECTS: saves the score record to file
    private void saveScoreRecord() {
        try {
            jsonWriter.open();
            jsonWriter.write(scoreRecord);
            jsonWriter.close();
            System.out.println("Saved to: " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads score record from file
    private void loadScoreRecord() {
        try {
            scoreRecord = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
