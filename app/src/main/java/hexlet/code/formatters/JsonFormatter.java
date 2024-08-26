package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DifferPropertyDescription;

import java.util.List;

public class JsonFormatter implements Formatter {

    public static final String FORMAT_NAME = "json";

    @Override
    public String generateOutput(List<DifferPropertyDescription> diff) {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(diff);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Не удалось преобразовать описание в json", e);
        }
    }
}
