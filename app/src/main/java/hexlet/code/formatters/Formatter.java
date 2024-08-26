package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public interface Formatter {
    String generateOutput(Map<String, List<Object>> diff);
}
