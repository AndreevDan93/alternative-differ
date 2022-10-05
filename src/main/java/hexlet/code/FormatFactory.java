package hexlet.code;

import hexlet.code.dataFormat.OutputFormat;
import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.JsonFormatter;
import hexlet.code.formatter.PlainFormatter;
import hexlet.code.formatter.StylishFormatter;

public final class FormatFactory {
    public static Formatter getFormatter(OutputFormat outputFormat) {
        return switch (outputFormat) {
            case JSON -> new JsonFormatter();
            case PLAIN -> new PlainFormatter();
            default -> new StylishFormatter();
        };
    }
}
