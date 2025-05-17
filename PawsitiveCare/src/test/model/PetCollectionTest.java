package model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PetCollectionTest {
    private PetCollection petCollection;
    private Pet pet1;
    private Pet pet2;

    @BeforeEach
    void runBefore() {
        petCollection = new PetCollection();
        pet1 = new Pet("Fluffy", "Cat", "Siamese", 3);
        pet2 = new Pet("Max", "Dog", "Labrador", 5);
        petCollection.addPet(pet1);
        petCollection.addPet(pet2);
    }

    @Test
    void testAddPet() {
        assertEquals("Name: Fluffy, Species: Cat, Breed: Siamese, Age: 3", petCollection.findPetByName("Fluffy").getPetSummary());
    }

    @Test
    void testRemovePet() {
    Pet petToRemove = petCollection.findPetByName("Fluffy");
    assertNotNull(petToRemove);
    petCollection.removePet(petToRemove);
    assertNull(petCollection.findPetByName("Fluffy"));
}

    @Test
    void testViewAllPets() {
        String expected = "Name: Fluffy, Species: Cat, Breed: Siamese, Age: 3\nName: Max, Species: Dog, Breed: Labrador, Age: 5\n";
        assertEquals(expected, petCollection.viewAllPets());
    }

    @Test
    void testViewAllPetsWhenEmpty() {
        PetCollection emptyCollection = new PetCollection();
        assertEquals("", emptyCollection.viewAllPets());
    }

    @Test
    void testFindPetByNameNotFound() {
        Pet pet = petCollection.findPetByName("NonExistentPet");
        assertNull(pet);  
    }
}
