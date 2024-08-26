package hexlet.code;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class DifferTest {

    @ParameterizedTest
    @CsvSource(
            value = {
                "src/test/resources/json/test1/expected.txt;"
                    + "src/test/resources/json/test1/json1.json;"
                    + "src/test/resources/json/test1/json2.json;"
                    + "stylish",
                "src/test/resources/json/test2/stylish.txt;"
                    + "src/test/resources/json/test2/json1.json;"
                    + "src/test/resources/json/test2/json2.json;"
                    + "stylish",
                "src/test/resources/yaml/test1/expected.txt;"
                    + "src/test/resources/yaml/test1/yml1.yml;"
                    + "src/test/resources/yaml/test1/yml2.yml;"
                    + "stylish",
                "src/test/resources/json/test2/plain.txt;"
                        + "src/test/resources/json/test2/json1.json;"
                        + "src/test/resources/json/test2/json2.json;"
                        + "plain",
                "src/test/resources/json/test2/json.json;"
                    + "src/test/resources/json/test2/json1.json;"
                    + "src/test/resources/json/test2/json2.json;"
                    + "json"
            },
            delimiter = ';')
    public void test(String expectedPath, String path1, String path2, String format) throws Exception {
        String expected = Files.readString(Path.of(expectedPath).toAbsolutePath().normalize());
        assertEquals(expected, Differ.generate(path1, path2, format));
    }
}
