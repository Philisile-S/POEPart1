/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart1;
/**
 *
 * @author Philisile Mavuso
 */
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
 import java.util.List;
 import java.io.FileWriter;
 import org.json.JSONArray;
 import org.json.JSONObject;
 
 
public class StoreMessage {
  //Users should be able to populate the following arrays: 
    private String[] sentMessage = new String[0];
    private String[] disregardedMessage = new String[0];
    private String[] storedMessages = new String[0];
    private String[] messageHash = new String[0];
    private String[] messageID = new String[0];
    
  //Additional parallel arrays for searching and displaying details 
    private String[] recipients = new String[0];
    private String[] senders = new String[0];
    private String[] statuses = new String[0];
    
  //Internal records retain the complete message details.
    private final List<MessageRecord> messages = new ArrayList<> ();
    
    //JSON file name
    private String jsonFile;
    
    //Default constructor
    public StoreMessage() {
          this("message.json");
    }
    
    //Constructor with JSON file name
    public StoreMessage(String jsonFile) {
          this.jsonFile = jsonFile;
          loadStoreMessageFromJSON();
          populateArrays();
    }
    
    //Inner class for storing mesage details
    private static class MessageRecord {
         String id;
         String hash;
         String recipient;
         String text;
         String status;
         String sender;
         
         MessageRecord(String id, String hash, String recipient, String text,
                 String status, String sender){
         this.id = id;
         this.hash = hash;
         this.recipient = recipient;
         this.text = text;
         this.status = status;
         this.sender = sender;
         
    }
}

    /*
    *Call this method whenever a message is created.
    *Status should be *Sent*, *Stored*, or *Disregard*.
    */
  public void addMessage(String id, String hash, String recipient, String text,
          String status, String sender) {
      
        if (!status.equalsIgnoreCase("Sent")
                && !status.equalsIgnoreCase("Stored")
                && !status.equalsIgnoreCase("Disregard")) {
             
            System.out.println("Invaild mesage status.");
            return;
        }
        
     messages.add (new MessageRecord(
                   id, hash, recipient,text, status, sender
     ));
     
     populateArrays();
     
     if("Stored".equalsIgnoreCase(status)){
        saveStoreMessageToJSON();      
     }
  }

  //Populate all five required arrays from actual message recipients.
   public final void populateArrays() {
    

     List<String> sent = new ArrayList<>();
     List<String> disregarded = new ArrayList<>();
     List<String> stored = new ArrayList<>();
     
     List<String> hashes = new ArrayList<>();
     List<String> ids = new ArrayList<>();
     List<String> recipientList = new ArrayList<>();
     List<String> senderList = new ArrayList<> ();
     List<String> statusList = new ArrayList<> ();
     
     for (MessageRecord message : messages) {
        hashes.add(message.hash);
        ids.add(message.id);
        recipientList.add(message.recipient);
        senderList.add(message.sender);
        statusList.add(message.status);
     
        if ("Sent".equalsIgnoreCase(message.status)) {
              sent.add(message.text);
        }
        else if ("Disregard".equalsIgnoreCase(message.status)) {
               disregarded.add(message.text);
       }
       else if ("Stored".equalsIgnoreCase(message.status)) {
             stored.add(message.text);
       }
    }
     
      //Convert lists into arrays 
      sentMessage = sent.toArray(new String[0]);
      disregardedMessage = disregarded.toArray(new String[0]);
      storedMessages = stored.toArray(new String[0]);
      
      messageHash = hashes.toArray(new String[0]);
      messageID = ids.toArray(new String[0]);
      
      recipients = recipientList.toArray(new String[0]);
      senders = senderList.toArray(new String[0]);
      statuses = statusList.toArray(new String[0]);
}
   
   //Save stored messages into a JSON file
   private void saveStoreMessageToJSON() {
       
       JSONArray jsonArray = new JSONArray();
       
       for (MessageRecord message : messages) {
           
         if ("Stored".equalsIgnoreCase(message.status)) {
           
           JSONObject jsonObject = new JSONObject();
           
           jsonObject.put("id", message.id);
           jsonObject.put("hash", message.hash);
           jsonObject.put("recipient", message.recipient);
           jsonObject.put("text", message.text);
           jsonObject.put("status", message.status);
           jsonObject.put("sender", message.sender);
           
           jsonArray.put(jsonObject);
    
       }
       
   }
   
     try (FileWriter writer = new FileWriter(jsonFile)) {
        
          writer.write(jsonArray.toString(4));
      
      }
         catch (IOException e) {
         
          System.out.println("Errors saving messages: " + e.getMessage());
      }
}
 
