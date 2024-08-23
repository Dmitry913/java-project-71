package hexlet.code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Differ {

    public static final String NO_VALUE = "noValue";

    public static Map<String, List<Object>> findDiff(Map<String, Object> json1, Map<String, Object> json2) {
        // key to (was, became)
        Map<String, List<Object>> diff = new HashMap<>();
        for (var keyValue1 : json1.entrySet()) {
            diff.put(
                    keyValue1.getKey(),
                    Arrays.asList(keyValue1.getValue(), json2.getOrDefault(keyValue1.getKey(), NO_VALUE))
            );
        }
        diff.putAll(
                json2.entrySet()
                        .stream()
                        .filter(entry -> !diff.containsKey(entry.getKey()))
                        .collect(
                                Collectors.toMap(
                                        Map.Entry::getKey,
                                        entry -> Arrays.asList(NO_VALUE, entry.getValue())
                                )
                        )
        );
        return diff;
    }
}
