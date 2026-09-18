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
                      + "and be at least 5 characters long.");
              
              System.out.print("Please enter username again: ");
              userName = myInput.next();
            }
         
          System.out.println("Username successfully captured.");
         
      //Ask for password input 
     
       while(!login.checkPasswordComplexity(password)){
            System.out.print("Please enter your password: ");
            password = myInput.nextLine();
     
      if (!login.checkPasswordComplexity(password)){
          System.out.println("Password is not correctly formatted.");
          System.out.println("Password must be:");
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