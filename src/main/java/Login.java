/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
  package com.mycompany.poepart1;
/**
 *
 * @author Philisile Mavuso
 */
public class Login {
  //Declarations
    String userName;
    String name;
    String surname;
    String password;
    String cellNumber;
    //Those stores the registerd user's details
     String registeredUsername;
     String registeredPassword;
     
    //Constructor
    public Login(){
        userName = " ";
        password = " ";
        name = " ";
        surname = " ";
        cellNumber = " ";
        
        registeredUsername = " ";
        registeredPassword = " ";
    }
    
    // Method 1: Check username
    public boolean checkUserName(String userName) {
     return userName.contains("_") && userName.length() <=5;
    }
    
    //Method 2: Check password complexity
    public boolean checkPasswordComplexity(String password){
         
         boolean hasCapitalLetter = false;
         boolean hasNumber = false;
         boolean hasSpecialCharacter = false;
         
         for (int i = 0; i < password.length(); i++){
         
             char character = password.charAt(i);
             
             if (Character.isUpperCase(character)){
                 hasCapitalLetter = true;
             }
             if (Character.isDigit(character)){
                   hasNumber = true;
             }
             if (!Character.isLetterOrDigit(character)){
                 hasSpecialCharacter = true;
             }
         }
    
    return password.length() >= 8
            && hasCapitalLetter
            && hasNumber
            &&hasSpecialCharacter;
  
}

//Cellphone number checker using REGEX
    public boolean checkCellPhoneNumber(String cellNumber){
        //Cellphone should have the correct length and
        //contaion the international code. South Africa: +27
        String regex = "^\\+27[6-8][0-9]{8}$";
        
        return cellNumber.matches(regex);
    }

//Method 3: Register user
   public String registerUser(String userName,String password,String name, 
                        String surname,String cellphone){
      //String manipulation
      userName = userName.trim();
      name = name.trim();
      surname = surname.trim();
      cellNumber = cellNumber.trim();
      
      //Check userName
      if (!checkUserName(userName)) {
         return "Cell phone number is not correctly formatted.";
      }
      
      //Check user cellphone number
      if(!checkCellPhoneNumber(cellNumber)){
        return "Cell phone number is not correctly formatted.";
      }
      
      //Store the user's information 
     this.registeredUsername = userName;
     this.registeredPassword = password;
     this.name = name;
     this.surname = surname;
     this.cellNumber = cellNumber;
     
      return "Registration successful.";
              
   }
   
   //Method 4: Login user
    public boolean loginUser(String userName,String password) {
      
        userName = userName.trim();
        
        return userName.equals(registeredUsername)
                &&password.equals(registeredPassword);
        
    }
    
   //Method 5: Return login status
    public String returnLoginStatus(String userName, String password){
       
     if(loginUser(userName,password)){
          
      return "Welcome" + name + " " + surname +"it is great to see you again.";
        }
     else {
      return "Username or password incorrect," + "please try again.";
     }
    }
}
  
