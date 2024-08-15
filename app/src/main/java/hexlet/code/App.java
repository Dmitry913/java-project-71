package hexlet.code;

import picocli.CommandLine;

import java.util.concurrent.Callable;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class App {
    public static void main(String[] args) {
        new CommandLine(new GenDiffCommand()).execute(args);
    }

}