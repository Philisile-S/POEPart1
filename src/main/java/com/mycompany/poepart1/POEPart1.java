/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poepart1;

import java.util.Scanner;

/**
 *
 * @author Philisile Mavuso
 */
public class POEPart1 {

    public static void main(String[] args) {
       Scanner myInput = new Scanner (System.in);
       //Declarations
        String userName;
        String name;
        String surname;
        String password = " ";
        String cellNumber = " ";
        String loginUserName;
        String loginPassword;
        String registrationMessage;
        String loginMessage;
        
         //Creating Login object
         Login login = new Login();
        
        // USER REGISTRATION 
        
       //User's name input
        System.out.print("Please enter your name: ");
        name = myInput.next();
        
       //User's surname input
        System.out.print("Please enter your surname: ");
        surname = myInput.next();
        
       //Username input
         System.out.println("Enter your username: ");
         userName = myInput.next();
         
          while (!login.checkUserName(userName)){
              System.out.println("Username is not valid");
              System.out.println("Username must contain an underscore (_) "
                      + "and no more than 5 characters long.");
              
              System.out.print("Please enter username again: ");
              userName = myInput.next();
            }
         
          System.out.println("Username successfully captured.");
         
      //Ask for password input 
     
       while(!login.checkPasswordComplexity(password)){
            System.out.print("Please enter your password: ");
            password = myInput.nextLine();
     
      if (!login.checkPasswordComplexity(password)){
          System.out.println("Be at least 8 characters long");
          System.out.println("Contain a capital letter");
          System.out.println("Contain a number");
          System.out.println("Contain a special character");
       }
   } 
      System.out.println("Password successfully captured.");
      
    //Ask for user's cellphone number 
      
      while(!login.checkCellPhoneNumber(cellNumber)){
          System.out.println("Please enter your cellphone number: ");
          cellNumber = myInput.nextLine();
          
       if (!login.checkCellPhoneNumber(cellNumber)){
       System.out.println("Cellphone number incorrectly formatted or"
               + " does not contain international code");
       System.out.println("Your cellphone number must start with +27.");
       System.out.println("Example: +27123678417");
         }
      }
         System.out.println("Cellphone number successfully added."); 
                 
      //Register the user 
      registrationMessage = login.registerUser(
        userName, 
        password,
        name,
        surname,
        cellNumber);
      
      System.out.println("\n" + registrationMessage);
     
       System.out.println();
       
       System.out.println("Registration successful");
       System.out.println("Welcome "+ name + " "+ surname);
   
       //Login Features
       System.out.print("Please enter your username: ");
       loginUserName = myInput.nextLine();
       
       System.out.print("Please enter your password: ");
       loginPassword = myInput.nextLine();
       
       //Checking login details
        loginMessage = login.returnLoginStatus(loginUserName, loginPassword);
        
        System.out.println("\n" + loginMessage);
        
       //Close Scanner
     myInput.close();
     
    }
}


// START OF PART 2: SENDING MESSAGES  

public class Main{
   public static void main(String[] args) {
   Scanner scanner = new Scanner(System.in);
   
     //Simulated Login Check (assuming login from Part 1) 
     System.out.println("=== QuickChat Login ===");
     System.out.print("Please enter your username: ");
     String username = scanner.nextLine();
     System.out.print("Please enter your password: ");
     String password = scanner.nextLine();
     
     //Simple validation placeholder for login success
     boolean isLoggedIn = !username.isEmpty() && !password.isEmpty();
     
     if (!isLoggedIn) {
       System.out.println("Login failed. Existing application.");
       return;
     }
     
     //Welcome Message 
     System.out.println("Welcome to QuickChat.");
     
     int choice;
     do{
        System.out.println("\n--- Main Menu ---");
        System.out.println("1) Send Messages");
        System.out.println("2) Show recently sent messages (Coming Soon)");
        System.out.println("3) Quit");
        System.out.print("Choose an option: ");
        
        while(!scanner.hasNextInt()) {
             System.out.print("Invaild input. Please enter a number(1-3): ");
             scanner.next();
        }
        choice = scanner.nextInt();
       //consume newline 
        scanner.nextLine();
        
        switch(choice) {
            case 1: 
                System.out.print("Enter number of messages to send: ");
                int numMessages = scanner.nextInt();
                scanner.nextLine();
                
                for (int 1 = 0; i < numMessages; i++) {
                   System.out.println("\n--- Message" + (i + 1) + "---");
                   
                   System.out.print("Enter recipient cell number (e.g. +2771...):");
                   String recipient = scanner.nextLine();
                   
                   System.out.print("Enter message (max 250 characters): ");
                   String text = scanner.nextLine();
                   
                   Message msg = new Message(recipient, text, i);
                   
                   //Validate recipient 
                   if (!msg.checkRecipientCell()) {
                     System.out.println("Cell phone number is incorrectly formatted "
                             + "or does not contain an international code. Please "
                             + "correct the number and try again.");
                     continue;
                   }
                   else {
                       System.out.println("Message ready to send.");
                   }
                   
                   //Sub-menu for action 
                   System.out.println("Choose action: ");
                   System.out.println("1) Send Message");
                   System.out.println("2) Disregard Message");
                   System.out.println("3) Store Message to send later");
                   System.out.print("Select option (1-3): ");
                   int actionChoice = scanner.nextInt();
                   scanner.nextLine();
                   
                   String result = msg.processMessageAction(actionChoice);
                   System.out.println(result);
                   
                   //Display Details if sent/stored 
                   if (actionChoice == 1 || actionChoice == 3) {
                      System.out.println("\n--- Message Summary ---");
                      System.out.println("Message ID: " + msg.getMessageID());
                      System.out.println("Message Hash: " + msg.getMessageHash());
                      System.out.println("Recipient: " + msg.getRecipient());
                      System.out.println("Message: " + msg.getMessageText());
                   }
                }
        
        System.out.println("Total messages processed/sent: " + Message.returnTotalMessages());
         break;
         
            case 2: 
                System.out.println("Coming Soon.");
                break;
            
            case 3:
                System.out.println("Existing QuickChat. Goodbye!");
                break;
                
            default: 
                System.out.println("Invaild option. Please choose between 1 and 3.");
        }
      } while (choice != 3);
      
      scanner.close();
     
   }
}

     // END OF PART TWO 