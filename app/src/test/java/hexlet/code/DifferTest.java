package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    public void testSimpleJson() throws Exception {
        String correctAnswer = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        String result = Differ.generate(
                Path.of("src/test/resources/json1.json"),
                Path.of("src/test/resources/json2.json")
        );
        assertEquals(correctAnswer, result);
    }

    @Test
    public void testSimpleYaml() throws Exception {
        String correctAnswer = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        String result = Differ.generate(
                Path.of("src/test/resources/yml1.yml"),
                Path.of("src/test/resources/yml2.yml")
        );
        assertEquals(correctAnswer, result);
    }
}
