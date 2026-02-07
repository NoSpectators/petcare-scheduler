import java.io.*;
import java.util.*;
import java.time.LocalDate;

/**
 * Main app to run the Pet Care Scheduler
 */

public class PetCareScheduler {

    private static Scanner scanner = new Scanner (System.in);
    private static Map<String, Pet> pets = new HashMap<>(); // hashmap for Pets

    public static void main(String[] args) {
        // loadPetsFromFile(); // load previously saved Pet data (serialization) for persistence between runs
        boolean running = true;
        while (running) {
            System.out.println("\n=== Pet Care Scheduler ===");
            System.out.println("1. Register Pet");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. Display Pets");
            System.out.println("4. Display Pet Appointments");
            System.out.println("5. Generate Reports");
            System.out.println("6. Save and Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            // the -> syntax automaticallys inserts "break" statement after calling function
            switch (choice) {
                case "1" -> registerPet();
//                case "2" -> schedulePetAppointment();
//                case "3" -> displayPets();
//                case "4" -> displayPetAppointments();
//                case "5" -> generateReports();
                case "6" -> {
                    // savePetsToFile();
                    running = false;
                    System.out.println("Data saved. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please select 1 - 6.");
            }
        }
    }

    private static void registerPet() {
        // promt user to enter a unique household ID
        System.out.print("Enter Pet ID: ");
        String id = scanner.nextLine().trim();

        // check if a pet with this id already exists
        if (pets.containsKey(id)) {
            System.out.println("Error: Pet ID already exists.");
            return; // stop execution and return early
        }

        // name
        System.out.print("Enter Pet name: ");
        String name = scanner.nextLine().trim();

        // breed
        System.out.println("Enter Pet breed: ");
        String breed = scanner.nextLine().trim();

        // age
        System.out.println("Enter Pet Age: ");
        int age = Integer.parseInt(scanner.nextLine().trim());
        if (age < 0) {
            System.out.println("invalid age.");
            return;
        }

        // owner name
        System.out.println("Enter Pet Owner Name: ");
        String ownerName = scanner.nextLine().trim();

        // contact info
        System.out.println("Enter Contact Information: ");
        String contactInfo = scanner.nextLine().trim();

        // create a new HouseHold object using the inputs
        Pet pet = new Pet(id, name, breed, age, ownerName, contactInfo);

        // add the new household to the households map
        pets.put(id, pet);

        // confirm to the user the household registration
        System.out.println("Pet registered successfully on " + pet.getRegistrationDate());
    }

    /*
    private static void logRecyclingEvent() {
        // prompt user for household id
        System.out.println("Enter household ID: ");
        String id = scanner.nextLine().trim();

        // look up household in hashmap by id
        Household household = households.get(id);

        // if not found show error and exit
        if (household == null) {
            System.out.println("Error: Household ID not found.");
            return;
        }

        // ask user for material type they recycled
        System.out.println("Enter material type (plastic/glass/metal/paper): ");
        String material = scanner.nextLine();

        double weight = 0.0;
        // loop until a valid weight is entered
        while (true) {
            try {
                System.out.print("Enter weight of material recycled (in kg): ");
                weight = Double.parseDouble(scanner.nextLine().trim());

                if (weight <= 0) throw new IllegalArgumentException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid weight. Must be a positive number.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid weight. Must be a positive number.");
            }

        }

        // create new RecyclingEvent using the material and weight
        RecyclingEvent event = new RecyclingEvent(material, weight);

        // add event to RecyclingEvents list
        household.addEvent(event);

        // show success message
        System.out.println("Recycling event logged! Points earned: " + event.getEcoPoints());
    }

    private static void displayHouseholds() {
        // check if households map is empty
        if (households.isEmpty()) {
            System.out.println("No households registered.");
            return; // exit early
        }

        System.out.println("\nRegistered Households: ");
        for (Household h : households.values()) {
            System.out.println(h);
        }
    }

    private static void displayHouseholdEvents() {
        // prompt user for household id
        System.out.println("Enter household ID: ");
        String id = scanner.nextLine().trim();

        // look up household in hashmap by id
        Household household = households.get(id);

        // if not found show error and exit
        if (household == null) {
            System.out.println("Error: Household ID not found.");
            return;
        }

        // print all recycling events
        System.out.println("\nRecycling Events for " + household.getName() + ":");
        if (household.getEvents().isEmpty()) {
            System.out.println("No events logged.");
            return;
        }
        for (RecyclingEvent e : household.getEvents()) {
            System.out.println(e); // the stringified version (calls toString())
        }

        // print total weight recycled by household
        System.out.println("Total weight recycled: " + household.getTotalWeight() + " kg");

        // print total eco points earned
        System.out.println("Total eco points earned: " + household.getTotalPoints() + " points");
    }

    private static void generateReports() {
        if (households.isEmpty()) {
            System.out.println("No households registered for eco-points program.");
            return;
        }

        // find household with hightest points
        Household top = null;
        for (Household h : households.values()) {
            if (top == null || h.getTotalPoints() > top.getTotalPoints()) {
                top = h;
            }
        }
        System.out.println("\nHousehold with highest eco points: " +
                           "\nID: " + top.getId() +
                           "\nName: " + top.getName() +
                           "\nPoints: " + top.getTotalPoints());

        // calculate total community recycling weight
        double totalWeight = 0.0;
        for (Household h : households.values()) {
            totalWeight += h.getTotalWeight();
        }

        System.out.println("Total Community Recycling Weight: " + totalWeight + " kg");
    }

    private static void saveHouseholdsToFile() {
        try {
            // create a FileOutputStream to write to the file named "households.ser"
            ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream("households.ser"));

            // write the entire households map to the file
            out.writeObject(households);
        } catch (IOException e) {
            System.out.println("Error: saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked") // suppresses unchecked cast warning when reading the object
    private static void loadHouseholdsFromFile() {
        // use a try-with-resources block to automatically close the input stream
        try (
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("households.ser"));
        ){
            // read the object from the file and cast it back to the correct type
            households = (Map<String, Household>) in.readObject();

            // confirmation message to let the user know data was loaded
            System.out.println("Household data loaded.");
        } catch(FileNotFoundException e) {
            System.out.println("No saved data found. Starting fresh.");
        } catch(IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    } */

}