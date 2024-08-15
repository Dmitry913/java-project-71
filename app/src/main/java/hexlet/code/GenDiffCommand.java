package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.Callable;

@Command(
        name = "genDiff",
        mixinStandardHelpOptions = true,
        version = "genDiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class GenDiffCommand implements Callable<Integer> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Parameters(index = "0", description = "path to first file")
    private Path pathToFirstFile;
    @Parameters(index = "1", description = "path to second file")
    private Path pathToSecondFile;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish]")
    private String format;

    @Override
    public Integer call() throws Exception {
        var contentFromFirstFile = getData(Files.readString(pathToFirstFile.toAbsolutePath().normalize()));
        var contentFromSecondFile = getData(Files.readString(pathToSecondFile));
        System.out.println(Differ.generate(contentFromFirstFile, contentFromSecondFile));
        return 0;
    }

    public static Map<String, String> getData(String content) throws Exception {
        return mapper.readValue(content, new TypeReference<>() {});
    }
}
