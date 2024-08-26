package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static hexlet.code.Differ.NO_VALUE;

public class PlainFormatter implements Formatter {

    public static final String FORMAT_NAME = "plain";

    @Override
    public String generateOutput(Map<String, List<Object>> diff) {
        StringBuilder result = new StringBuilder();
        diff.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry -> {
                StringBuilder lineBuilder = new StringBuilder();
                lineBuilder.append("Property '").append(entry.getKey()).append("' was ");
                List<Object> values = entry.getValue();
                if (NO_VALUE.equals(values.get(0))) {
                    lineBuilder.append("added with value: ")
                            .append(getValueDescription(values.get(1)));
                } else if (NO_VALUE.equals(values.get(1))) {
                    lineBuilder.append("removed");
                } else if (!Objects.equals(values.get(0), values.get(1))) {
                    lineBuilder.append("updated. From ")
                            .append(getValueDescription(values.get(0)))
                            .append(" to ")
                            .append(getValueDescription(values.get(1)));
                } else {
                    return;
                }
                lineBuilder.append("\n");
                result.append(lineBuilder);
            }
        );
        return result.toString();
    }

    private String getValueDescription(Object object) {
        return switch (object) {
            case null -> "null";
            case Number number -> object.toString();
            case Boolean b -> b ? "true" : "false";
            case String s -> "'" + object + "'";
            default -> "[complex value]";
        };
    }
}
