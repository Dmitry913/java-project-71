package hexlet.code.formatter;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static hexlet.code.Differ.NO_VALUE;

public class StylishFormatter implements Formatter {
    public static final String FORMAT_NAME = "stylish";

    @Override
    public String generateOutput(Map<String, List<Object>> diff) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        diff.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry -> {
            List<Object> values = entry.getValue();
            builder.append("  ");
            if (NO_VALUE.equals(values.get(0))) {
                builder.append("+ ").append(entry.getKey()).append(": ").append(
                        Optional.ofNullable(values.get(1)).orElse("null"));
            } else if (NO_VALUE.equals(entry.getValue().get(1))) {
                builder.append("- ").append(entry.getKey()).append(": ").append(
                        Optional.ofNullable(values.get(0)).orElse("null"));
            } else if (Objects.equals(values.get(0), values.get(1))) {
                builder.append("  ").append(entry.getKey()).append(": ").append(
                        Optional.ofNullable(values.get(0)).orElse("null"));
            } else {
                builder.append("- ").append(entry.getKey()).append(": ").append(
                        Optional.ofNullable(values.get(0)).orElse("null")).append("\n");
                builder.append("  ").append("+ ").append(entry.getKey()).append(": ").append(
                        Optional.ofNullable(values.get(1)).orElse("null"));
            }
            builder.append("\n");
        }
        );
        builder.append("}");
        return builder.toString();
    }
}
