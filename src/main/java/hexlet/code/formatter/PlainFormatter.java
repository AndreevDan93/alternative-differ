package hexlet.code.formatter;

import hexlet.code.Value;

import java.util.Map;

public final class PlainFormatter extends Formatter {
    private final StringBuilder builder = new StringBuilder();

    @Override
    public String format(Map<String, Value> valueMap) {
        generateFormat(valueMap);
        return builder.toString().trim();
    }

    @Override
    protected void valueWasAdded(Map<String, Value> valueMap, String key) {
        builder.append("Property '")
                .append(key)
                .append("' was added with value: ")
                .append(getPlainValue(valueMap.get(key).getSecondOb()))
                .append("\n");
    }

    @Override
    protected void valueWasDeleted(Map<String, Value> valueMap, String key) {
        builder.append("Property '")
                .append(key)
                .append("' was removed")
                .append("\n");
    }

    @Override
    protected void valueWasChanged(Map<String, Value> valueMap, String key) {
        builder.append("Property '")
                .append(key)
                .append("' was updated. From ")
                .append(getPlainValue(valueMap.get(key).getFirstOb()))
                .append(" to ")
                .append(getPlainValue(valueMap.get(key).getSecondOb()))
                .append("\n");
    }

    @Override
    protected void valueWasUnchanged(Map<String, Value> valueMap, String key) {
    }

    private static String getPlainValue(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Integer || value instanceof Boolean) {
            return value.toString();
        } else {
            return "[complex value]";
        }
    }
}

