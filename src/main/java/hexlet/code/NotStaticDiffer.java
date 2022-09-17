package hexlet.code;

import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.JsonFormatter;
import hexlet.code.formatter.PlainFormatter;
import hexlet.code.formatter.StylishFormatter;
import hexlet.code.parser.Parser;
import hexlet.code.parser.JsonParser;
import hexlet.code.parser.YmlParser;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public final class NotStaticDiffer {
    private  Parser parser;
    private  Formatter formatter;
    private final Path absolutPath1;
    private final Path absolutPath2;
    private final String format;


    public NotStaticDiffer(String filepath1, String filepath2, String format) throws IOException {
        this.absolutPath1 = Utils.getAbsolutePath(filepath1);
        this.absolutPath2 = Utils.getAbsolutePath(filepath2);
        this.format = format;
    }

    public NotStaticDiffer(String filepath1, String filepath2) throws IOException {
        this(filepath1, filepath2, "stylish");
    }

    public String generate() throws IOException {
        this.parser = chooseParser(absolutPath1, absolutPath2);


        Map<String, Value> map = getDiff();
        this.formatter = chooseFormatter(format);
        return this.formatter.format(map);
    }

    private Parser chooseParser(Path filePath1, Path filePath2) throws IOException {
        String extension1 = Utils.getExtension(String.valueOf(filePath1));
        String extension2 = Utils.getExtension(String.valueOf(filePath2));
        if (!extension1.equals(extension2)) {
            throw new RuntimeException("input file formats are different");
        }
        return switch (extension1) {
            case "json" -> new JsonParser();
            case "yml" -> new YmlParser();
            default -> throw new RuntimeException("this file type is not supported");
        };
    }

    private Formatter chooseFormatter(String format) {
        return switch (format) {
            case "json" -> new JsonFormatter();
            case "plain" -> new PlainFormatter();
            case "stylish" -> new StylishFormatter();
            default -> throw new RuntimeException(format + " this format is not supported");
        };
    }

    private Map<String, Value> getDiff() throws IOException {
        Map<String, Object> map1 = this.parser.parse(this.absolutPath1);
        Map<String, Object> map2 = this.parser.parse(this.absolutPath2);

        Set<String> keysSet = new TreeSet<>(map1.keySet());
        keysSet.addAll(map2.keySet());

        Map<String, Value> diff = new LinkedHashMap<>();
        for (String key : keysSet) {
            if (!map1.containsKey(key)) {
                diff.put(key, new Value(null, map2.get(key), Status.STATUS_ADDED));
            } else if (!map2.containsKey(key)) {
                diff.put(key, new Value(map1.get(key), null, Status.STATUS_DELETED));
            } else if (compare(map1.get(key), map2.get(key))) {
                diff.put(key, new Value(map1.get(key), map2.get(key), Status.STATUS_UNCHANGED));
            } else {
                diff.put(key, new Value(map1.get(key), map2.get(key), Status.STATUS_CHANGED));
            }
        }
        return diff;
    }

    private boolean compare(Object value1, Object value2) {
        if (value1 == null || value2 == null) {
            return value1 == null && value2 == null;
        } else {
            return value1.equals(value2);
        }
    }

}
