package hexlet.code.formatters;

import hexlet.code.DifferPropertyDescription;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static hexlet.code.DifferPropertyDescription.NO_VALUE;

public final class StylishFormatter extends Formatter {
    public static final String FORMAT_NAME = "stylish";

    @Override
    public String generate(List<DifferPropertyDescription> diff) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        diff.stream().sorted(Comparator.comparing(DifferPropertyDescription::getPropertyName)).forEach(elem -> {
            builder.append("  ");
                if (NO_VALUE.equals(elem.getPreferValue())) {
                    builder.append("+ ").append(elem.getPropertyName()).append(": ").append(
                            Optional.ofNullable(elem.getNewValue()).orElse("null"));
                } else if (NO_VALUE.equals(elem.getNewValue())) {
                    builder.append("- ").append(elem.getPropertyName()).append(": ").append(
                            Optional.ofNullable(elem.getPreferValue()).orElse("null"));
                } else if (Objects.equals(elem.getPreferValue(), elem.getNewValue())) {
                    builder.append("  ").append(elem.getPropertyName()).append(": ").append(
                            Optional.ofNullable(elem.getPreferValue()).orElse("null"));
            } else {
                builder.append("- ").append(elem.getPropertyName()).append(": ").append(
                        Optional.ofNullable(elem.getPreferValue()).orElse("null")).append("\n");
                builder.append("  ").append("+ ").append(elem.getPropertyName()).append(": ").append(
                        Optional.ofNullable(elem.getNewValue()).orElse("null"));
            }
            builder.append("\n");
        }
        );
        builder.append("}");
        return builder.toString();
    }
}
