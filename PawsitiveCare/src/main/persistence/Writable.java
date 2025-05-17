package persistence;

import org.json.JSONObject;

// cited from JsonSerializationDemo
public interface Writable {
    // EFFECTS: Returns the object as a JSON object
    JSONObject toJson();
}
