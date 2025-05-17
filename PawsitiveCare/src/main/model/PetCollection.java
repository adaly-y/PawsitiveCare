package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

/**
 * The PetCollection class represents a collection of pets.
 * It allows adding and removing for pets, as well as viewing all pets in the collection.
 * - Adding a new pet to the collection.
 * - Finding a pet by its name.
 * - Removing a pet from the collection by its name.
 * - Viewing a summary of all pets in the collection.
 * 
 * Example:
 * 
 * PetCollection petCollection = new PetCollection();
 * Pet pet1 = new Pet("Max", "Dog", "Golden Retriever", 5);
 * Pet pet2 = new Pet("Mittens", "Cat", "Siamese", 2);
 * 
 * petCollection.addPet(pet1);
 * petCollection.addPet(pet2);
 * 
 * // Find a pet by name
 * Pet foundPet = petCollection.findPetByName("Max");
 * System.out.println(foundPet.getPetSummary());
 * // Output: "Max, a 5-year-old Golden Retriever"
 * 
 * // Remove a pet by name
 * petCollection.removePet("Mittens");
 * 
 * // View all pets
 * System.out.println(petCollection.viewAllPets());
 * // Output: "Max, a 5-year-old Golden Retriever"
 */

public class PetCollection implements Writable {
    private List<Pet> pets;

    // EFFECTS: constructs an empty collection of pets
    public PetCollection() {
        pets = new ArrayList<>();
    }

    // REQUIRES: pet != null
    // MODIFIES: this
    // EFFECTS: adds a pet to the collection of pets
    public void addPet(Pet pet) {
        pets.add(pet);
        EventLog.getInstance().logEvent(new Event("Pet '" + pet.getName() + "' added to collection"));
    }


    // Getter method for the list of pets
    public List<Pet> getPets() {
        return pets;
    }

    // REQUIRES: name != null
    // EFFECTS: returns the pet with the specified name, or null if not found
    public Pet findPetByName(String name) {
        for (Pet pet : pets) {
            if (pet.getPetSummary().contains(name)) {
                return pet;
            }
        }
        return null;
    }

    // REQUIRES: name != null
    // MODIFIES: this
    // EFFECTS: removes the pet with the specified name from the collection
    public void removePet(Pet pet) {
        pets.remove(pet);
        EventLog.getInstance().logEvent(new Event("Pet '" + pet.getName() + "' removed from collection"));
    }
    
    // EFFECTS: returns a summary of all pets in the collection
    public String viewAllPets() {
        StringBuilder petList = new StringBuilder();
        for (Pet pet : pets) {
            petList.append(pet.getPetSummary()).append("\n");
        }
        return petList.toString();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray petArray = new JSONArray();
        for (Pet pet : pets) {
            petArray.put(pet.toJson());
        }
        json.put("pets", petArray);
        return json;
    }
    
}
