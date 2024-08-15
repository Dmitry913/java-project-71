package hexlet.code;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "genDiff",
        mixinStandardHelpOptions = true,
        version = "genDiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class GenDiffCommand implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("Hello world!");
        return 0;
    }
}
