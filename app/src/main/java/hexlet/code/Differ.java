package hexlet.code;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Differ {

    private static final String REMOVE_PAIR = "- ";
    private static final String ADD_PAIR = "+ ";

    public static String generate(Path path1, Path path2) throws Exception {
        var contentFromFirstFile = Parser.parseFile(path1);
        var contentFromSecondFile = Parser.parseFile(path2);
        return findDiff(contentFromFirstFile, contentFromSecondFile);
    }

    private static String findDiff(Map<String, String> json1, Map<String, String> json2) throws Exception {
        Map<String, String> copyJson2 = new HashMap<>(json2);
        Map<List<String>, String> diff = new LinkedHashMap<>();
        for (var keyValue1 : json1.entrySet()) {
            if (copyJson2.containsKey(keyValue1.getKey())) {
                if (copyJson2.get(keyValue1.getKey()).equals(keyValue1.getValue())) {
                    diff.put(List.of("  ", keyValue1.getKey()), keyValue1.getValue());
                } else {
                    diff.put(List.of(REMOVE_PAIR, keyValue1.getKey()), keyValue1.getValue());
                    diff.put(List.of(ADD_PAIR, keyValue1.getKey()), copyJson2.get(keyValue1.getKey()));
                }
                copyJson2.remove(keyValue1.getKey());
            } else {
                diff.put(List.of(REMOVE_PAIR, keyValue1.getKey()), keyValue1.getValue());
            }
        }
        copyJson2.forEach((key, value) -> diff.put(List.of(ADD_PAIR, key), value));
        StringBuilder builder = new StringBuilder("{\n");
        diff.entrySet().stream().sorted(
                        Comparator.comparing(entry -> entry.getKey().getLast()))
                .forEach(entry -> builder.append(
                        "  " + entry.getKey().getFirst() + entry.getKey().getLast() + ": " + entry.getValue() + "\n"));
        builder.append("}");
        return builder.toString();
    }
}
