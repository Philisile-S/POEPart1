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
        String password;
        String cellNumber;
        
        
       //User's name input
        System.out.print("Please enter your name: ");
        name = myInput.next();
        
       //User's surname input
        System.out.print("Please enter your surname: ");
        surname = myInput.next();
        
       //Username input
         System.out.println("Enter your username: ");
         userName = myInput.next();
         
          while (!checksUserName(userName)){
              System.out.println("Username is not valid");
              System.out.println("Username must contain an underscore (_) and be at least 5 characters long.");
              
              System.out.print("Please enter username again: ");
              userName = myInput.next();
            }
         
          System.out.println("Username successfully captured.");
         
      //Ask for password input 
     
       while(true){
            System.out.print("Please enter your password: ");
            password = myInput.nextLine();
     
      if (checkPassword(password)){
          System.out.println("Password successfully captured.");
       break;
       }
      else{
         System.out.println("Password is not correctly formatted.");
          System.out.println("Password must be:");
          System.out.println("Be at least 8 characters long");
          System.out.println("Contain a capital letter");
          System.out.println("Contain a number");
          System.out.println("Contain a special character");
       }
   } 
      
    //Ask for user's cellphone number 
      
      while(true){
          System.out.println("Please enter your cellphone number: ");
          cellNumber = myInput.nextLine();
          
       if (checkCellPhoneNumber(cellNumber)){
          System.out.println("Cellphone number successfully added.");
        break;
       }
       else{
       System.out.println("Cellphone number incorrectly formatted or does not contain international code");
       System.out.println("Your cellphone number must start with: ");
       System.out.println("+27");
       System.out.println("Your cellphone number, e.g +27123678417");
      }
    
         }
     
       System.out.println();
       
       System.out.println("Registration successful");
       System.out.println("Welcome "+ name + " "+ surname);
    
     myInput.close();
      }
     
      //Method to check if username is valid
      public static boolean checksUserName(String username) {
          
          return username.length() >= 5 && username.contains("_");
        }
   
     //Method to check if password is vaild
     public static boolean checkPassword(String password) { 
        
         if(password.length() < 8){
           return false;  
        } 
         
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
         
        for (int i = 0; i < password.length(); i++)  {
           
            char character = password.charAt(i);
         
        if (Character.isUpperCase(character)) {
              hasCapital = true;
        }
        
        if (Character.isDigit(character)) {
            hasNumber = true;
        }
        
        if (Character.isLetterOrDigit(character)) {
            hasSpecial = true;
        }
       } 
        //Password is vaild only when all three requirements are met
        return hasCapital && hasNumber && hasSpecial;
     }
     
     //Method to check if the cellphone number is vaild
      public static boolean checkCellPhoneNumber(String cellNumber) {
          
          if (!cellNumber.startsWith("+27")) {
              return false;
          }
          
         String numberWithoutSpaces = cellNumber.replace(" "," ");
         
         if (numberWithoutSpaces.length() !=12) {
             return false;
           }
         
        for (int i = 3; i < numberWithoutSpaces.length(); i++) {
           
           if (!Character.isDigit(numberWithoutSpaces.charAt(i))) {
               return false;
           }
        }
        return true;
      }
}
      
     
  
         
     
