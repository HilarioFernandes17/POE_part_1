/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.poe_part_1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hilar
 */
public class MessageManagerTest {
    
   private MessageManager manager;

    @Before
    public void setUp() {
        manager = new MessageManager();
        MessageManager.sentMessages.clear(); // Reset global sentMessages
    }

    @Test
    public void testAddMessage1_Sent() {
        Message m1 = new Message("Did you get the cake?", 27834557896L);
        manager.addMessage(m1);

        assertEquals(1, MessageManager.getTotalMessagesCount());
        assertEquals("Did you get the cake?", MessageManager.sentMessages.get(0).getMessage());
    }

    @Test
    public void testAddMessage2_Stored() {
        Message m2 = new Message("Where are you? You are late! I have asked you to be on time.", 27838884567L);
        manager.addMessage(m2);

        assertEquals(1, MessageManager.getTotalMessagesCount());
        assertTrue(MessageManager.sentMessages.get(0).getMessage().contains("late"));
    }

    @Test
    public void testMessage3_Disregarded_NotAdded() {
        // Disregarded => do not add
        assertEquals(0, MessageManager.getTotalMessagesCount());
    }

    @Test
    public void testAddMessage4_Sent() {
        Message m4 = new Message("It is dinner time !", 838884567L); // Developer = recipient
        manager.addMessage(m4);

        assertEquals(1, MessageManager.getTotalMessagesCount());
    }

    @Test
    public void testAddMessage5_Stored() {
        Message m5 = new Message("Ok, I am leaving without you.", 27838884567L);
        manager.addMessage(m5);

        assertEquals(1, MessageManager.getTotalMessagesCount());
        assertTrue(MessageManager.sentMessages.get(0).getMessage().contains("leaving"));
    }

    @Test
    public void testAllValidMessagesAddedCorrectly() {
        // Add valid messages (1,2,4,5)
        manager.addMessage(new Message("Did you get the cake?", 27834557896L));
        manager.addMessage(new Message("Where are you? You are late! I have asked you to be on time.", 27838884567L));
        manager.addMessage(new Message("It is dinner time !", 838884567L));
        manager.addMessage(new Message("Ok, I am leaving without you.", 27838884567L));

        assertEquals(4, MessageManager.getTotalMessagesCount());
        assertEquals(2, manager.getMessagesByRecipient(27838884567L).split("\n").length - 1); // 2 messages for this recipient
    }

    @Test
    public void testFindMessageById() {
        Message m = new Message("Dinner?", 111L);
        manager.addMessage(m);

        Message found = manager.findMessageById(m.getMessageId());
        assertNotNull(found);
        assertEquals("Dinner?", found.getMessage());
    }

    @Test
    public void testDeleteMessageById() {
        Message m = new Message("Temp", 222L);
        manager.addMessage(m);

        boolean deleted = manager.deleteMessageById(m.getMessageId());
        assertTrue(deleted);
        assertEquals(0, MessageManager.getTotalMessagesCount());
    }
    
}
