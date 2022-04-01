package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads score record from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads score record from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ScoreRecord read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseScoreRecord(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses score record from JSON object and returns it
    private ScoreRecord parseScoreRecord(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ScoreRecord scoreRecord = new ScoreRecord(name);
        addScoreRecord(scoreRecord, jsonObject);
        return scoreRecord;
    }

    // MODIFIES: scoreRecord
    // EFFECTS: parses from JSON object and adds them to score record
    private void addScoreRecord(ScoreRecord scoreRecord, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Scores");
        for (Object json : jsonArray) {
            JSONObject nextScore = (JSONObject) json;
            addScore(scoreRecord, nextScore);
        }
    }

    // MODIFIES: scoreRecord
    // EFFECTS: parses from JSON object and adds it to score record
    private void addScore(ScoreRecord scoreRecord, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int scoreValue = jsonObject.getInt("score");
        Score score = new Score(name, scoreValue);
        scoreRecord.addScore(score);
    }
}
