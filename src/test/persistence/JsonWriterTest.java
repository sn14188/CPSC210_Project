// Work cited: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

package persistence;

import model.Score;
import model.ScoreRecord;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ScoreRecord scoreRecord = new ScoreRecord();
            JsonWriter writer = new JsonWriter("./data/a\0impossible:fileName.json");
            writer.open();
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriteEmptyScoreRecord() {
        try {
            ScoreRecord scoreRecord = new ScoreRecord();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyScoreRecord.json");
            writer.open();
            writer.write(scoreRecord);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyScoreRecord.json");
            scoreRecord = reader.read();
            assertEquals(0, scoreRecord.getScoreRecords().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriteGeneralScoreRecord() {
        try {
            ScoreRecord scoreRecord = new ScoreRecord();
            scoreRecord.addScore(new Score("Kelly", 10));
            scoreRecord.addScore(new Score("Rena", 5));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralScoreRecord.json");
            writer.open();
            writer.write(scoreRecord);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralScoreRecord.json");
            scoreRecord = reader.read();
            List<Score> scores = scoreRecord.getScoreRecords();
            assertEquals(2, scores.size());
            checkScore("Kelly", 10, scores.get(0));
            checkScore("Rena", 5, scores.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}