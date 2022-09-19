package hexlet.code;

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
                diff.put(key, new Value(null, map2.get(key), Status.STATUS_ADDED));
            } else if (!map2.containsKey(key)) {
                diff.put(key, new Value(map1.get(key), null, Status.STATUS_DELETED));
            } else if (compare(map1.get(key), map2.get(key))) {
                diff.put(key, new Value(map1.get(key), map2.get(key), Status.STATUS_UNCHANGED));
            } else {
                diff.put(key, new Value(map1.get(key), map2.get(key), Status.STATUS_CHANGED));
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
