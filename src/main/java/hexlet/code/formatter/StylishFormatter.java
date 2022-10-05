package hexlet.code.formatter;

import hexlet.code.value.Value;

import java.util.Map;


public final class StylishFormatter extends Formatter {
    private final StringBuilder builder = new StringBuilder();

    @Override
    public String format(Map<String, Value> valueMap) {
        builder.append("{\n");
        generateFormat(valueMap);
        builder.append("}");
        return builder.toString();
    }

    @Override
    protected void valueWasAdded(Map<String, Value> valueMap, String key) {
        builder.append("  + ").append(key).append(": ")
                .append(valueMap.get(key).getValue2()).append("\n");
    }

    @Override
    protected void valueWasDeleted(Map<String, Value> valueMap, String key) {
        builder.append("  - ").append(key).append(": ")
                .append(valueMap.get(key).getValue1()).append("\n");

    }

    @Override
    protected void valueWasChanged(Map<String, Value> valueMap, String key) {
        builder.append("  - ").append(key).append(": ")
                .append(valueMap.get(key).getValue1()).append("\n");
        builder.append("  + ").append(key).append(": ")
                .append(valueMap.get(key).getValue2()).append("\n");
    }

    @Override
    protected void valueWasUnchanged(Map<String, Value> valueMap, String key) {
        builder.append("    ").append(key).append(": ")
                .append(valueMap.get(key).getValue1()).append("\n");
    }

}
