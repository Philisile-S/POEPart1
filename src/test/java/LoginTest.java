/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
   package com.mycompany.poepart1;
   
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Philisile Mavuso
 */
public class LoginTest {
    
    //My unit test will start here.
    //Arranging 
    private Login login;
    
    @BeforeEach
            void setUp(){
         login = new Login();
            }
            
   //Section 1: assertEquals Tests (As per the task specification table)
     
    @Test
    void testRegisterUser_UsernameCorrectlyFormatted_ShouldReturnWelcomeMessage() {
    //Test Data from table: "kyl_1"
    //Note: Using placeholders <User first name> and <user lastname> as specified 
    String expected = "Welcome <user first name> ,<user last name>"
            + " it is great to see you.";
    String actual = login.registerUser("kyl_1","Ch&&sec@ke99!","<user first name>",
            "user last name>","+27838968976"); 
    
     assertEquals(expected, actual);
    }
    
    @Test
    void testRegisterUser_User_UsernameIncorrectlyFormatted_ShouldReturnFormatError() {
    //Test Data from table: "kyle!!!!!!"
    String expected = "Username is not correctly formatted; please ensure that"
            + "+ your username contains an underscore and is no more than five"
            + "+ characters in length.";
    String actual = login.registerUser("kyle!!!!!!","Ch&&sec@ke99!", "Kyle",
            "Smith","+27838968976");
    
    assertEquals(expected, actual);
    }
    
    @Test
    void testRegisterUser_PasswordMeetsComplexity_ShouldReturnSuccessMessage(){
      //Test Data from table: "Ch&&sec@ke99!"
      //Note: If your registerUser method returns this string when the password 
      //vaild, this checks it: 
      String expected = "Password succcessfully captured.";
      String actual = login.registerUser("kyl_1","Ch&&sec@ke99!","Kyle","Smith",
              "+27838968976");
      
      assertEquals(expected, actual);
    }
    
    @Test 
    void testRegisterUser_PasswordDoesNotMeetComplexity_ShouldReturnFormatError(){
        //Test Data from table: "password"
      String expected = "Password is not correctly formatted; please ensure that"
              + " the password contains at leasteight characters, a capital letter"
              + ", a number, and a special character.";
      String actual = login.registerUser("kyl_1","password","Kyle","Smith",
              "+27838968976");
      
      assertEquals(expected, actual);
        }
    
    @Test
    void testRegisterUser_CellPhoneCorrectlyFormatted_ShouldReturnSuccessMessage(){
      //Test Data from table: "+2783968976"
      String expected = "Cell number successfully captured.";
      String actual = login.registerUser("kyl_1","Ch&&sec@ke99!","Kyle","Smith",
              "+27838968976");
      
      assertEquals(expected, actual);
    }
    
    @Test
    void testRegisterUser_CellPhoneIncorrectlyFormatted_ShouldReturnFormatError(){
        //Test Data from table: "08966553"
        String expected = "Cell number is incorrectly formatted or does not contain"
                + "an international code; please correct the number and try again.";
        String actual = login.registerUser("kyl_1","Ch&&sec@ke99!","Kyle","Smith","08966553");
        
        assertEquals(expected, actual);
    }
    
    //Section 2: assertTrue / assertFalse TESTS (As per the second table)
    
    @Test
    void testLoginUser_LoginSuccessful_ShouldReturnTrue() {
    //Arrange: Directly assign public fields to ensure match 
    login.registeredUsername = "kyl_1";
    login.registeredPassword = "Ch&&sec@ke99!";
    
    //Act and Assert
    assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    
    }
    
    @Test
    void testLoginUser_LoginFailed_ShouldReturnFalse() {
    //Arrange 
    login.registeredUsername = "kyl_1";
    login.registeredPassword = "Ch&&sec@ke99!";
    
    //Act and Assert
    assertFalse(login.loginUser("kyl_1","wrongpassword"));
    
    }
    
    @Test 
    void testCheckUserName_CorrectlyFormatted_ShouldReturnTrue(){
        assertTrue(login.checkUserName("kyl_1"));
    }
    
    @Test 
    void testCheckUserName_IncorrectlyFormated_ShouldReturnFalse() {
        assertFalse(login.checkUserName("kyle!!!!!!"));
    }
    
    @Test
    void testCheckPasswordComplexity_MeetsComplexity_ShouldReturnTrue() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    
    @Test
    void testCheckPasswordComplexity_DoesNotMeetComplexity_ShouldReturnFalse() {
        assertFalse(login.checkPasswordComplexity("password"));
    }
    
    @Test
    void testCheckCellPhoneNumber_CorrectlyFormatted_ShouldReturnTrue() {
        assertTrue(login.checkCellPhoneNumber("+2783968976"));
    }
    
    @Test
    void testCheckCellPhoneNumber_IncorrectlyFormatted_ShouldReturnFalse() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
 }

