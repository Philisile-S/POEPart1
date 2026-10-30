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
     private String sender;
     
     //ARRAYS FOR PART 3 STORED MESSAGES
     private static final int MAX_MESSAGES = 100;
     
     private static String[] messageIDs = new String[MAX_MESSAGES];
     private static String[] senders = new String[MAX_MESSAGES];
     private static String[] recipients = new String[MAX_MESSAGES];
     private static String[] messages = new String[MAX_MESSAGES];
     private static String[] messageHashes = new String[MAX_MESSAGES];
     
     //Static counter to track total messages sent globally across instances
     private static int messageCount = 0;
     private static int totalnumofMessageSent = 0;
     
     //Construtor 
     public SendMessage(String recipient, String messageText, int messageSent) {
         this.messageID = generateMessageID();
         this.recipient = recipient;
         this.messageText = messageText;
         this.messageSent = messageSent;
         this.messageHash = createMessageHash();
         this.sender = "";
     }
     
     //Generate a random 10 digit message ID 
     private String generateMessageID() {
        Random rand = new Random();
        long id = (long)(rand.nextDouble() * 9000000000L) + 1000000000L;
        return String.valueOf(id);
     }
     
     //Set sender
     public void setSender(String sender){
          this.sender = sender;
     }
     
     //1. Check MessageID: Ensure ID is not more than 10 characters 
     public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <=10;
     }
     
     //2. Check/Validate recipient cell number: Ensures no more than 10 characters and 
     //contain an international code like '+'
     public String validateRecipientCell() {
      if (this.recipient != null && this.recipient.startsWith("+") && this.recipient.length() <=10) {
       return "Cell phone number successfully captured.";
      }
      else{
         return "Cell phone number is incorrectly formatted or does not contain"
                 + " an international code. Please correct the number and try again.";
      }
     }
     public boolean checkRecipientCell() {
       return this.recipient != null && this.recipient.startsWith("+") && this.recipient.length() <= 10;
     }
    
     //3. Check message length (Max of 250 characters) 
     public String vaildateMessageLength() {
       if (this.messageText != null && this.messageText.length() <= 250){
         return "Message ready to send.";
     }
       else{
          int excess = this.messageText.length() - 250;
          return "Message exceeds 250 characters by " + excess + "please reduce the size.";
               }
     }
     
     //4. Create Message Hash: first 2 numbers of the messageID(:)Message number
     //(:)First and Last words in the mesasage
     public String createMessageHash() {
         if (this.messageID == null || this.messageID.length() <2) {
             return"";
         String firstTwoId = messageID.substring(0, 2);
         //Clean and extract first and last words
         String cleanedText = this.messageText.replaceAll("[^a-zA-Z ]", "").trim();
         String[] words = cleanedText.split("\\s+");
         
         String firstWord = words.length > 0 ? words[0] : "";
         String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
         
         return firstTwoId + ":" + this.messageSent + ":" + (firstWord + lastWord).toUpperCase();        
     }
     
       //Store message in arrays
       private boolean storeMessage() {
           if (messageCount >= MAX_MESSAGES){
               return false; 
           }
           
           messageIDs[messageCount] = messageID;
           senders[messageCount] = sender;
           recipients[messageCount] = recipient;
           messages[messageCount] = messageText;
           messageHashes[messageCount] = messageHash;
           
           messageCount++;
           
           return true;
           
       }
 
     //5. Handle Send/Store/Disregard Actions
     public String processMessageAction(int choice) {
       switch (choice) {
           //Send Message
           case 1: 
               totalnumofMessageSent++;
               return "Message successfully sent.";
           //Disregard Message or delete
           case 2: 
               return "Message disregarded.";
           //Store Message to send later 
           case 3: 
               if(storeMessage()) {
               return "Message successfully stored.";
               }
               else{
               return "Message storage is full.";
               }
           default:
               return "Invaild choice.";
                       
       }
     }
     
     // a. Display sender and recipient 
     public static void displaySenderAndRecipients() {
         
         if (messageCount == 0) {
            System.out.println("No stored messages available.");
            return;
         }
         
         for (int i = 0; i < messageCount; i++){
             System.out.println("Sender: " + senders[i]);
             System.out.println("Recipient: " + recipients[i]);
             System.out.println("-----------------------------");
         }
     }
        
     // b. Display longest message 
     public static void displayLongestMessage() {
     
         if (messageCount == 0) {
            System.out.println("No stored messages available");
            return;
         }
         
         int longestIndex = 0;
         
         for (int i = 1; i < messageCount; i++) {
            if (mesages[i].length() > mesages[longestIndex].length()) {
               longestIndex = i;
            }
         }
         
         System.out.println("Longest stored message: ");
         System.out.println(messages[longestIndex]);
     }
     
     // c. Search by message ID
      public static void searchMessageID(String id) {
         
          boolean found = false;
          
          for (int i = 0; i < messageCount; i++) {
             
              if (messageIDs[i].equals(id)) {
                 System.out.println("Recipient: " + recipients[i]);
                 System.out.println("Message: " + messages[i]);
                 found = true;
                 break;
              }
          }
          
          if (!found) {
              System.out.println("Message ID not found.");
          }
      }
      
      // d. Search messages by recipient
      public static void searchRecipient(String recipient) {
          
          boolean found = false;
          
          for (int i = 0; i < messageCount; i++) {
          
              if (recipients[i].equals(recipients)) {
                 System.out.println("Message ID: " + messageIDs[i]);
                 System.out.println("Message: " + messages[i]);
                 System.out.println("---------------------------");
                 found = true;
              }
          }
          
          if (!found) {
          System.out.println("No messages found for this recipient");
          }
      }
      
      // e. Delete message using message hash
      public static void deleteMessage(String hash) {
      
          for (int i =0; i < messageCount; i++) {
              
              if (messageHashes[i].equals(hash)) {
               
                  for (int j = i; j < messageCount - 1; j++) {
                      messageIDs[j] = messageIDs[j + 1];
                      senders[j] = senders[j + 1];
                      recipients[j] = recipients[j + 1];
                      messages[j] = messages[j + 1];
                      messageHashes[j] = messageHashes[j + 1];
                  }
                  
                  messageCount--;
                  
                  messageIDs[messageCount] = null;
                  senders[messageCount] = null;
                  recipients[messageCount] = null;
                  messages[messageCount] = null;
                  messageHashes[messageCount] = null;
                  
                  System.out.println("Message successfully deleted.");
                  return;
              }
          }
          
          System.out.println("Message hash out found.");
      }
      
      // f. Display full message report 
      public static void displayMessageReport() {
       
          if (messageCount == 0) {
             System.out.println("No stored messages available.");
             return;
          }
          
          System.out.println("==== STORED MESSAGES REPORT ====");
          
          for (int i = 0; i < messageCount; i++) {
              System.out.println("Message ID: " + messageIDs[i]);
              System.out.println("Message Hash: " + messageHashes[i]);
              System.out.println("Sender: " + senders[i]);
              System.out.println("Recipient: " + recipients[i]);
              System.out.println("Message: " + messages[i]);
              System.out.println("------------------------------- ");
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

