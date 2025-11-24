# 📅 Event Reminder App

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-GUI-blue?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

A powerful and intuitive Java Swing-based desktop application to manage your events and never miss an important moment! Get timely reminders for all your upcoming events within 24 hours.

## ✨ Features

- 📝 **Add Events** - Create events with custom names and dates
- 🗑️ **Delete Events** - Remove events you no longer need
- ⏰ **Smart Reminders** - Automatic notifications for events within 24 hours
- 💾 **Persistent Storage** - Events are saved to a file and loaded automatically
- 🎨 **Clean GUI** - User-friendly interface built with Java Swing
- 📊 **Table View** - View all your events in an organized table format
- 📅 **Date Formatting** - Supports dd-MM-yyyy HH:mm format

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Java Runtime Environment (JRE)

### Installation

1. Clone the repository:
```bash
git clone https://github.com/susmithapasumarthi349/Event-Reminder-App.git
```

2. Navigate to the project directory:
```bash
cd Event-Reminder-App
```

3. Compile the Java files:
```bash
javac Event.java EventManager.java EventReminderGUI.java
```

4. Run the application:
```bash
java EventReminderGUI
```

## 📖 Usage

1. **Adding an Event**: Click the "Add Event" button, enter the event name and date-time in the format `dd-MM-yyyy HH:mm`
2. **Viewing Events**: All events are displayed in the table with their names and scheduled times
3. **Deleting an Event**: Select an event from the table and click "Delete Event"
4. **Getting Reminders**: When you launch the app, you'll automatically see a popup with all events scheduled within the next 24 hours

## 🏗️ Project Structure

```
Event-Reminder-App/
├── Event.java              # Event model class
├── EventManager.java       # Business logic and file I/O
├── EventReminderGUI.java   # Main GUI application
├── events.txt             # Persistent storage file (auto-generated)
├── README.md              # Project documentation
├── LICENSE                # MIT License
└── .gitignore            # Git ignore file
```

## 💡 How It Works

- **Event.java**: Represents an event with a name and LocalDateTime
- **EventManager.java**: Handles CRUD operations and file persistence
- **EventReminderGUI.java**: Creates the Swing interface and handles user interactions
- Events are stored in `events.txt` and automatically loaded on startup

## 🎯 Future Enhancements

- [ ] Add recurring events support
- [ ] Implement custom reminder times
- [ ] Add event categories and color coding
- [ ] Export events to calendar formats (iCal)
- [ ] Add search and filter functionality
- [ ] Implement sound notifications
- [ ] Add dark mode theme

## 🤝 Contributing

Contributions are welcome! Feel free to:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👤 Author

**Susmitha Pasumarthi**
- GitHub: [@susmithapasumarthi349](https://github.com/susmithapasumarthi349)

## 🌟 Show Your Support

Give a ⭐️ if you like this project!

## 📧 Contact

Have questions or suggestions? Feel free to reach out or open an issue!

---

**Made with ❤️ using Java Swing**
