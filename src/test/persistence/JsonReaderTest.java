// Work cited: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

package persistence;

import model.ScoreRecord;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ScoreRecord scoreRecord = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderScoreRecord() {
        JsonReader reader = new JsonReader("./data/testReaderScoreRecord.json");
        try {
            ScoreRecord scoreRecord = reader.read();
            assertEquals("Aiden", scoreRecord.getScoreRecord().get(0).getName());
            assertEquals(100, scoreRecord.getScoreRecord().get(0).getScore());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}