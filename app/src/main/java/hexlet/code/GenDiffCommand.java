package hexlet.code;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;


import java.nio.file.Path;
import java.util.concurrent.Callable;

@Command(
        name = "genDiff",
        mixinStandardHelpOptions = true,
        version = "genDiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class GenDiffCommand implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file")
    private Path pathToFirstFile;
    @Parameters(index = "1", description = "path to second file")
    private Path pathToSecondFile;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish]")
    private String format;

    @Override
    public Integer call() throws Exception {
        System.out.println("Hello world!");
        return 0;
    }
}
