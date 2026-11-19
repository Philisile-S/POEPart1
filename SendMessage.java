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
     private String recipient;
     private String messageHash;
     private String sender;
     private String status;
     
     //ARRAYS FOR PART 3 STORED MESSAGES
     private static final int MAX_MESSAGES = 100;
     
     private static String[] messageIDs = new String[MAX_MESSAGES];
     private static String[] senders = new String[MAX_MESSAGES];
     private static String[] recipients = new String[MAX_MESSAGES];
     private static String[] messages = new String[MAX_MESSAGES];
     private static String[] messageHashes = new String[MAX_MESSAGES];
     
     //Static counter to track total messages sent globally across instances 
     private static int messageCount = 0;
     //Static counter for messages successfully sent
     public static int totalnumofMessageSent = 0;
     
     //Construtor 
     public SendMessage(String recipient, String messageText, int messageSent) {
         this.messageID = generateMessageID();
         this.recipient = recipient;
         this.messageText = messageText;
         this.messageSent = messageSent;
         this.sender = "Developer";
         this.status = "";
         this.messageHash = createMessageHash();
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
        return this.messageID != null && messageID.length() <= 10;
     }
     
     //2. Check/Validate recipient cell number: Ensures no more than 10 characters and 
     //contain an international code like '+'
     public String validateRecipientCell() {
      if (this.recipient != null && this.recipient.startsWith("+") && this.recipient.length() <=13) {
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
          int length = (this.messageText == null)? 0 : this.messageText.length();
          int excess = Math.max(0, length - 250);
          return "Message exceeds 250 characters by " + excess + "please reduce the size.";
               }
     }
      
      //Boolean vaildation for ChatMain 
     public boolean isMessageLengthVaild() {
         return messageText != null && messageText.length() <= 250;
     }
     
     //Boolean method required by ChatMain
     public boolean vaildateMessageLengthBoolean() {
         return isMessageLengthVaild();
     }
     
     //4. Create Message Hash: first 2 numbers of the messageID(:)Message number
     //(:)First and Last words in the mesasage
     public String createMessageHash() {
         if (this.messageID == null || this.messageID.length() <2) {
             return"";
         }
         
         String firstTwoId = messageID.substring(0, 2);
         
         //Clean and extract first and last words
         String cleanedText = this.messageText.replaceAll("[^a-zA-Z ]", "").trim();
         String[] words = cleanedText.split("\\s+");
         
         String firstWord = "";
         String lastWord = "";
         
          if (!cleanedText.isEmpty()) {
              
              firstWord = words[0];
              lastWord = words[words.length - 1]; 
          }
          
         return firstTwoId + ":" + this.messageSent + ":" + (firstWord + lastWord).toUpperCase();        
     }
     
  
 
     //5. Handle Send/Store/Disregard Actions
     public String processMessageAction(int choice) {
       switch (choice) {
           //Send Message
           case 1: 
               status = "Sent"; 
               totalnumofMessageSent++;
               return "Message successfully sent.";
               
           //Disregard Message or delete
           case 2: 
               status = "Disregard";
               return "Message disregarded.";
               
           //Store Message to send later 
           case 3: 
               status = "Stored";
               
               return "Message successfully stored.";
               
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
        
     //Getters
     public String getMessageID() {
         return messageID; 
     }
     
     public String getRecipient() {
         return recipient; 
     }
     
     public String getMessageText() {
         return messageText;
     }
     public String getMessageHash() { 
         return messageHash; 
     } 
     public String getSender() {
         return sender;
     }
     public String getStatus() {
         return status;
     }
     public int getMessageSent() {
         return messageSent;
     }
     
     //Return total number of messages sent 
     public static int returntotalnumofMessageSent() {
        return totalnumofMessageSent;
     }
}



    
    
