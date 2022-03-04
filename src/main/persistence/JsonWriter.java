// Work cited: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

package persistence;

import model.ScoreRecord;
import org.json.JSONObject;

import java.io.*;

public class JsonWriter {
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer
    public void open() throws FileNotFoundException {
        writer = new PrintWriter((destination));
    }

    public void write(ScoreRecord scoreRecord) {
        JSONObject json = scoreRecord.toJson();
        saveToFile(json.toString(4));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }
}