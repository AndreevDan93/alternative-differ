package hexlet.code;

import hexlet.code.differ.Differ;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

import static hexlet.code.formatter.enums.OutputFormat.getOutputFormat;


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
        try {
            Differ differ = DifferFactory.getDiffer(filepath1, filepath2);
            System.out.println(differ.generate(getOutputFormat(format)));
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
