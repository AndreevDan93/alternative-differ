package hexlet.code.differ;

import hexlet.code.parser.Parser;
import hexlet.code.parser.YmlParser;


public class YmlDiffer extends Differ {


    public YmlDiffer(String filePath1, String filePath2) {
        super(filePath1, filePath2);
    }

    @Override
    Parser getParser() {
        return new YmlParser();
    }
}
