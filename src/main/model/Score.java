// Work cited: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

package model;

import org.json.JSONObject;

// Represents a score
public class Score {
    private String name;
    private int scoreValue;

    // EFFECTS: constructs a score with a name and score
    public Score(String name, int score) {
        this.name = name;
        this.scoreValue = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return scoreValue;
    }

    // EFFECTS: returns string representation of this score
    public String toString() {
        return name + ": " + scoreValue;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("score", scoreValue);
        return json;
    }
}

