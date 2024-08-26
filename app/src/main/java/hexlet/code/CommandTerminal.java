package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(
        name = "genDiff",
        mixinStandardHelpOptions = true,
        version = "genDiff 1.0",
        description = "Compares two configuration files and shows a difference.")
@AllArgsConstructor
@NoArgsConstructor
public final class CommandTerminal implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file")
    private String pathToFirstFile;
    @Parameters(index = "1", description = "path to second file")
    private String pathToSecondFile;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish]")
    private String format;

    @Override
    public Integer call() throws Exception {
        String output = Differ.generate(pathToFirstFile, pathToSecondFile, format);
        System.out.println(output);
        return 0;
    }

}
