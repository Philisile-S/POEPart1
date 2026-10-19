package com.mycompany.poepart1;

import java.util.Random;

/**
 * 
 * @author Philisile Mavuso
 */
 public class SendMessage {
     //Message Decalarations
     private String messageID;
     private int messageSent;
     private String messageText;
     private String recipient;;
     private String messageHash;
     
     //Static counter to track total messages sent globally across instances
     private static int totalnumofMessageSent = 0;
     
     //Construtor 
     public SendMessage(String recipient, String messageText, int messageSent) {
         this.messageID = generateMessageID();
         this.recipient = recipient;
         this.messageText = messageText;
         this.messageSent = messageSent;
         this.messageHash = createMessageHash();
     }
     
     //Generate a random 10 digit message ID 
     private String generateMessageID() {
        Random rand = new Random();
        long id = (long)(rand.nextDouble() * 9000000000L) + 1000000000L;
        return String.valueOf(id);
     }
     
     //1. Check MessageID: Ensure ID is not more than 10 characters 
     public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <=10;
     }
     
     //2. Check recipient cell number: Ensures no more than 10 characters and 
     //contain an international code like '+'
     public boolean checkRecipientCell() {
        if (this.recipient == null) return false;
       //must contain an international code and must be less than 10 characters
       // (or as per specs)
          return this.recipient.startsWith("+") && this.recipient.length() <= 10;
     }
    
     //3. Check message length (Max of 250 characters) 
     public boolean checkMessageLength() {
        return this.messageText != null && this.messageText.length() <= 250;
     }
     
     //4. Create Message Hash: first 2 numbers of the messageID(:)Message number
     //(:)First and Last words in the mesasage
     public String createMessageHash() {
         if (this.messageID == null || this.messageID.length() <2) return"";
         String firstTwoId = this.messageID.substring(0, 2);
         
         //Clean and extract first and last words
         String cleanedText = this.messageText.replaceAll("[^a-zA-Z ]", "").trim();
         String[] words = cleanedText.split("\\s+");
         String firstWord = words.length > 0 ? words[0] : "";
         String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
         
         return firstTwoId + ":" + this.messageSent + ":" + (firstWord + lastWord).toUpperCase();        
     }
     
     //5. Handle Send/Store/Disregard Actions
     public String processMessageAction(int choice) {
       switch (choice) {
           //Send Message
           case 1: 
               messageSent++;
               return "Message successfully sent.";
           //Disregard Message or delete
           case 2: 
               return "Press 0 to delete the message.";
           //Store Message to send later 
           case 3: 
               return "Message successfully stored.";
           default:
               return "Invaild choice.";
                       
       }
     }
     
     //Getters
     public String getMessageID() { return messageID; }
     public String getRecipient() { return recipient; }
     public String getMessageText() { return messageText; }
     public String getMessageHash() { return messageHash; } 
     
     public static int returntotalnumofMessageSent() {
        return totalnumofMessageSent;
     }
 }

