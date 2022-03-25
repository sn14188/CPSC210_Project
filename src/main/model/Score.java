package model;

import org.json.JSONObject;

// Represents a score
public class Score {
    private String name;
    private int score;

    // EFFECTS: constructs a score with a name and score
    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    // EFFECTS: returns string representation of this score
    public String toString() {
        return name + ": " + score;
    }

    // EFFECTS: returns this as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("score", score);
        return json;
    }
}

