/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.mycompany.poepart1;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class StoreMessageTest {

    private StoreMessage store;

    @BeforeEach
    public void setUp() {
        store = new StoreMessage();

        // Add the four test messages
        store.addMessage("0838884567",
                "Did you get the cake?");

        store.addMessage("+27838884567",
                "Where are you? You are late! I have asked you to be on time.");

        store.addMessage("+27838884567",
                "Ok, I am leaving without you.");

        store.addMessage("0838884567",
                "It is dinner time!");
    }

    // TEST 1: Sent Messages array correctly populated
    @Test
    public void testSentMessagesArray() {

        assertEquals(4, store.getTotalMessages());

        assertTrue(store.getAllMessages().contains(
                "Did you get the cake?"));

        assertTrue(store.getAllMessages().contains(
                "It is dinner time!"));
    }

    // TEST 2: Display the longest message
    @Test
    public void testDisplayLongestMessage() {

        String expected =
                "Where are you? You are late! I have asked you to be on time.";

        assertEquals(expected, store.getLongestMessage());
    }

    // TEST 3: Search for message ID
    @Test
    public void testSearchMessageID() {

        String expected = "It is dinner time!";

        String actual = store.searchMessageID("0838884567");

        assertEquals(expected, actual);
    }

    // TEST 4: Search messages using recipient
    @Test
    public void testSearchRecipient() {

        String expected =
                "Where are you? You are late! I have asked you to be on time."
                + "Ok, I am leaving without you.";

        String actual = store.searchRecipient("+27838884567");

        assertTrue(actual.contains(
                "Where are you? You are late! I have asked you to be on time."));

        assertTrue(actual.contains(
                "Ok, I am leaving without you."));
    }

    // TEST 5: Delete a message using its hash
    @Test
    public void testDeleteMessageUsingHash() {

        String messageHash = store.getMessageHash(1);

        assertTrue(store.deleteMessage(messageHash));

        assertFalse(store.getAllMessages().contains(
                "Where are you? You are late! I have asked you to be on time."));

        assertEquals(3, store.getTotalMessages());
    }

    // TEST 6: Display complete message report
    @Test
    public void testDisplayMessageReport() {

        String report = store.displayReport();

        assertNotNull(report);

        assertTrue(report.contains("Message Hash"));
        assertTrue(report.contains("Recipient"));
        assertTrue(report.contains("Message"));

        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("It is dinner time!"));
    }
}