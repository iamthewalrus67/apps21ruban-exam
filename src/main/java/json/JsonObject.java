package json;

import java.util.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private LinkedHashMap<String, Json> hashMap = new LinkedHashMap<>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair jsonPair: jsonPairs) {
            hashMap.put(jsonPair.key, jsonPair.value);
        }
    }

    @Override
    public String toJson() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Json> mapEntry: hashMap.entrySet()) {
            String key = mapEntry.getKey();
            Json value = mapEntry.getValue();

            result.append(String.format("'%s': %s, ", key, value));
        }

        return "{"+result.toString().substring(0, Math.max(result.length()-2, 0))+"}";
    }

    public void add(JsonPair... jsonPairs) {
        for (JsonPair jsonPair: jsonPairs) {
            hashMap.put(jsonPair.key, jsonPair.value);
        }
    }

    public Json find(String name) {
        return hashMap.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject jsonObject = new JsonObject();
        for (String name: names) {
            if (hashMap.get(name) == null) {
                continue;
            }
            JsonPair jsonPair = new JsonPair(name, hashMap.get(name));
            jsonObject.add(jsonPair);
        }

        return jsonObject;
    }
}
