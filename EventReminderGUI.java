import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventReminderGUI extends JFrame {
    private EventManager manager;
    private DefaultTableModel tableModel;
    private JTable table;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public EventReminderGUI() {
        manager = new EventManager();

        setTitle("Event Reminder");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table
        tableModel = new DefaultTableModel(new String[]{"Event Name", "Date & Time"}, 0);
        table = new JTable(tableModel);
        refreshTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Event");
        JButton deleteButton = new JButton("Delete Event");

        addButton.addActionListener(e -> addEvent());
        deleteButton.addActionListener(e -> deleteEvent());

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Show reminders at startup
        showUpcomingReminders();

        setVisible(true);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Event e : manager.getEvents()) {
            tableModel.addRow(new Object[]{e.getName(), e.getDateTime().format(formatter)});
        }
    }

    private void addEvent() {
        String name = JOptionPane.showInputDialog(this, "Enter Event Name:");
        if (name == null || name.trim().isEmpty()) return;

        String dateTimeStr = JOptionPane.showInputDialog(this, "Enter Date & Time (dd-MM-yyyy HH:mm):");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
            manager.addEvent(new Event(name, dateTime));
            refreshTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format!");
        }
    }

    private void deleteEvent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            manager.deleteEvent(selectedRow);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an event to delete.");
        }
    }

    private void showUpcomingReminders() {
        java.util.List<Event> upcoming = manager.getUpcomingEventsWithin24Hours();
        if (!upcoming.isEmpty()) {
            StringBuilder msg = new StringBuilder("Upcoming Events within 24 hours:\n");
            for (Event e : upcoming) {
                msg.append("- ").append(e.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(this, msg.toString(), "Reminders", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EventReminderGUI::new);
    }
}
