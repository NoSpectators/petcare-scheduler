import java.io.Serializable; // allows object to be serialized and deserialized (written to file and vice-versa)
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Pet registered to be registered for an Appointment
 */

public class Pet implements Serializable {
    private String id;
    private String name;
    private String breed; // or species
    private int age;
    private String ownerName;
    private String contactInfo;
    private LocalDate registrationDate;
    private List<Appointment> appointments;

    // master constructor
    public Pet(String id, String name, String breed, int age, String ownerName, String contactInfo) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.ownerName = ownerName;
        this.contactInfo = contactInfo;
        // hardcoded logic
        this.registrationDate = LocalDate.now(); // initialized to moment of new Pet object creation
        this.appointments = new ArrayList<>(); // initialized (empty, not null)
    }

    // minimal constructor (passes default values age, ownerName, contactInfo to master constructor)
    public Pet(String id, String name, String breed) {
        this(id, name, breed, 0, "N/A", "N/A");
    }

    // getters
    public String getId() { return this.id; }
    public String getName() { return this.name; }
    public String getBreed() { return this.breed; }
    public int getAge() { return this.age; }
    public String getOwnerName() { return this.ownerName; }
    public String getContactInfo() { return this.contactInfo; }
    public LocalDate getRegistrationDate() { return this.registrationDate; }
    public List<Appointment> getAppointments() { return this.appointments; }

    // setter
    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    @Override
    public String toString() {
        return "\nID: " + this.getId() +
                "\nName: " + this.getName() +
                "\nBreed: " + this.getBreed() +
                "\nAge: " + this.getAge() +
                "\nOwner: " + this.getOwnerName() +
                "\nContact Info: " + this.getContactInfo() +
                "\nRegistration Date: " + this.getRegistrationDate();
    }

}