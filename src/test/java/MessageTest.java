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
      assertEquals("Message ready to send.",msg.vaildateMessageLength());
    } 
    
    @Test
    public void testMessageLengthFailure() {
      String longText= "A". repeat(260); //260 characters (10 over limit)
      SendMessage msg = new SendMessage("+27718693002", longText, 0);
      assertEquals("Message exceeds 250 characters by 10; please reduce the size.",msg.vaildateMessageLength());
    }
    
    @Test 
    public void testRecipientCellSuccess() {
      SendMessage msg = new SendMessage("+27718693002", "Test message", 0);
      assertEquals("Cell phone number successfully captured.", msg.validateRecipientCell());
    }
    
    @Test
    public void testRecipientCellFailure() {
      SendMessage msg = new SendMessage("08575975889", "Test message", 0); //Missing "+" international code
      assertEquals("Cell phone number is incorrectly formatted or does not contain"
              + " an international code. Please correct the number and try again", msg.validateRecipientCell());
    }
    
    @Test
    public void testMessageHashGeneration() {
      SendMessage msg = new SendMessage("+27718693002", "Hi tonight", 0);
      //Note: Hash format incorporates first 2 digits of generated ID, Index 0, and 
      //first/last words in CAPS.
      assertNotNull(msg.createMessageHash());
      assertTrue(msg.createMessageHash().contains(":0:"));
    }
    
    @Test
    public void testMessageIDValidation() {
      SendMessage msg = new SendMessage("+27718693002", "Hello", 0);
      assertTrue(msg.checkMessageID());
      assertNotNull(msg.getMessageID());
    }
    
    @Test 
    public void testMessageActionSend(){
       SendMessage msg = new SendMessage("+27718693002", "Hello", 0);
     assertEquals("Message successfully sent.", msg.processMessageAction(1));
    }
    
    @Test 
    public void testMessageActionDisregard(){
      SendMessage msg = new SendMessage("+27718693002", "Hello", 0);
      assertEquals("Press 0 to delete the message.", msg.processMessageAction(2));
    }
    
    @Test
    public void testMessageActionStore(){
    SendMessage msg = new SendMessage("+27718693002", "Hello", 0);
    assertEquals("Message successfully stored.", msg.processMessageAction(3));
    }
}
    
   