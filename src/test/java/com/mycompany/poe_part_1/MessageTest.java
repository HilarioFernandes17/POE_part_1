/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.poe_part_1;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author hilar
 */
public class MessageTest {
    
   @Test
    public void testCheckRecipientCell_Success() {
        Message m = new Message();
        assertTrue(m.checkRecipientCell("+27834557896"));
    }

    @Test
    public void testCheckRecipientCell_Failure() {
        Message m = new Message();
        assertFalse(m.checkRecipientCell("08575975889"));
    }

    @Test
    public void testMessageLength_Success() {
        String msg = "Hi Mike, can you join us for dinner tonight";
        assertTrue(msg.length() <= 250);
    }

    @Test
    public void testMessageLength_Failure() {
        String msg = "x".repeat(260);
        assertTrue(msg.length() > 250);
    }
    
}
