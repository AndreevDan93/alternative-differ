package hexlet.code;

import hexlet.code.differ.Differ;
import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.JsonFormatter;
import hexlet.code.formatter.PlainFormatter;
import hexlet.code.formatter.StylishFormatter;
import hexlet.code.parser.JsonParser;
import hexlet.code.parser.Parser;
import hexlet.code.parser.YmlParser;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public final class Engine {
    private final String firsPath;
    private final String secondPart;
    private final String outputFormat;

    public Engine(String filePath1, String filePath2, String format) {
        this.firsPath = filePath1;
        this.secondPart = filePath2;
        this.outputFormat = format;
    }

    public Engine(String filePath1, String filePath2) {
        this(filePath1, filePath2, "stylish");
    }

    public String generate() throws IOException {
        Path absolutePath1 = Utils.getAbsolutePath(firsPath);
        Path absolutePath2 = Utils.getAbsolutePath(secondPart);
        Parser parser = getParser();
        Map<String, Object> map1 = parser.parse(absolutePath1);
        Map<String, Object> map2 = parser.parse(absolutePath2);
        Differ differ = getDiffer();
        Map<String, Value> diff = differ.getDiff(map1, map2);
        Formatter formatter = getFormatter();
        return formatter.format(diff);
    }

    public Differ getDiffer() {
        return new Differ();
    }

    public Parser getParser() throws IOException {
        String extension1 = Utils.getExtension(this.firsPath);
        String extension2 = Utils.getExtension(String.valueOf(this.secondPart));
        if (!extension1.equals(extension2)) {
            throw new RuntimeException("input file formats are different");
        }
        return switch (extension1) {
            case "json" -> new JsonParser();
            case "yml" -> new YmlParser();
            default -> throw new RuntimeException("this file type is not supported");
        };
    }

    public Formatter getFormatter() {
        return switch (this.outputFormat) {
            case "json" -> new JsonFormatter();
            case "plain" -> new PlainFormatter();
            case "stylish" -> new StylishFormatter();
            default -> throw new RuntimeException(this.outputFormat + " this format is not supported");
        };
    }
}
