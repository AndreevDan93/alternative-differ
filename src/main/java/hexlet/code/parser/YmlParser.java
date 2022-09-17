package hexlet.code.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import static java.nio.file.Files.readString;

public final class YmlParser implements Parser {
    @Override
    public Map<String, Object> parse(Path path) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(readString(path), new TypeReference<>() {
        });
    }
}
