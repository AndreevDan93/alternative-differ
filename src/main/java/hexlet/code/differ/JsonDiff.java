package hexlet.code.differ;


import hexlet.code.Utils;

import hexlet.code.parser.JsonParser;
import hexlet.code.parser.Parser;

import java.io.IOException;

public class JsonDiff extends Diff {


    public JsonDiff(String filePath1, String filePath2) {
        super(filePath1, filePath2);
    }


    @Override
    void correctExtCheck() throws IOException {
        if (!Utils.getExtension(getFilePath1()).equals("json")) {
            throw new RuntimeException("incorrect input file format");
        }
    }

    @Override
    Parser getParser() {
        return new JsonParser();
    }
}
