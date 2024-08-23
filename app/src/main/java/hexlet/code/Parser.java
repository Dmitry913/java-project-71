package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Parser {

    public static Map<String, String> parseFile(Path path1) throws Exception {
        String contentFile = Files.readString(path1.toAbsolutePath().normalize());
        ObjectMapper mapper = createMapper(path1);
        return mapper.readValue(contentFile, new TypeReference<>() {
        });
    }

    private static ObjectMapper createMapper(Path path) {
        if (path.getFileName().toString().endsWith(".json")) {
            return new JsonMapper();
        } else if (path.getFileName().toString().endsWith(".yaml") || path.getFileName().toString().endsWith(".yml")) {
            return new YAMLMapper();
        } else {
            throw new RuntimeException(
                    "Данный тип файла не может быть прочитан. Поменяйте расширение на json/yaml/yml");
        }
    }

}
