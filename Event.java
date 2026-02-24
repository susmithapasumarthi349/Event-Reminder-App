import java.time.LocalDateTime; //date + time ni kalipi store chesthundhi
import java.time.format.DateTimeFormatter; // formats date/time into readable form

public class Event { // class decalration
    private String name; // event name ni store chesthundhi
    private LocalDateTime dateTime; // date & time ni store chesthundhi

    public Event(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    } // idhi manam oka kotha event create chesinappdu run avthundhi
    // assigns the parameter value to the class variable, this refers to current object

    public String getName() {
        return name;
    } // event name ni isthundhi

    public LocalDateTime getDateTime() {
        return dateTime;
    } // date & time ni isthundhi

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return name + " | " + dateTime.format(formatter);
    } // idhi event ni manam chadavagala format lo isthundhi
}

