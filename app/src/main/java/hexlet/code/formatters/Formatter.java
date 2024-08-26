package hexlet.code.formatters;

import hexlet.code.DifferPropertyDescription;

import java.util.List;

public interface Formatter {
    String generateOutput(List<DifferPropertyDescription> diff);
}
