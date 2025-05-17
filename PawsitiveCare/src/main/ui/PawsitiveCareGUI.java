package ui;

import model.Pet;
import model.PetCollection;
import persistence.PetJsonReader;
import persistence.PetJsonWriter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class PawsitiveCareGUI extends JFrame {
    private static final String JSON_STORE = "./data/petData.json";
    private PetCollection petCollection;
    private PetJsonReader reader;
    private PetJsonWriter writer;
    private JButton addPetButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton viewPetsButton;
    private JButton deletePetButton; 
    private JButton updatePetButton;
    private JTextArea petDisplayArea;
    private JPanel buttonPanel;

    @SuppressWarnings("methodlength")

    // Effects: Create a GUI for the PetRecordApp, now named Pawsitive Care 
    public PawsitiveCareGUI() {
        petCollection = new PetCollection();
        reader = new PetJsonReader(JSON_STORE);
        writer = new PetJsonWriter(JSON_STORE);
        setTitle("Pet Record Application");

        // Setup main window layout
        setLayout(new BorderLayout());

        petDisplayArea = new JTextArea(15, 40);
        petDisplayArea.setEditable(false);
        add(new JScrollPane(petDisplayArea), BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        // Add buttons
        addPetButton = new JButton("Add Pet");
        saveButton = new JButton("Save Pet Data");
        loadButton = new JButton("Load Pet Data");
        viewPetsButton = new JButton("View Pets");
        deletePetButton = new JButton("Delete Pet");
        updatePetButton = new JButton("Update Pet Record");

        buttonPanel.add(addPetButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(viewPetsButton);
        buttonPanel.add(deletePetButton);
        buttonPanel.add(updatePetButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        addPetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPet();
            }
        });

        deletePetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePet();
            }
        });
        
        updatePetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePetMedicalRecord();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePetData();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPetData();
            }
        });

        viewPetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewPets();
            }
        });

        setPreferredSize(new Dimension(612, 306));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printEventLog();
                dispose();
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // Effect: Add a pet to the Collection by user filling in name, species, breed, age
    private void addPet() {
        String name = JOptionPane.showInputDialog(this, "Enter pet's name:");
        String species = JOptionPane.showInputDialog(this, "Enter pet's species:");
        String breed = JOptionPane.showInputDialog(this, "Enter pet's breed:");
        int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter pet's age:"));

        Pet pet = new Pet(name, species, breed, age);
        petCollection.addPet(pet);
        JOptionPane.showMessageDialog(this, "Pet added!");
    }

    // Effect: Remove a pet from the entered list 
    private void deletePet() {
        String name = JOptionPane.showInputDialog(this, "Enter the pet's name to delete:");
        Pet petToDelete = null;
    
        for (Pet pet : petCollection.getPets()) {
            if (pet.getName().equals(name)) {
                petToDelete = pet;
                break;
            }
        }
    
        if (petToDelete != null) {
            petCollection.removePet(petToDelete);
            JOptionPane.showMessageDialog(this, "Pet record deleted!");
        } else { 
            JOptionPane.showMessageDialog(this, "Pet not found."); 
        }
    }

    // Effects: update a Pet's medical record by searching for its name 
    private void updatePetMedicalRecord() {
        String name = JOptionPane.showInputDialog(this, "Enter the pet's name to update:");
        Pet petToUpdate = null;
        
        for (Pet pet : petCollection.getPets()) {
            if (pet.getName().equals(name)) {
                petToUpdate = pet;
                break;
            }
        }

        if (petToUpdate != null) {
        // Prompt user for medical records
            String allergies = JOptionPane.showInputDialog(this, "Enter allergies (comma separated):");
            String medications = JOptionPane.showInputDialog(this, "Enter medications (comma separated):");
            String diet = JOptionPane.showInputDialog(this, "Enter diet (comma separated):");
            String vaccinations = JOptionPane.showInputDialog(this, "Enter vaccinations (comma separated):");

        // Update the pet's medical record
            petToUpdate.setAllergies(Arrays.asList(allergies.split(", ")));
            petToUpdate.setMedications(Arrays.asList(medications.split(", ")));
            petToUpdate.setDiet(Arrays.asList(diet.split(", ")));
            petToUpdate.setVaccinations(Arrays.asList(vaccinations.split(", ")));
            
            JOptionPane.showMessageDialog(this, "Pet medical record updated!");
        } else {
            JOptionPane.showMessageDialog(this, "Pet not found.");
        }
    }

    // Effects: Save Pet data as Json file 
    private void savePetData() {
        try {
            writer.open();
            writer.write(petCollection);
            writer.close();
            JOptionPane.showMessageDialog(this, "Pet data saved successfully!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to save pet data.");
        }
    }

    // Effects: Load previous saved Pet Json data 
    private void loadPetData() {
        try {
            petCollection = reader.read();
            JOptionPane.showMessageDialog(this, "Pet data loaded successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to load pet data.");
        }
    }

    // Effects: view a list of pets entered 
    private void viewPets() {
        petDisplayArea.setText("");  
        System.out.println("Pets in collection: " + petCollection.getPets().size());
        for (Pet pet : petCollection.getPets()) {
            petDisplayArea.append("Name: " + pet.getName() + "\n");
            petDisplayArea.append("Species: " + pet.getSpecies() + "\n");
            petDisplayArea.append("Breed: " + pet.getBreed() + "\n");
            petDisplayArea.append("Age: " + pet.getAge() + "\n");
            petDisplayArea.append("Vaccinations: " + String.join(", ", pet.getVaccinations()) + "\n");
            petDisplayArea.append("Allergies: " + String.join(", ", pet.getAllergies()) + "\n");
            petDisplayArea.append("Medications: " + String.join(", ", pet.getMedications()) + "\n");
            petDisplayArea.append("Diet: " + String.join(", ", pet.getDiet()) + "\n");
            petDisplayArea.append("\n");
        }
    }

    private void printEventLog() {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event.toString());
        }
    }

    public static void main(String[] args) {
        new PawsitiveCareGUI();
    }
}
