package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.Value;

import java.util.Map;

public abstract class Formatter {

    public abstract String format(Map<String, Value> valueMap) throws JsonProcessingException;

    protected final void generateFormat(Map<String, Value> valueMap) {
        for (Map.Entry<String, Value> entry : valueMap.entrySet()) {
            switch (entry.getValue().getStatus()) {
                case STATUS_ADDED -> valueWasAdded(valueMap, entry.getKey());
                case STATUS_DELETED -> valueWasDeleted(valueMap, entry.getKey());
                case STATUS_CHANGED -> valueWasChanged(valueMap, entry.getKey());
                case STATUS_UNCHANGED -> valueWasUnchanged(valueMap, entry.getKey());
                default -> {
                }
            }
        }
    }

    protected abstract void valueWasAdded(Map<String, Value> valueMap, String key);

    protected abstract void valueWasDeleted(Map<String, Value> valueMap, String key);

    protected abstract void valueWasChanged(Map<String, Value> valueMap, String key);

    protected abstract void valueWasUnchanged(Map<String, Value> valueMap, String key);


}
