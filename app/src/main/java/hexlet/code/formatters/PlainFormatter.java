package hexlet.code.formatters;

import hexlet.code.DifferPropertyDescription;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static hexlet.code.DifferPropertyDescription.NO_VALUE;

public final class PlainFormatter extends Formatter {

    public static final String FORMAT_NAME = "plain";

    @Override
    public String generate(List<DifferPropertyDescription> diff) {
        StringBuilder result = new StringBuilder();
        diff.stream().sorted(Comparator.comparing(DifferPropertyDescription::getPropertyName))
            .forEach(elem -> {
                StringBuilder lineBuilder = new StringBuilder();
                lineBuilder.append("Property '").append(elem.getPropertyName()).append("' was ");
                if (NO_VALUE.equals(elem.getPreferValue())) {
                        lineBuilder.append("added with value: ")
                                .append(getValueDescription(elem.getNewValue()));
                } else if (NO_VALUE.equals(elem.getNewValue())) {
                        lineBuilder.append("removed");
                } else if (!Objects.equals(elem.getPreferValue(), elem.getNewValue())) {
                        lineBuilder.append("updated. From ")
                                .append(getValueDescription(elem.getPreferValue()))
                                .append(" to ")
                                .append(getValueDescription(elem.getNewValue()));
                } else {
                    return;
                }
                lineBuilder.append("\n");
                result.append(lineBuilder);
            }
        );
        result.delete(result.length() - 1, result.length());
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
