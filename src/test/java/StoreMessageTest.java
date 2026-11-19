/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.mycompany.poepart1;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class StoreMessageTest {

    private StoreMessage store;

   
    @TempDir
    Path tempDir;

    @BeforeEach
    public void setUp() {

        // Use a separate JSON file for testing
        String testFile = tempDir.resolve("testMessages.json").toString();

        store = new StoreMessage(testFile);

        // Add four stored test messages
        store.addMessage(
                "1111111111",
                "11:1:DIDCAKE",
                "+27838884567",
                "Did you get the cake?",
                "Stored",
                "Developer"
        );

        store.addMessage(
                "2222222222",
                "22:2:WHERETIME",
                "+27827776666",
                "Where are you? You are late! I have asked you to be on time.",
                "Stored",
                "Developer"
        );

        store.addMessage(
                "3333333333",
                "33:3:OKYOU",
                "+27827776666",
                "Ok, I am leaving without you.",
                "Stored",
                "Developer"
        );

        store.addMessage(
                "4444444444",
                "44:4:ITTIME",
                "+27838884567",
                "It is dinner time!",
                "Stored",
                "Developer"
        );
    }

    // TEST 1: Check that stored messages are correctly populated
    @Test
    public void testStoredMessagesArray() {

        assertEquals(4, store.getStoredMessage().length);

        assertTrue(Arrays.asList(store.getStoredMessage())
                .contains("Did you get the cake?"));

        assertTrue(Arrays.asList(store.getStoredMessage())
                .contains("It is dinner time!"));
    }

    // TEST 2: Display the longest stored message
    @Test
    public void testDisplayLongestMessage() {

        String expected =
                "Where are you? You are late! I have asked you to be on time.";

        String actual = store.displayLongestStoredMessage();

        assertEquals(expected, actual);
    }

    // TEST 3: Search for a stored message using its ID
    @Test
    public void testSearchMessageID() {

        String actual = store.searchStoredMessageID("4444444444");

        assertTrue(actual.contains("It is dinner time!"));
        assertTrue(actual.contains("+27838884567"));
    }

    // TEST 4: Search stored messages using the recipient's number
    @Test
    public void testSearchRecipient() {

        String actual = store.searchStoredRecipient("+27827776666");

        assertTrue(actual.contains(
                "Where are you? You are late! I have asked you to be on time."
        ));

        assertTrue(actual.contains(
                "Ok, I am leaving without you."
        ));
    }

    // TEST 5: Delete a stored message using its hash
    @Test
    public void testDeleteMessageUsingHash() {

        String messageHash = "22:2:WHERETIME";

        String result = store.deleteStoredMessage(messageHash);

        assertTrue(result.contains("successfully deleted"));

        assertEquals(3, store.getStoredMessage().length);

        assertFalse(Arrays.asList(store.getStoredMessage())
                .contains(
                    "Where are you? You are late! I have asked you to be on time."
                ));
    }

    // TEST 6: Display the complete stored message report
    @Test
    public void testDisplayMessageReport() {

        String report = store.displayStoredMessageReport();

        assertNotNull(report);

        assertTrue(report.contains("Message Hash"));
        assertTrue(report.contains("Recipient"));
        assertTrue(report.contains("Message"));

        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("It is dinner time!"));
    }
}