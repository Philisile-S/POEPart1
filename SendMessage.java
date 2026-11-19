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
      if (recipient.matches("\\+[0-9]{1,12}")) {
       return "Cell phone number successfully captured.";
      }
      else{
         return "Cell phone number is incorrectly formatted or does not contain"
                 + " an international code. Please correct the number and try again.";
      }
     }
     
     public boolean checkRecipientCell() {
    return recipient != null
            && recipient.matches("\\+[0-9]{1,12}");
     }
    
     //3. Check message length (Max of 250 characters) 
     public String vaildateMessageLength() {
         if (messageText !=null && messageText.length() <= 250) {
             return "Message ready to send";
             }
         else {
                int length = (messageText == null) ? 0 : messageText.length();
                int excess = Math.max(0, length - 250);
                
         return "Message exceeds 250 charcters by "
                 + excess + "characters. Please reduce the size.";
         }
     }
      
      //Boolean vaildation for ChatMain 
     public boolean isMessageLengthValid() {
         return messageText != null && messageText.length() <= 250;
     }
     
     //4. Create Message Hash: first 2 numbers of the messageID(:)Message number
     //(:)First and Last words in the mesasage
     public String createMessageHash() {
         if (messageID == null || messageID.length() <2) {
             return"";
         }
         
         String firstTwoId = messageID.substring(0, 2);
         
         //Clean and extract first and last words
         String cleanedText = (messageText == null)
                 ? ""
                 : messageText.replaceAll("[^a-zA-Z ]", "").trim();
        
         String[] words = cleanedText.isEmpty()
                ? new String[0]
                : cleanedText.split("\\s+");
                        
         String firstWord = "";
         String lastWord = "";
         
          if (words.length > 0) {
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



    
    
