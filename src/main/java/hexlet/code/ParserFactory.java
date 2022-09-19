package hexlet.code;

import hexlet.code.parser.JsonParser;
import hexlet.code.parser.Parser;
import hexlet.code.parser.YmlParser;

import java.io.IOException;

public class ParserFactory {
    public static Parser getParser(String path1, String path2) throws IOException {
        String extension1 = Utils.getExtension(path1);
        String extension2 = Utils.getExtension(path2);
        if (!extension1.equals(extension2)) {
            throw new RuntimeException("input file formats are different");
        }
        return switch (extension1) {
            case "json" -> new JsonParser();
            case "yml" -> new YmlParser();
            default -> throw new RuntimeException("this file type is not supported");
        };
    }
}
