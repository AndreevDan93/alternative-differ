package hexlet.code.differ;

import hexlet.code.Utils;
import hexlet.code.parser.Parser;
import hexlet.code.parser.YmlParser;

import java.io.IOException;


public class YmlDiff extends Diff {


    public YmlDiff(String filePath1, String filePath2) {
        super(filePath1, filePath2);
    }

    @Override
    void correctExtCheck() throws IOException {
        if (!Utils.getExtension(getFilePath1()).equals("yml")) {
            throw new RuntimeException("incorrect input file format");
        }
    }

    @Override
    Parser getParser() {
        return new YmlParser();
    }
}
