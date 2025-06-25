/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.poe_part_1;

import com.google.gson.Gson;
import javax.swing.JOptionPane;

/**
 *
 * @author hilar
 */
public class POE_part_1 {

    public static void main(String[] args) {
        Gson gson = new Gson();
        MessageManager messageManager = new MessageManager();

        // Registration with validation
        String firstName = JOptionPane.showInputDialog("Enter First Name:");
        String lastName = JOptionPane.showInputDialog("Enter Last Name:");

        String username;
        while (true) {
            username = JOptionPane.showInputDialog("Enter Username (max 5 characters and must include '_'):");
            if (username != null && username.length() <= 5 && username.contains("_")) break;
            JOptionPane.showMessageDialog(null, "Invalid username. Must be ≤ 5 characters and include '_'.");
        }

        String password;
        while (true) {
            password = JOptionPane.showInputDialog("Enter Password (min 8 characters, with capital letter, number, special char):");
            if (isValidPassword(password)) break;
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters, with a capital letter, number, and special character.");
        }

        String phoneNumber;
        while (true) {
            phoneNumber = JOptionPane.showInputDialog("Enter Phone Number (format: +27xxxxxxxxx):");
            if (phoneNumber != null && phoneNumber.matches("\\+27\\d{9}")) break;
            JOptionPane.showMessageDialog(null, "Invalid phone number. Must follow +27xxxxxxxxx format.");
        }

        Login user = new Login(firstName, lastName, username, password, phoneNumber);
        String registrationMessage = user.registerUser(username, password, phoneNumber);
        JOptionPane.showMessageDialog(null, registrationMessage);

        if (!registrationMessage.equals("User registered successfully.")) {
            JOptionPane.showMessageDialog(null, "Fix the errors above and try again.");
            return;
        }

        // Login
        while (true) {
            String loginUsername = JOptionPane.showInputDialog("Enter Username:");
            String loginPassword = JOptionPane.showInputDialog("Enter Password:");
            String loginMessage = user.returnLoginStatus(loginUsername, loginPassword);
            JOptionPane.showMessageDialog(null, loginMessage);
            if (loginMessage.equals("Login successful"));
            break;

        }

        // Main menu
        boolean running = true;
        while (running) {
            String menu = """
                    Choose an option:
                    1) Send messages
                    2) Search messages by recipient
                    3) Delete message by hash
                    4) Display longest message
                    5) Search message by ID
                    6) Display messages report
                    7) Quit
                    """;

            String input = JOptionPane.showInputDialog(menu);
            if (input == null) continue; // If user cancels input

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    String countStr = JOptionPane.showInputDialog("How many messages to send?");
                    try {
                        int count = Integer.parseInt(countStr);
                        messageManager.sendMessages(count);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid number.");
                    }
                }
                case 2 -> {
                    String recipient = JOptionPane.showInputDialog("Enter recipient number to search:");
                    messageManager.searchByRecipient(recipient);
                }
                case 3 -> {
                    String hash = JOptionPane.showInputDialog("Enter message hash to delete:");
                    messageManager.deleteByHash(hash);
                }
                case 4 -> messageManager.displayLongestMessage();
                case 5 -> {
                    String id = JOptionPane.showInputDialog("Enter message ID to search:");
                    messageManager.searchByID(id);
                }
                case 6 -> messageManager.displayReport();
                case 7 -> {
                    JOptionPane.showMessageDialog(null, "Exiting QuickChat. Goodbye!");
                    running = false;
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid option. Try again.");
            }
        }
        
    }
    
    private static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) return false;
        boolean hasUpper = false, hasDigit = false, hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else if (!Character.isLetterOrDigit(ch)) hasSpecial = true;
        }
        return hasUpper && hasDigit && hasSpecial;
    }
}
