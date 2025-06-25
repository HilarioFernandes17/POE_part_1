/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe_part_1;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author hilar
 */
public final class Message {
    private long messageId = generateRandom();
    private final String message;
    private final long recipient;
    private final String hash;


    // Static list of all messages
    private static final ArrayList<Message> messagesList = new ArrayList<>();

    // Constructor
    public Message( String message, long recipient) {
        this.message = message;
        this.recipient = recipient;
        this.hash = createMessageHash();
        messagesList.add(this);
    }

    // Getters

    public long getMessageId() {
        return messageId;
    }

    public String getMessage() {
        return message;
    }

    public long getRecipient() {
        return recipient;
    }

    public static ArrayList<Message> getMessagesList() {
        return messagesList;
    }

    public static void clearMessagesList() {
        messagesList.clear();
    }

    // Generates a random 10-digit message ID
    public long generateRandom() {
        Random random = new Random();
        long id = 1000000000L + (long)(random.nextDouble() * 8999999999L);
        this.messageId = id;
        return id;
    }
    

    // Creates a hash based on the message content & ID
    public String createMessageHash() {
        String[] words = message.split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        String prefix = "";
        String hash;
        if(String.valueOf(messageId).length()<2){ prefix = String.valueOf(messageId);}else{ prefix = String.valueOf(messageId).substring(0, 2);}

            hash = prefix + ":" + MessageManager.getTotalMessagesCount() +":"+ firstWord+lastWord;

        return hash;
    }
    public String getMessageHash(){return hash;}

    // Returns all messages as a JSON string
    public static String getMessagesAsJsonString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(messagesList);
    }

    public static void printMessagesAsJson() {
        Gson gson = new Gson();
        StringBuilder jsonOutput = new StringBuilder("Recently Sent Messages (JSON):\n\n");
        for (Message m : messagesList) {
            jsonOutput.append(gson.toJson(m)).append("\n");
        }
        JOptionPane.showMessageDialog(null, jsonOutput.toString());
    }
}

