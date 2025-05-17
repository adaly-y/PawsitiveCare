# Pet Medical Record Management System

## What will the application do? 

The **Pet Medical Record Management System** will allow pet owners to store, manage, and track their pets' medical records. Users will be able to:
- Add new pets with their personal and medical details.
- View the medical history, vaccinations, medications, and allergies of each pet.
- Update pet records with new treatments or vaccinations.
- Search for pets based on name or medical condition.
- Save and load pet data to ensure progress is not lost between sessions.
- Provide a special view for pet sitters, displaying important medical details and care instructions for each pet.

## Who will use it and why is this important to me? 

This application is intended for pet owners who want to keep track of their pets' health records. It is also designed to assist pet sitters by providing a clear summary of the pet's medical details and care instructions when the pet owner is away. As a pet owner, I am passionate about animal care and believe that having a well-organized system for tracking pets' health is crucial for their well-being. 

## User Stories
- As a user, I want to add a new pet to the record app. 
- As a user, I want to view a list of pets if I have entered more than 1. 
- As a user, I want to select a pet and view the list of entered medical history of my pet.
- As a user, I want to update my pet medical record with new information. 
- As a user, I want to be able to delete an entered pet medical file.
- As a user, I want to be able to save my entered pet record.  
- As a user, I want to be able to be able to load my entered pet record. 

## Instructions for End User
- You can add a new pet by clicking the "Add Pet" button and filling out the pet details form.
- You can view all your pets in the "Pets List" panel, which displays all the entered pet names.
- Select a pet from the list to see its medical history, including vaccinations, allergies, and medications.
- You can update a pet's medical record by selecting it, editing the required section, and saving the changes.
- You can delete a pet's medical record by selecting it and clicking the "Delete Pet" button.
- You can save your entered pet records by clicking the "Save" button, which stores the data in a file.
- You can load your previously saved pet records by selecting the "Load" option when the application starts.

## Phase 4: Task 2

- Sun Mar 30 15:38:07 PDT 2025
- Pet 'pet1' added to collection
- Sun Mar 30 15:38:15 PDT 2025
- Pet 'pet2' added to collection
- Sun Mar 30 15:38:24 PDT 2025
- Pet 'pet3' added to collection
- Sun Mar 30 15:38:48 PDT 2025
- Pet 'pet1' removed from collection

## Phase 4: Task 3

- The Pet class currently holds medical records as a list of strings (vaccinations, allergies, medications, etc.). For further development, I would create a separate MedicalRecord class that could encapsulate all aspects of a pet's medical history. This would allow better encapsulation, and make it easier to manage and update the pet's medical data. It would also help with interface design. 
- Though unit testing exists for basic functionality, I would create additional tests that validate interactions with the persistence layer, or to verify the correct behavior when working with an empty data file.
- One issue I encountered throughout this project was that method length were constantly too long. For further development, I would break methods into smaller, more manageable helper methods that performed more specific tasks. 