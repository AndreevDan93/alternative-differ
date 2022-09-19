package hexlet.code.differ;

import hexlet.code.Utils;
import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.JsonFormatter;
import hexlet.code.formatter.PlainFormatter;
import hexlet.code.formatter.StylishFormatter;

import java.io.IOException;

public abstract class Diff {
    final String filePath1;
    final String filePath2;


    public Diff(String filePath1, String filePath2) {
        this.filePath1 = filePath1;
        this.filePath2 = filePath2;
    }


    public abstract String generate(String format) throws IOException;

    public Formatter getFormatter(String format) {
        return switch (format) {
            case "json" -> new JsonFormatter();
            case "plain" -> new PlainFormatter();
            default -> new StylishFormatter();
        };
    }
}
