/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe_part_1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author hilar
 */
public class MessageManager {
    
     public static ArrayList<Message> sentMessages = new ArrayList<>();
    private ArrayList<Message> disregardedMessages = new ArrayList<>();
    private ArrayList<Message> disabledMessages = new ArrayList<>();
    private ArrayList<String> messageHashes = new ArrayList<>();
    private ArrayList<Long> messageIds = new ArrayList<>();

    public void initialize(int numberOfMessages) {
        Message.clearMessagesList();
        JOptionPane.showMessageDialog(null, "Ready to handle " + numberOfMessages + " messages.");
    }

    public void sendMessages(int count) {
        for (int i = 0; i < count; i++) {
            String text = JOptionPane.showInputDialog("Enter message text:");
            if (text == null) {
                return;
            }

            String recipientStr = JOptionPane.showInputDialog("Enter recipient phone number:");
            if (recipientStr == null) {
                return;
            }

            long recipient;
            try {
                recipient = Long.parseLong(recipientStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid recipient number. Try again.");
                i--; // retry the current message
                continue;
            }

            Message msg = new Message(text, recipient);
            msg.generateRandom();

            sentMessages.add(msg);
            messageIds.add(msg.getMessageId());
            messageHashes.add(msg.createMessageHash());

            JOptionPane.showMessageDialog(null,
                    "Message sent!\nID: " + msg.getMessageId() + "\nHash: " + msg.createMessageHash());
        }
    }

    public void searchByRecipient(String recipientStr) {
        long recipient;
        try {
            recipient = Long.parseLong(recipientStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid recipient number format.");
            return;
        }

        StringBuilder results = new StringBuilder();
        for (Message m : sentMessages) {
            if (m.getRecipient() == recipient) {
                results.append("ID: ").append(m.getMessageId()).append(", Text: \"").append(m.getMessage()).append("\"\n");
            }
        }

        if (results.length() == 0) {
            JOptionPane.showMessageDialog(null, "No messages found for recipient " + recipient);
        } else {
            JOptionPane.showMessageDialog(null, results.toString(), "Messages for " + recipient, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void deleteByHash(String hash) {
        Message toDelete = null;
        for (Message m : sentMessages) {
            if (m.createMessageHash().equals(hash)) {
                toDelete = m;
                break;
            }
        }
        if (toDelete != null) {
            sentMessages.remove(toDelete);
            JOptionPane.showMessageDialog(null, "Deleted message with hash: " + hash);
        } else {
            JOptionPane.showMessageDialog(null, "No message found with hash: " + hash);
        }
    }

    public void displayLongestMessage() {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages to display.");
            return;
        }
        Message longest = sentMessages.stream()
                .max(Comparator.comparingInt(m -> m.getMessage().length()))
                .orElse(null);

        if (longest != null) {
            JOptionPane.showMessageDialog(null,
                    "Longest message:\n" + longest.getMessage()
                    + "\nLength: " + longest.getMessage().length());
        }
    }

    public void searchByID(String idStr) {
        long id;
        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid message ID format.");
            return;
        }

        for (Message m : sentMessages) {
            if (m.getMessageId() == id) {
                JOptionPane.showMessageDialog(null,
                        "Found message:\nID: " + m.getMessageId()
                        + "\nText: " + m.getMessage()
                        + "\nRecipient: " + m.getRecipient());
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "No message found with ID: " + id);
    }

    public void displayReport() {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages to report.");
            return;
        }

        StringBuilder report = new StringBuilder("Messages Report:\n");
        for (Message m : sentMessages) {
            report.append("ID: ").append(m.getMessageId())
                    .append(", Recipient: ").append(m.getRecipient())
                    .append(", Message: ").append(m.getMessage()).append("\n");
        }

        JTextArea textArea = new JTextArea(report.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Messages Report", JOptionPane.INFORMATION_MESSAGE);
    }

    public Message getLongestMessage() {
        Message longest = sentMessages.stream()
                .max(Comparator.comparingInt(m -> m.getMessage().length()))
                .orElse(null);

        if (longest != null) {
            JOptionPane.showMessageDialog(null,
                    "Longest message: " + longest.getMessage()
                    + "\nLength: " + longest.getMessage().length());
        }

        return longest;
    }

    public void addMessage(Message message) {
        sentMessages.add(message);
        messageIds.add(message.getMessageId());
        messageHashes.add(message.getMessageHash());
    }

    public static int getTotalMessagesCount() {
        return sentMessages.size();
    }

    public void deleteAllMessages() {
        sentMessages.clear();
    }

    public Message findMessageById(long messageId) {
        for (Message m : sentMessages) {
            if (m.getMessageId() == messageId) {
                return m;
            }
        }
        return null;
    }

    public String getMessagesByRecipient(long recipient) {
        StringBuilder messages = new StringBuilder();
        for (Message m : sentMessages) {
            if (m.getRecipient() == recipient) {
                messages.append("\n").append(m.getMessage());
            }
        }
        return messages.toString();
    }

    public boolean deleteMessageById(long messageId) {
        for (Message m : sentMessages) {
            if (m.getMessageId() == messageId) {
                sentMessages.remove(m);
                return true;
            }
        }
        return false;
    }
}
