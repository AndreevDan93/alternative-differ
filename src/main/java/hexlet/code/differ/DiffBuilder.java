package hexlet.code.differ;

import hexlet.code.value.Status;
import hexlet.code.value.Value;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public final class DiffBuilder {

    public static Map<String, Value> getDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keysSet = new TreeSet<>(map1.keySet());
        keysSet.addAll(map2.keySet());

        Map<String, Value> diff = new LinkedHashMap<>();
        for (String key : keysSet) {
            if (!map1.containsKey(key)) {
                Value value = new Value(null, map2.get(key), Status.STATUS_ADDED);
                diff.put(key, value);
            } else if (!map2.containsKey(key)) {
                Value value = new Value(map1.get(key), null, Status.STATUS_DELETED);
                diff.put(key, value);
            } else if (compare(map1.get(key), map2.get(key))) {
                Value value = new Value(map1.get(key), map2.get(key), Status.STATUS_UNCHANGED);
                diff.put(key, value);
            } else {
                Value value = new Value(map1.get(key), map2.get(key), Status.STATUS_CHANGED);
                diff.put(key, value);
            }
        }
        return diff;
    }

    private static boolean compare(Object value1, Object value2) {
        if (value1 == null || value2 == null) {
            return value1 == null && value2 == null;
        } else {
            return value1.equals(value2);
        }
    }
}
