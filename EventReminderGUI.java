import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventReminderGUI extends JFrame {

    private EventManager manager;
    private DefaultTableModel tableModel;
    private JTable table;

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public EventReminderGUI() {

        manager = new EventManager();

        setTitle("Event Reminder System");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        getContentPane().setBackground(new Color(245, 247, 250));

        // ================= TITLE =================
        JLabel titleLabel = new JLabel("Event Reminder System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // ================= TABLE =================
        tableModel = new DefaultTableModel(
                new String[]{"Event Name", "Date & Time"}, 0);

        table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        refreshTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(scrollPane, BorderLayout.CENTER);

        // ================= BUTTON PANEL =================
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 247, 250));

        JButton addButton = new JButton("Add Event");
        JButton deleteButton = new JButton("Delete Event");

        addButton.setBackground(new Color(108, 99, 255));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        deleteButton.setBackground(new Color(220, 53, 69));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        addButton.addActionListener(e -> addEvent());
        deleteButton.addActionListener(e -> deleteEvent());

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // ================= START SYSTEMS =================
        showUpcomingReminders();
        startReminderChecker();   // 🔔 Real-time alert system

        setVisible(true);
    }

    // ================= TABLE REFRESH =================
    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Event e : manager.getEvents()) {
            tableModel.addRow(new Object[]{
                    e.getName(),
                    e.getDateTime().format(formatter)
            });
        }
    }

    // ================= ADD EVENT =================
    private void addEvent() {

        String name = JOptionPane.showInputDialog(this, "Enter Event Name:");
        if (name == null || name.trim().isEmpty()) return;

        String dateTimeStr = JOptionPane.showInputDialog(
                this,
                "Enter Date & Time (dd-MM-yyyy HH:mm):"
        );

        try {
            LocalDateTime dateTime =
                    LocalDateTime.parse(dateTimeStr, formatter);

            manager.addEvent(new Event(name, dateTime));
            refreshTable();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid date format!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= DELETE EVENT =================
    private void deleteEvent() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow >= 0) {
            manager.deleteEvent(selectedRow);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select an event to delete.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // ================= REAL-TIME REMINDER CHECKER =================
    private void startReminderChecker() {

        int delay = 60000; // Check every 1 minute

        new javax.swing.Timer(delay, e -> {

            LocalDateTime now = LocalDateTime.now()
                    .withSecond(0)
                    .withNano(0);

            for (Event event : manager.getEvents()) {

                LocalDateTime eventTime = event.getDateTime()
                        .withSecond(0)
                        .withNano(0);

                if (eventTime.equals(now)) {

                    Toolkit.getDefaultToolkit().beep(); // 🔔 Beep sound

                    JOptionPane.showMessageDialog(
                            this,
                            "⏰ Event Reminder!\n\n" + event.getName(),
                            "Event Alert",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }

        }).start();
    }

    // ================= STARTUP UPCOMING REMINDERS =================
    private void showUpcomingReminders() {

        java.util.List<Event> upcoming =
                manager.getUpcomingEventsWithin24Hours();

        if (!upcoming.isEmpty()) {

            StringBuilder msg =
                    new StringBuilder("Upcoming Events within 24 hours:\n\n");

            for (Event e : upcoming) {
                msg.append("• ")
                        .append(e.toString())
                        .append("\n");
            }

            JOptionPane.showMessageDialog(
                    this,
                    msg.toString(),
                    "Reminders",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(
                    "javax.swing.plaf.nimbus.NimbusLookAndFeel"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(EventReminderGUI::new);
    }
}
