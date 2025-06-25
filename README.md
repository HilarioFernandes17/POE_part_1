QuickChat Java Chat Application

With the ability to store and retrieve messages, this project is a Java-based console chat application that mimics a user registration, login, message creation, and management process.

Features

Part 1: Registration and Login

-User registration with username, password, and South African cell number.
-Username must contain an underscore and be no longer than five characters.
-Password must be at least 8 characters long, include a capital letter, a number, and a special character.
-Cell number must match the South African international format (e.g. +27834567890).
-Login verification with authentication message.

Part 2: Sending Messages

-Logged-in users can access a QuickChat menu:

	-Send messages
	-View placeholder for sent messages ("Coming Soon")
	-Quit

Each message includes:

-Unique 10-digit Message ID
-Recipient number (validated)
-Message content (max 250 characters)
-Auto-generated Message Hash (e.g., 00:0:HITHANKS)
-Options to send, disregard, or store the message
-Stored messages are saved in a JSON file.

Part 3: Message Management

Sent, disregarded, and stored messages are kept in separate arrays.

Message management features:

-Display all sent messages
-Display the longest sent message
-Search by Message ID
-Search messages by recipient number
-Delete a message by hash
-Display a full report of sent messages

Technologies Used

-Java (SE)
-Swing (JOptionPane for dialogs)
-Gson (for JSON handling)

Prerequisites

-JDK 17+
-An IDE like NetBeans or IntelliJ IDEA
-Git and GitHub for version control

Setup Instructions

-Clone the repository from GitHub (make sure it's a private repo inside your assigned GitHub Classroom organization).
-Open the project in your preferred Java IDE.
-Ensure the Gson library is added to your project dependencies.
-Compile and run the MainApp class.
-Running Tests

Unit tests should be written using JUnit to verify:

-Username formatting
-Password complexity
-Cell number format
-Message field validation
-Message hash generation
-Search and deletion logic
-Automation & CI/CD
-GitHub Actions should be configured to automatically run tests on every commit using a .github/workflows/java.yml file.



Author: Hilario F A Malavoloneque
Stundant Number: ST10497336
Date: 25 June 2025
