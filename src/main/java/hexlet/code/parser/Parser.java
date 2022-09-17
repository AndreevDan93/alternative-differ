package hexlet.code.parser;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface Parser {
    Map<String, Object> parse(Path path) throws IOException;

}
