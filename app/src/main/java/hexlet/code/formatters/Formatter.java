package hexlet.code.formatters;

import hexlet.code.DifferPropertyDescription;

import java.util.List;

public abstract class Formatter {

    public static String generateOutput(List<DifferPropertyDescription> diff, String format) {
        return createFormatter(format).generate(diff);
    }

    abstract String generate(List<DifferPropertyDescription> diff);

    private static Formatter createFormatter(String format) {
        switch (format) {
            case StylishFormatter.FORMAT_NAME:
                return new StylishFormatter();
            case PlainFormatter.FORMAT_NAME:
                return new PlainFormatter();
            case JsonFormatter.FORMAT_NAME:
                return new JsonFormatter();
            default:
                throw new RuntimeException("Не наден данный формат вывода");
        }
    }
}
