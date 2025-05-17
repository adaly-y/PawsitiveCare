package persistence;

import model.Pet;
import model.PetCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

public class PetJsonWriterTest {
    private PetJsonWriter writer;
    private PetCollection petCollection;

    @BeforeEach
    void setUp() {
        writer = new PetJsonWriter("./data/testWriterGeneralWorkroom.json");
        petCollection = new PetCollection();
        // Add a test pet to the collection
        Pet pet = new Pet("Max", "Dog", "Labrador", 5);
        pet.addVaccination("Rabies");
        pet.addAllergy("Peanuts");
        pet.addMedication("Antihistamine");
        pet.addDiet("Dry food");
        petCollection.addPet(pet);
    }

    @Test
    void testWriteValidFile() {
        try {
            writer.open();
            writer.write(petCollection);
            writer.close();

            // Verify the file exists and is not empty
            File file = new File("./data/testWriterGeneralWorkroom.json");
            assertTrue(file.exists());
            assertTrue(file.length() > 0);
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException should not have been thrown");
        }
    }

    @Test
    void testWriteAndReadBack() {
        try {
            writer.open();
            writer.write(petCollection);
            writer.close();

            // Read back the file and verify it matches the petCollection
            PetJsonReader reader = new PetJsonReader("./data/testWriterGeneralWorkroom.json");
            PetCollection readCollection = reader.read();
            assertEquals(1, readCollection.getPets().size());
            Pet pet = readCollection.getPets().get(0);
            assertEquals("Max", pet.getName());
            assertTrue(pet.getVaccinations().contains("Rabies"));
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }
}
