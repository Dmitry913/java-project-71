package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;
import java.util.concurrent.Callable;

@Command(
        name = "genDiff",
        mixinStandardHelpOptions = true,
        version = "genDiff 1.0",
        description = "Compares two configuration files and shows a difference.")
@AllArgsConstructor
@NoArgsConstructor
public class GenDiffCommand implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file")
    private Path pathToFirstFile;
    @Parameters(index = "1", description = "path to second file")
    private Path pathToSecondFile;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish]")
    private String format;

    @Override
    public Integer call() throws Exception {
        String output = getOutput();
        System.out.println(output);
        return 0;
    }

    public String getOutput() throws Exception {
        var contentFromFirstFile = Parser.parseFile(pathToFirstFile);
        var contentFromSecondFile = Parser.parseFile(pathToSecondFile);
        var diff = Differ.findDiff(contentFromFirstFile, contentFromSecondFile);
        return createFormatter().generateOutput(diff);
    }

    private Formatter createFormatter() {
        switch (format) {
            case StylishFormatter.FORMAT_NAME:
                return new StylishFormatter();
            case PlainFormatter.FORMAT_NAME:
                return new PlainFormatter();
            default:
                throw new RuntimeException("Не наден данный формат вывода");
        }
    }
}
