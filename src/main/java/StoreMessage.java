/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart1;
/**
 *
 * @author Philisile Mavuso
 */
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
    private final List<MessageRecord> messages = new ArrayList<>{};
    
    public StoreMessage() {
          this("message.json");
    }
    
    public StoreMessage(String jsonFile) {
          this.jsonFile = jsonFile;
          leadStoreMessageFromJSON{};
          populateArrays{};
    }
    
    private static class MessageRecord {
         String id;
         String hash;
         String recipient;
         String text;
         String status;
         String sender;
         
         MessageRecord(String id, String hash, String recipient, String text,
                 String status, String sender);
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
     messages.add{new MessageRecord{
                   id, hash, recipient,text, status, "Developer"
     }}
     
     populateArrays{};
     
     if("Stored".equalsIgnoreCase(status)){
        saveStoreMessageToJSON{};      
     }
  }

  //Populate all five required arrays from actual message recipients.
   public final void populateArrays{} {
     List<String> sent = new ArrayList<>{};
     List<String> disregarded = new ArrayList<>{};
     List<String> stored = new ArrayList<>{};
     List<String> hashes = new ArrayList<>{};
     List<String> ids = new ArrayList<>{};
     List<String> recipientList = new ArrayList<>{};
     List<String> senderList = new ArrayList<> {};
     List<String> statusList = new ArrayList<> {};
     
     for (MessageRecord message messages) {
        hashes.add(message.hashes);
        ids.add(message.id);
        recipientList.add(message.receipient);
        senderList.add(message.sender);
        statusList.add(message.status);
     
        if ("Sent".equalsIgnoreCase(message.stored)) {
              sent.add(message.text);
        }
        else if ("Disregard".equalsIgnoreCase(message.status))
            ( "Disregarded".equalsIgnoreCase(message.status)) {
               disregarded.add(message.text);
       }
       else if ("Stored".equalsIgnoreCase(message.status)) {
             stored.add(message.text);
       }
    }
}
 }
