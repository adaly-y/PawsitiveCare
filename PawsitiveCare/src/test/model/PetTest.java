package model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PetTest {
    private Pet testPet;

    @BeforeEach
    void runBefore() {
        testPet = new Pet("Fluffy", "Cat", "Siamese", 3);
    }

    @Test
    void testAddVaccination() {
        testPet.addVaccination("Rabies");
        assertTrue(testPet.getMedicalHistory().contains("Rabies"));
    }

    @Test
    void testAddAllergy() {
        testPet.addAllergy("Peanuts");
        assertTrue(testPet.getMedicalHistory().contains("Peanuts"));
    }

    @Test
    void testAddMedication() {
        testPet.addMedication("Heartworm medication");
        assertTrue(testPet.getMedicalHistory().contains("Heartworm medication"));
    }

    @Test
    void testAddDiet() {
        testPet.addDiet("Fish");
        assertTrue(testPet.getMedicalHistory().contains("Fish"));
    }

    @Test
    void testGetPetSummary() {
        assertEquals("Name: Fluffy, Species: Cat, Breed: Siamese, Age: 3", testPet.getPetSummary());
    }
}
