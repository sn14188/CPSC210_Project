package ui;

import model.Player;
import model.Poop;
import java.util.ArrayList;
import java.util.Scanner;

// Represents avoid poop game
public class APGame {
    public static final int WIDTH = 9; // Horizontal size of field
    public static final int HEIGHT = 9; // Vertical size of field

    private int turn = 0; // turn count
    private boolean isGameOver = false; // false until the player's hit by poop

    private Scanner input; // for user input

    private Player player; // variable declaration
    private ArrayList<Poop> poops; // list storing poop instances

    // EFFECTS: constructs a game
    public APGame() {
        startGame();
        while (!(isGameOver)) {
            turnIncrease();
            addPoop();
            printPoops();
            printPlayer();
            movePoops();
            movePlayer();
        }
        printResults();
    }

    // EFFECTS: starts a game and instantiates player and poop list when pressing s key
    //          otherwise terminates
    private void startGame() {
        Boolean gameStarted = false;
        System.out.println("Press S to start game.");

        while (gameStarted == false) {
            input = new Scanner(System.in);
            String start = input.next();
            if (start.equals("S") || start.equals("s")) {
                gameStarted = true;
                player = new Player();
                poops = new ArrayList<Poop>();
            } else {
                System.out.println("Invalid key. Please press S to start game.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: increases and prints the turn count
    private void turnIncrease() {
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
        System.out.print("Poop: ");
        for (Poop poop : poops) {
            System.out.print(poop.getLocation() + " ");
        }
    }

    // EFFECTS: prints the player's location
    private void printPlayer() {
        System.out.println(); // for space
        System.out.println("Player: " + player.getLocation());
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
            if (poop.getLocation().equals(player.getLocation())) {
                this.isGameOver = true;
            }
        }
        poops.remove(poopToRemove);
    }

    // MODIFIES: this, Player
    // EFFECTS: asks the user for the next move
    //          moves accordingly or stays otherwise
    private void movePlayer() {
        System.out.println();
        System.out.println("Press L to move left, or R to move right.");
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
        System.out.println("Your score: " + turn);
    }
}