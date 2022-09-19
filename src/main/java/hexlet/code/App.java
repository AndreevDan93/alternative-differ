package hexlet.code;

import hexlet.code.differ.Diff;
import hexlet.code.differ.JsonDiff;
import hexlet.code.differ.YmlDiff;
import hexlet.code.formatter.Format;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;


@Command(name = "getDiff", mixinStandardHelpOptions = true, version = "getDiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {
    private static final Integer ERROR_CODE = 123;

    @Parameters(index = "0", description = "path to first file.")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file.")
    private String filepath2;

    @Option(names = {"-f", "--format"}, description = "output format \"stylish\" or \"plain\" or \"json\" "
            + "[default: stylish]")
    private String format = "stylish";

    //  Define your business logic in the run or call method of your class. This method is called after parsing
//  is successfully completed.
    @Override
    public final Integer call() { // your business logic goes here...
        Format inputFormat = Format.STYLISH;
        for (Format form : Format.values()) {
            if (form.toString().toLowerCase().equals(format)) {
                inputFormat = form;
            }
        }
        try {
            Diff differ = switch (Utils.getExtension(filepath1)) {
                case ("json") -> new JsonDiff(filepath1, filepath2);
                case ("yml") -> new YmlDiff(filepath1, filepath2);
                default -> throw new RuntimeException("Unsupported file's format");
            };
            differ.generate(inputFormat);
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR_CODE;
        }
    }

    public static void main(String[] args) {
        System.exit(new CommandLine(new App()).execute(args));
    }
}
