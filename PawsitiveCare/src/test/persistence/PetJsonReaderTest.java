package persistence;

import model.Pet;
import model.PetCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class PetJsonReaderTest {
    private PetJsonReader reader;

    @BeforeEach
    void setUp() {
        reader = new PetJsonReader("./data/testReaderGeneralWorkRoom.json"); // Provide your test JSON file
    }

    @Test
    void testReadValidFile() {
        try {
            PetCollection petCollection = reader.read();
            assertNotNull(petCollection);
            assertEquals(2, petCollection.getPets().size()); // Expecting 2 pets in the test JSON
            Pet firstPet = petCollection.getPets().get(0);
            assertEquals("Max", firstPet.getName());
            assertTrue(firstPet.getVaccinations().contains("Rabies"));

            // Test for allergies, medications, and diet
            assertTrue(firstPet.getAllergies().contains("Peanuts"));
            assertTrue(firstPet.getMedications().contains("Antihistamine"));
            assertTrue(firstPet.getDiet().contains("Dry food"));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testReadEmptyFile() {
    reader = new PetJsonReader("./data/testReaderEmptyWorkRoom.json");
    try {
        PetCollection petCollection = reader.read();
        assertNotNull(petCollection);
        System.out.println("Pet Collection Size: " + petCollection.getPets().size()); // Debug output
        assertEquals(0, petCollection.getPets().size()); // Expecting 0 pets in the empty file
    } catch (IOException e) {
        fail("Exception should not have been thrown");
    }
}


    @Test
    void testReadMalformedFile() {
        reader = new PetJsonReader("./data/testMalformedFile.json");
        try {
            reader.read();
            fail("IOException should have been thrown due to malformed JSON");
        } catch (IOException e) {
            // Expected behavior
        }
    }
}
