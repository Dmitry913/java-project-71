package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static hexlet.code.DifferPropertyDescription.NO_VALUE;

public class Differ {

    public static List<DifferPropertyDescription> findDiff(Map<String, Object> json1, Map<String, Object> json2) {
        List<DifferPropertyDescription> diff = new ArrayList<>();
        Map<String, Object> copyJson2 = new HashMap<>(json2);
        for (var keyValue1 : json1.entrySet()) {
            diff.add(new DifferPropertyDescription(keyValue1.getKey(), keyValue1.getValue(),
                    copyJson2.getOrDefault(keyValue1.getKey(), NO_VALUE)
            ));
            copyJson2.remove(keyValue1.getKey());
        }
        diff.addAll(
                copyJson2.entrySet()
                        .stream()
                        .map(entry -> new DifferPropertyDescription(entry.getKey(), NO_VALUE, entry.getValue()))
                        .toList()
        );
        return diff;
    }
}
