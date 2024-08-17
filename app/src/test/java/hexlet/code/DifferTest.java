package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    public void testSimpleJson() throws Exception {
        String correctAnswer = "{\n" +
                "  - follow: false\n" +
                "    host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "  + timeout: 20\n" +
                "  + verbose: true\n" +
                "}";
        String result = Differ.generate(
                Path.of("src/test/resources/json1.json"),
                Path.of("src/test/resources/json2.json")
        );
        assertEquals(correctAnswer, result);
    }
}
