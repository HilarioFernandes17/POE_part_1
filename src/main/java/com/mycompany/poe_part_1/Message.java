/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe_part_1;
import javax.swing.*;
import java.util.Random;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author hilar
 */
public class Message {
    
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;

    public boolean checkMessageID(String messageID) {
        return messageID.length() <= 10;
    }

    public boolean checkRecipientCell(String cellNumber) {
        return cellNumber.startsWith("+") && cellNumber.length() <= 13;
    }

    public String createMessageHash() {
        String[] words = message.trim().split("\\s+");
        String first = words[0];
        String last = words[words.length - 1];
        String hash = messageID.substring(0, 2) + ":" + messageNumber + ":" + first + last;
        return hash.toUpperCase();
    }

    public String sendMessageOption(String option) {
        switch (option.toLowerCase()) {
            case "send":
                return "Message successfully sent.";
            case "store":
                return "Message successfully stored.";
            case "discard":
                return "Press 0 to delete message.";
            default:
                return "Invalid option.";
        }
    }

    public String printMessage() {
        return "Message ID: " + messageID +
               "\nMessage Hash: " + messageHash +
               "\nRecipient: " + recipient +
               "\nMessage: " + message;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public void generateMessage(String recipient, String message, int messageCount) {
        this.recipient = recipient;
        this.message = message;
        this.messageNumber = messageCount;
        this.messageID = String.format("%010d", new Random().nextInt(1_000_000_000));
        this.messageHash = createMessageHash();
    }
    
    public void storeMessage() {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();  // Create a Gson object with formatting

    try (FileWriter writer = new FileWriter("stored_messages.json", true)) {
        gson.toJson(this, writer);  // 'this' refers to the current Message object
        System.out.println("Message successfully saved to JSON.");
    } catch (IOException e) {
        System.out.println("Error saving message: " + e.getMessage());
    }
}
    
}
