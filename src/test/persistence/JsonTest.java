package persistence;

import model.Score;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkScore(String name, int score, Score s) {
        assertEquals(name, s.getName());
        assertEquals(score, s.getScore());
    }
}