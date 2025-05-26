/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.poe_part_1;

import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author hilario
 */
public class POE_part_1 {

    public static void main(String[] args) {

        boolean loggedIn = true;  // Simulated login success
        if (!loggedIn) {
            JOptionPane.showMessageDialog(null, "You must log in to send messages.");
            return;
        }

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");
        Message[] messages = new Message[5];
        int messageCount = 0;
        int totalMessages = Integer.parseInt(JOptionPane.showInputDialog("How many messages do you want to send?"));

        while (true) {
            String choice = JOptionPane.showInputDialog("Choose an option:\n1) Send Message\n2) Show recently sent messages\n3) Quit");

            switch (choice) {
                case "1":
                    if (messageCount >= totalMessages) {
                        JOptionPane.showMessageDialog(null, "Message limit reached.");
                        continue;
                    }

                    String recipient = JOptionPane.showInputDialog("Enter recipient cell number (+ format):");
                    String content = JOptionPane.showInputDialog("Enter message (max 250 characters):");

                    Message msg = new Message();
                    if (!msg.checkRecipientCell(recipient)) {
                        JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted.");
                        continue;
                    }

                    if (content.length() > 250) {
                        JOptionPane.showMessageDialog(null, "Message exceeds 250 characters by " + (content.length() - 250));
                        continue;
                    }

                    msg.generateMessage(recipient, content, messageCount);
                    String option = JOptionPane.showInputDialog("Choose option: Send / Store / Discard");
                    JOptionPane.showMessageDialog(null, msg.sendMessageOption(option));
                    JOptionPane.showMessageDialog(null, msg.printMessage());

                    messages[messageCount] = msg;
                    messageCount++;
                    break;

                case "2":
                    JOptionPane.showMessageDialog(null, "Coming Soon.");
                    break;

                case "3":
                    JOptionPane.showMessageDialog(null, "Quitting application.");
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid selection.");
            }
        }

    }
}
