package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

// Represents score records having a collection of scores
public class ScoreRecord {
    private String name;
    private List<Score> scores;

    // EFFECTS: constructs a score record with score values
    public ScoreRecord(String name) {
        this.name = name;
        scores = new ArrayList<>();
    }

    public List<Score> getScoreRecord() {
        return Collections.unmodifiableList(scores);
    }

    // MODIFIES: this
    // EFFECTS: adds a score to the list
    public void addScore(Score score) {
        EventLog.getInstance().logEvent(new Event("Added score: " + score.getName() + " " + score.getScore()));
        scores.add(score);

    }

    // EFFECTS: returns this as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
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
