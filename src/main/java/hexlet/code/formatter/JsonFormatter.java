package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Value;

import java.util.LinkedHashMap;
import java.util.Map;


public final class JsonFormatter extends Formatter {
    private final Map<String, Object> jMap = new LinkedHashMap<>();

    @Override
    public String format(Map<String, Value> valueMap) throws JsonProcessingException {
        generateFormat(valueMap);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(jMap);
    }

    @Override
    protected void valueWasAdded(Map<String, Value> valueMap, String key) {
        jMap.put("+" + key, valueMap.get(key).getSecondOb());
    }

    @Override
    protected void valueWasDeleted(Map<String, Value> valueMap, String key) {
        jMap.put("-" + key, valueMap.get(key).getFirstOb());
    }

    @Override
    protected void valueWasChanged(Map<String, Value> valueMap, String key) {
        jMap.put("-" + key, valueMap.get(key).getFirstOb());
        jMap.put("+" + key, valueMap.get(key).getSecondOb());
    }

    @Override
    protected void valueWasUnchanged(Map<String, Value> valueMap, String key) {
        jMap.put(key, valueMap.get(key).getFirstOb());
    }
}
