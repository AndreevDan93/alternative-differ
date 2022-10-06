package hexlet.code.differ;


import hexlet.code.parser.JsonParser;
import hexlet.code.parser.Parser;

public final class JsonDiffer extends Differ {


    public JsonDiffer(String filePath1, String filePath2) {
        super(filePath1, filePath2);
    }

    @Override
    Parser getParser() {
        return new JsonParser();
    }
}
