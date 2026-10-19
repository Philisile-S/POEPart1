/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poepart1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Philisile Mavuso
 */
public class MessageTest {
    
    @Test 
    public void testMessageLengthSuccess() {
      SendMessage msg = new SendMessage("+27718693002","Hi Mike, can you join us for dinner tonight?", 0);
      assertTrue(msg.checkMessageLength());
    }
    
    @Test
    public void tsetMessageLengthFailure() {
      String longText= "A". repeat(260); //260 characters
      SendMessage msg = new SendMessage("27718693002", longText, 0);
      assertFalse(msg.checkMessageLength());
    }
    
    @Test 
    public void testRecipientCellSuccess() {
      SendMessage msg = new SendMessage("+27718693002", "Test message", 0);
      assertTrue(msg.checkRecipientCell());
    }
    
    @Test
    public void testRecipientCellFailure() {
      SendMessage msg = new SendMessage("08575975889", "Test message", 0); //Missing "+" international code
      assertFalse(msg.checkRecipientCell());
    }
    
    @Test
    public void testMessageHashGeneration() {
      SendMessage msg = new SendMessage("+27718693002", "Hi Keegan, did you receive the payment?", 0);
      //Note: Hash format incorporates first 2 digits of generated ID, Index 0, and 
      //first/last words in CAPS.
      assertNotNull(msg.createMessageHash());
      assertTrue(msg.createMessageHash().contains(":0:"));
    }
    
    @Test
    public void testMessageIDValidation() {
      SendMessage msg = new SendMessage("+27718693002", "Hello", 0);
      assertTrue(msg.checkMessageID());
    }
}
    
   