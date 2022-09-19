package hexlet.code.differ;

import hexlet.code.DiffBuilder;
import hexlet.code.ParserFactory;
import hexlet.code.Utils;
import hexlet.code.Value;
import hexlet.code.formatter.Format;
import hexlet.code.formatter.Formatter;
import hexlet.code.parser.Parser;

import java.io.IOException;
import java.util.Map;

public class JsonDiff extends Diff {


    public JsonDiff(String filePath1, String filePath2) {
        super(filePath1, filePath2);
    }

    @Override
    public final String generate(Format format) throws IOException {
        if (!Utils.getExtension(getFilePath1()).equals("json")) {
            throw new RuntimeException("incorrect input file format");
        }
        Parser parser = ParserFactory.getParser(getFilePath1(), getFilePath2());
        Map<String, Object> map1 = parser.parse(Utils.getAbsolutePath(getFilePath1()));
        Map<String, Object> map2 = parser.parse(Utils.getAbsolutePath(getFilePath2()));
        Map<String, Value> diff = DiffBuilder.getDiff(map1, map2);

        Formatter formatter = getFormatter(format);
        return formatter.format(diff);
    }
}
