import java.io.Serializable; // lets the class be serializable (can save to file)
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a single appointment
 */
public class Appointment implements Serializable {
    private String appointmentType;
    private String notes;
    private LocalDate date;
    private LocalTime time;

    // constructor one: date and time default to current date/time
    public Appointment(String appointmentType, String notes) {
        this.appointmentType = appointmentType;
        this.notes = notes;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    // constructor two: date and time are specifically set (normal way)
    public Appointment(String appointmentType, String notes, LocalDate date, LocalTime time) {
        this.appointmentType = appointmentType;
        this.notes = notes;
        this.date = date;
        this.time = time;
    }

    // setters to potentially change appointment details
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // getters
    public String getAppointmentType() {
        return this.appointmentType;
    }

    public String getNotes() {
        return this.notes;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "\nAppointment Type: " + this.appointmentType +
                "\nDate: " + this.date +
                "\nTime: " + this.time +
                "\nNotes: " + this.notes;
    }

}