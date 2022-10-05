package hexlet.code.differ;

import hexlet.code.value.Value;
import hexlet.code.dataFormat.OutputFormat;
import hexlet.code.formatter.Formatter;
import hexlet.code.parser.Parser;

import java.io.IOException;
import java.util.Map;

import static hexlet.code.FormatFactory.getFormatter;
import static hexlet.code.Utils.getAbsolutePath;

public abstract class Differ {
    private final String filePath1;
    private final String filePath2;

    public Differ(String filePath1, String filePath2) {
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

    abstract Parser getParser();


}
