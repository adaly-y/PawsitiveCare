package model;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;


/**
 * The Pet class represents a pet and its medical details, such as vaccinations, allergies, medications, and diet.
 * This class allows adding and updating medical records for a pet.
 * - Adding vaccinations, allergies, medications, and diet items to the pet's medical record.
 * - Retrieving a summary of the pet's medical history.
 * - Retrieving a summary of the pet's basic details (name, species, breed, age).
 * 
 * Example:
 * 
 * Pet myPet = new Pet("Buddy", "Dog", "Golden Retriever", 3);
 * myPet.addVaccination("Rabies");
 * myPet.addAllergy("Peanuts");
 * myPet.addMedication("Anti-allergy pills");
 * myPet.addDiet("Dry food");
 * 
 * // Get a summary of the pet's information
 * System.out.println(myPet.getPetSummary()); 
 * // Output: "Buddy, a 3-year-old Golden Retriever"
 * 
 * // Get the full medical history
 * System.out.println(myPet.getMedicalHistory());
 * // Output: "Vaccinations: Rabies; Allergies: Peanuts; Medications: Anti-allergy pills; Diet: Dry food"
 */

public class Pet implements Writable {
    private String name;
    private String species;
    private String breed;
    private int age;
    private List<String> vaccinations;
    private List<String> allergies;
    private List<String> medications;
    private List<String> diet;

    // EFFECTS: constructs a Pet with the specified name, species, breed, and age
    public Pet(String name, String species, String breed, int age) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.vaccinations = new ArrayList<>();
        this.allergies = new ArrayList<>();
        this.medications = new ArrayList<>();
        this.diet = new ArrayList<>();
    }

    // REQUIRES: vaccination != null
    // MODIFIES: this
    // EFFECTS: adds a vaccination to the pet's medical record
    public void addVaccination(String vaccination) {
        vaccinations.add(vaccination);
        EventLog.getInstance().logEvent(new Event("Vaccination '" + vaccination + "' added to pet " + name));
    }

    // REQUIRES: allergy != null
    // MODIFIES: this
    // EFFECTS: adds an allergy to the pet's medical record
    public void addAllergy(String allergy) {
        allergies.add(allergy);
        EventLog.getInstance().logEvent(new Event("Allergy '" + allergy + "' added to pet " + name));
    }

    // REQUIRES: medication != null
    // MODIFIES: this
    // EFFECTS: adds a medication to the pet's medical record
    public void addMedication(String medication) {
        medications.add(medication);
        EventLog.getInstance().logEvent(new Event("Medication '" + medication + "' added to pet " + name));
    }

    // REQUIRES: dietItem != null
    // MODIFIES: this
    // EFFECTS: adds a diet item to the pet's medical record
    public void addDiet(String dietItem) {
        diet.add(dietItem);
        EventLog.getInstance().logEvent(new Event("Diet item '" + dietItem + "' added to pet " + name));
    }

    // EFFECTS: returns a summary of the pet's medical history
    public String getMedicalHistory() {
        String history = "Vaccinations: " + String.join(", ", vaccinations)
                         + "\nAllergies: " + String.join(", ", allergies)
                         + "\nMedications: " + String.join(", ", medications)
                         + "\nDiet: " + String.join(", ", diet);
        return history;
    }

    // EFFECTS: returns a summary of the pet's details
    public String getPetSummary() {
        return "Name: " + name + ", Species: " + species + ", Breed: " + breed + ", Age: " + age;
    }

    // EFFECTS: returns a JSON representation of the pet
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("species", species);
        json.put("breed", breed);
        json.put("age", age);
        json.put("vaccinations", new JSONArray(vaccinations));
        json.put("allergies", new JSONArray(allergies));
        json.put("medications", new JSONArray(medications));
        json.put("diet", new JSONArray(diet));
        return json;
    }
    
    // Getter methods for Pet's fields
    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public List<String> getVaccinations() {
        return vaccinations;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public List<String> getMedications() {
        return medications;
    }

    public List<String> getDiet() {
        return diet;
    }

    public void setVaccinations(List<String> vaccinations) {
        this.vaccinations = vaccinations;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public void setDiet(List<String> diet) {
        this.diet = diet;
    }
}
