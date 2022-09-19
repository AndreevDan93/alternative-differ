package hexlet.code.differ;

import hexlet.code.formatter.Format;
import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.JsonFormatter;
import hexlet.code.formatter.PlainFormatter;
import hexlet.code.formatter.StylishFormatter;

import java.io.IOException;

public abstract class Diff {
    private final String filePath1;
    private final String filePath2;

    public Diff(String filePath1, String filePath2) {
        this.filePath1 = filePath1;
        this.filePath2 = filePath2;
    }

    public final String getFilePath1() {
        return filePath1;
    }

    public final String getFilePath2() {
        return filePath2;
    }

    public abstract String generate(Format format) throws IOException;

    public final Formatter getFormatter(Format format) {
        return switch (format) {
            case JSON -> new JsonFormatter();
            case PLAIN -> new PlainFormatter();
            default -> new StylishFormatter();
        };
    }

}
