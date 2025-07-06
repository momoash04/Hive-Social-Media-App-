# JavaFX Social Media Platform

A Java-based social media platform built using Object-Oriented Programming (OOP), JavaFX for GUI, and basic file serialization for data persistence. This project simulates core social media features such as posting, commenting, liking, user profiles, and messaging.

## 🚀 Features

- User Registration and Login
- Profile Picture, Bio, and Friends List
- Post creation with images and captions
- Like/Unlike Posts
- Comment on Posts
- Messaging System (Basic Chat UI)
- Search Users and Add/Remove Friends
- Edit Profile (Change Bio, Password, Profile Picture)
- Dynamic Feed showing Friends’ Posts
- File-based data storage using Java Serialization

---

## 🛠️ Technologies Used

- **Java**
- **JavaFX** - GUI development
- **FXML** - Scene structure and layout
- **Object-Oriented Programming (OOP)**
- **Serialization (.ser files)** - Data persistence

---

## 🗃️ Data Storage

All data is stored using Java Serialization:
- `users.ser` - Stores user data, profile images, bios, and posts.
- `posts.ser` - Stores post data, including comments and likes.
- Profile and post images are stored as byte arrays for compatibility with serialization.

---

## 🧪 How to Run

1. **Clone the repository**
2. Make sure Java and JavaFX are installed.
3. Open the project in an IDE like IntelliJ or Eclipse.
4. Ensure JavaFX SDK is configured correctly in project settings.
5. Run `Main.java`

---

## 🔐 Notes

- Image handling is done using `transient` and byte arrays to allow serialization.
- `Singleton.java` is used to hold global references to users and posts.
- No database is used—this app is purely file-based for demonstration purposes.