   //Lead stored mesages from JSON
   //Read stored messages from JSON file
 private void loadStoreMessageFromJSON() {
     
      try {
          
           if(!Files.exists(Paths.get(jsonFile))) {
               return;
           }
           
           String content = new String(
               Files.readAllBytes(Paths.get(jsonFile))
           );
           
           JSONArray jsonArray = new JSONArray(content);
           
           for (int i = 0; i < jsonArray.length(); i++) {
               
               JSONObject jsonObject = jsonArray.getJSONObject(i);
               
               MessageRecord record = new MessageRecord(
                   jsonObject.getString("id"),
                   jsonObject.getString("hash"),
                   jsonObject.getString("recipient"),
                   jsonObject.getString("text"),
                   jsonObject.getString("status"),
                   jsonObject.getString("sender")
               );
               
                  messages.add(record);
               
           }
        } catch (IOException e) {
                      
                System.out.println("Error loading messages: " + e.getMessage());
            }
       }
      
     //(a) Display sender and recipient of stored mesages 
      public String displayStoredSendersAndRecipients() {
           
          StringBuilder result = new StringBuilder();
          
          for (MessageRecord message : messages) {
              
             if ("Stored".equalsIgnoreCase(message.status)) {
                 
                result.append("Sender: ")
                       .append(message.sender) 
                       .append("\n");
                
                result.append("Recipient: ")
                       .append(message.recipient)
                       .append("\n");
               
                result.append("--------------------------\n");
                       
             }
          }
          
            return result.length() == 0 
                    ? "No stored messages available."
                    : result.toString();
      }
      
       //(b) Display the longest stored message 
         public String displayLongestStoredMessage() {
             
             String longest = "";
             
             for (MessageRecord message : messages) {
                 
                 if ("Stored".equalsIgnoreCase(message.status)
                      && message.text.length() > longest.length()) {
                    
                     longest = message.text;
                 }
             }
               
             return longest.isEmpty() 
                     ? "No stored messages found."
                     : longest;
         }
         
         //(c) Search stored message using Message ID 
           public String searchStoredMessageID(String id) {
               
               for (MessageRecord message : messages) {
                   
                   if ("Stored".equalsIgnoreCase(message.status)
                           && message.id.equals(id)) {
                       
                       return "Recipient: " + message.recipient
                                + "\nMessage: " + message.text;
                   }
               }
               
              return "Message ID not found.";
           }
           
         //(d) Search stored messages using recipient 
           public String searchStoredRecipient(String recipient) {
               
               String result = "";
               
               for (MessageRecord message : messages) {
                   
                   if ("Stored".equalsIgnoreCase(message.status)
                         && message.recipient.equals(recipient)) {
                       
                        result += "Message ID: " + message.id
                        + "\nMessage: " + message.text
                        + "\n\n";
                   }
               }
               
               return result.isEmpty()
                ? "No stored messages found for this recipient."
                : result;
           }
           
            // (e) Delete stored message using Message Hash
    public String deleteStoredMessage(String hash) {

        for (int i = 0; i < messages.size(); i++) {

            MessageRecord message = messages.get(i);

            if ("Stored".equalsIgnoreCase(message.status)
                    && message.hash.equals(hash)) {

                String deletedText = message.text;

                messages.remove(i);

                populateArrays();
                saveStoreMessageToJSON();

                return "Message: \"" + deletedText
                        + "\" successfully deleted.";
            }
        }

        return "Message hash not found.";
    }

    // (f) Display full report of stored messages
    public String displayStoredMessageReport() {

        String result = "";

        for (MessageRecord message : messages) {

            if ("Stored".equalsIgnoreCase(message.status)) {

                result += "Message ID: " + message.id
                        + "\nMessage Hash: " + message.hash
                        + "\nSender: " + message.sender
                        + "\nRecipient: " + message.recipient
                        + "\nMessage: " + message.text
                        + "\nStatus: " + message.status
                        + "\n--------------------------\n";
            }
        }

        return result.isEmpty()
                ? "No stored messages found."
                : result;
    }

     
      //Getters for accessing the arrays 
       
        public String[] getSentMessage() {
            return sentMessage;         
        }
        
        public String[] getDisregardedMessage() {
            return disregardedMessage;
        }
        
        public String[] getStoredMessage() {
            return storedMessages;
        }
        
        public String[] getMessageHash() {
            return messageHash;
        }
        
        public String[] getMessageID() {
            return messageID;
        }
        
        public String[] getRecipients() {
            return recipients;
        }
        
        public String[] getSenders() {
            return senders;
        }
        
        public String[] getStatuses() {
            return statuses;
        }
 }
 
   
  
 


 
