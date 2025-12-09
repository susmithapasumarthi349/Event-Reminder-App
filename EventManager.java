import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EventManager {
    private ArrayList<Event> events = new ArrayList<>();
    private static final String FILE_NAME = "events.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public EventManager() {
        loadEventsFromFile();
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        events.add(event);
        saveAllEventsToFile();
    }

    public void deleteEvent(int index) {
        if (index >= 0 && index < events.size()) {
            events.remove(index);
            saveAllEventsToFile();
        }
    }

    public ArrayList<Event> getUpcomingEventsWithin24Hours() {
        ArrayList<Event> upcoming = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (Event e : events) {
            if (e.getDateTime().isAfter(now) && e.getDateTime().isBefore(now.plusDays(1))) {
                upcoming.add(e);
            }
        }
        return upcoming;
    }

    private void saveAllEventsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Event e : events) {
                writer.write(e.getName() + ";" + e.getDateTime().format(formatter));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving events.");
        }
    }

    private void loadEventsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    String name = parts[0];
                    LocalDateTime dateTime = LocalDateTime.parse(parts[1], formatter);
                    events.add(new Event(name, dateTime));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading events.");
        }
    }
}

