package hexlet.code.differ;

import hexlet.code.DiffBuilder;
import hexlet.code.Value;
import hexlet.code.formatter.OutputFormat;
import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.JsonFormatter;
import hexlet.code.formatter.PlainFormatter;
import hexlet.code.formatter.StylishFormatter;
import hexlet.code.parser.Parser;

import java.io.IOException;
import java.util.Map;

import static hexlet.code.Utils.getAbsolutePath;
import static hexlet.code.Utils.getExtension;

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

    public String generate(OutputFormat outputFormat) throws IOException {
        extMatchingCheck();
        correctExtCheck();
        Parser parser = getParser();
        Map<String, Object> map1 = parser.parse(getAbsolutePath(getFilePath1()));
        Map<String, Object> map2 = parser.parse(getAbsolutePath(getFilePath2()));
        Map<String, Value> diff = DiffBuilder.getDiff(map1, map2);

        Formatter formatter = getFormatter(outputFormat);
        return formatter.format(diff);
    }

    public final String generate() throws IOException {
        return generate(OutputFormat.STYLISH);
    }

    public final Formatter getFormatter(OutputFormat outputFormat) {
        return switch (outputFormat) {
            case JSON -> new JsonFormatter();
            case PLAIN -> new PlainFormatter();
            default -> new StylishFormatter();
        };
    }

    void extMatchingCheck() throws IOException {
        String extension1 = getExtension(filePath1);
        String extension2 = getExtension(filePath2);
        if (!extension1.equals(extension2)) {
            throw new RuntimeException("input file formats are different");
        }
    }

    abstract void correctExtCheck() throws IOException;

    abstract Parser getParser();


}
