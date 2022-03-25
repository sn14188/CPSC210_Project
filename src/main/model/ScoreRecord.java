package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// Represents score records
public class ScoreRecord {
    private ArrayList<Score> scores;

    // EFFECTS: constructs a score record with score values
    public ScoreRecord() {
        scores = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a score to the list
    public void addScore(Score score) {
        scores.add(score);
    }

    public ArrayList<Score> getScoreRecord() {
        return scores;
    }

    // EFFECTS: returns this as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Scores", scoreRecordToJson());
        return json;
    }

    // EFFECTS: returns scores as JSON array
    public JSONArray scoreRecordToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Score score : scores) {
            jsonArray.put(score.toJson());
        }
        return jsonArray;
    }
}
