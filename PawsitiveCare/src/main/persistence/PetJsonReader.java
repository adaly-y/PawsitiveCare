package persistence;

import model.PetCollection;
import model.Pet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

// code referencing JsonSerializationDemo
public class PetJsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public PetJsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads pet collection from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PetCollection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePetCollection(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(source)) {
            int i;
            while ((i = reader.read()) != -1) {
                contentBuilder.append((char) i);  // Read file content
            }
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses pet collection from JSON object and returns it
    private PetCollection parsePetCollection(JSONObject jsonObject) {
        PetCollection petCollection = new PetCollection();
        JSONArray petArray = jsonObject.getJSONArray("pets");

        for (Object json : petArray) {
            JSONObject petObject = (JSONObject) json;
            addPet(petCollection, petObject);
        }

        return petCollection;
    }

    // MODIFIES: petCollection
    // EFFECTS: parses a pet from JSON object and adds it to the pet collection
    private void addPet(PetCollection petCollection, JSONObject petObject) {
        String name = petObject.getString("name");
        String species = petObject.getString("species");
        String breed = petObject.getString("breed");
        int age = petObject.getInt("age");

        Pet pet = new Pet(name, species, breed, age);

        // Converting JSONArray to List<String> for each field
        pet.setVaccinations(convertJsonArrayToList(petObject.getJSONArray("vaccinations")));
        pet.setAllergies(convertJsonArrayToList(petObject.getJSONArray("allergies")));
        pet.setMedications(convertJsonArrayToList(petObject.getJSONArray("medications")));
        pet.setDiet(convertJsonArrayToList(petObject.getJSONArray("diet")));

        petCollection.addPet(pet);
    }

    // Converts a JSONArray to a List<String>
    private List<String> convertJsonArrayToList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        for (Object obj : jsonArray) {
            list.add((String) obj);  // Ensure each element is a String
        }
        return list;
    }
    
}
