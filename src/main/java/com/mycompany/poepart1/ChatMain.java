/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart1;
import java.util.Scanner;
/**
 *
 * @author Philisile Mavuso
 */
public class ChatMain {
    // One StoreMessage object manages all message arrays and JSON data
    private static StoreMessage store = new StoreMessage();

    // Start QuickChat after successful login
    public static void start(Scanner scanner, String username) {

        int choice = 0;

        System.out.println("\n================================");
        System.out.println("       WELCOME TO QUICKCHAT");
        System.out.println("================================");

        do {

            System.out.println("\n--------- MAIN MENU ---------");
            System.out.println("1. Send Messages");
            System.out.println("2. Show Recently Sent Messages");
            System.out.println("3. Quit");
            System.out.println("4. Stored Messages");
            System.out.print("Choose an option: ");

            choice = readInteger(scanner);

            switch (choice) {

                case 1:
                    sendMessages(scanner, username);
                    break;

                case 2:
                    displaySentMessages();
                    break;

                case 3:
                    System.out.println("Exiting QuickChat. Goodbye!");
                    break;

                case 4:
                    storedMessagesMenu(scanner);
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1 to 4.");
                    break;
            }

        } 
        while (choice != 3);
    }

    // Read an integer safely
    private static int readInteger(Scanner scanner) {

        while (!scanner.hasNextInt()) {

            System.out.print("Invalid input. Please enter a number: ");
            scanner.nextLine();
        }

        int number = scanner.nextInt();
        scanner.nextLine();

        return number;
    }

    // Send, store or disregard messages
    private static void sendMessages(Scanner scanner, String username) {

        System.out.print("Enter number of messages to process: ");
        int numMessages = readInteger(scanner);

        if (numMessages <= 0) {
            System.out.println("Please enter at least one message.");
            return;
        }

        for (int i = 0; i < numMessages; i++) {

            System.out.println("\n------- MESSAGE " + (i + 1) + " -------");

            System.out.print("Enter recipient cellphone number (+27...): ");
            String recipient = scanner.nextLine();

            System.out.print("Enter your message (maximum 250 characters): ");
            String text = scanner.nextLine();

            // Create SendMessage object
            SendMessage msg = new SendMessage(recipient, text, i + 1);

            // Set the logged-in user as sender
            msg.setSender(username);

            // Validate recipient
            if (!msg.checkRecipientCell()) {

                System.out.println(msg.validateRecipientCell());
                System.out.println("Message was not processed.");
                continue;
            }

            // Validate message length
            if (!msg.validateMessageLength().equals("Message ready to send.")) {

                System.out.println(msg.validateMessageLength());
                System.out.println("Message was not processed.");
                continue;
            }

            System.out.println("\nChoose an action:");
            System.out.println("1. Send Message");
            System.out.println("2. Disregard Message");
            System.out.println("3. Store Message");
            System.out.print("Select option: ");

            int actionChoice = readInteger(scanner);

            // Process selected action
            String result = msg.processMessageAction(actionChoice);

            System.out.println(result);

            // Add only successfully processed messages
            if (msg.getStatus().equals("Sent")
                    || msg.getStatus().equals("Stored")
                    || msg.getStatus().equals("Disregard")) {

                store.addMessage(
                        msg.getMessageID(),
                        msg.getMessageHash(),
                        msg.getRecipient(),
                        msg.getMessageText(),
                        msg.getStatus(),
                        msg.getSender()
                );

                System.out.println("\n------- MESSAGE SUMMARY -------");
                System.out.println("Message ID: " + msg.getMessageID());
                System.out.println("Message Hash: " + msg.getMessageHash());
                System.out.println("Sender: " + msg.getSender());
                System.out.println("Recipient: " + msg.getRecipient());
                System.out.println("Message: " + msg.getMessageText());
                System.out.println("Status: " + msg.getStatus());
            }
        }

        System.out.println("\nTotal messages sent: "
                + SendMessage.returntotalnumofMessageSent());
    }

    // Display all sent messages
    private static void displaySentMessages() {

        String[] sent = store.getSentMessage();

        if (sent.length == 0) {

            System.out.println("No sent messages available.");
            return;
        }

        System.out.println("\n------- SENT MESSAGES -------");

        for (int i = 0; i < sent.length; i++) {

            System.out.println((i + 1) + ". " + sent[i]);
        }
    }

    // Stored Messages submenu
    private static void storedMessagesMenu(Scanner scanner) {

        int storedChoice = 0;

        do {

            System.out.println("\n------- STORED MESSAGES -------");
            System.out.println("1. Display sender and recipient");
            System.out.println("2. Display longest stored message");
            System.out.println("3. Search using Message ID");
            System.out.println("4. Search messages using recipient");
            System.out.println("5. Delete message using Message Hash");
            System.out.println("6. Display full stored messages report");
            System.out.println("7. Return to main menu");
            System.out.print("Choose an option: ");

            storedChoice = readInteger(scanner);

            switch (storedChoice) {

                case 1:
                    System.out.println(
                            store.displayStoredSendersAndRecipients()
                    );
                    break;

                case 2:
                    System.out.println(
                            store.displayLongestStoredMessage()
                    );
                    break;

                case 3:
                    System.out.print("Enter Message ID: ");
                    String id = scanner.nextLine();

                    System.out.println(
                            store.searchStoredMessageID(id)
                    );
                    break;

                case 4:
                    System.out.print("Enter recipient cellphone number: ");
                    String recipient = scanner.nextLine();

                    System.out.println(
                            store.searchStoredRecipient(recipient)
                    );
                    break;

                case 5:
                    System.out.print("Enter Message Hash: ");
                    String hash = scanner.nextLine();

                    System.out.println(
                            store.deleteStoredMessage(hash)
                    );
                    break;

                case 6:
                    System.out.println(
                            store.displayStoredMessageReport()
                    );
                    break;

                case 7:
                    System.out.println("Returning to the main menu...");
                    break;

                default:
                    System.out.println(
                            "Invalid option. Please choose between 1 and 7."
                    );
                    break;
            }

        } while (storedChoice != 7);
    }
}
