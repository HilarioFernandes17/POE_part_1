/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.poe_part_1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hilar
 */
public class LoginTest {
    
    private Login login;

    @Before
    public void setUp() {
        login = new Login("Kyle", "Miguel", "kyl_1", "Ch&&sec@ke99!", "+27838966553");
    }

    // USERNAME TESTS
    @Test
    public void testCheckUserName_Valid() {
        assertTrue(Login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserName_Invalid_Length() {
        assertFalse(Login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testCheckUserName_Invalid_NoUnderscore() {
        assertFalse(Login.checkUserName("kyle"));
    }

    // PASSWORD TESTS
    @Test
    public void testCheckPasswordComplexity_Valid() {
        assertTrue(Login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexity_Invalid_NoUpperCase() {
        assertFalse(Login.checkPasswordComplexity("kylemiguel11!"));
    }

    @Test
    public void testCheckPasswordComplexity_Invalid_NoNumber() {
        assertFalse(Login.checkPasswordComplexity("Password!"));
    }

    @Test
    public void testCheckPasswordComplexity_Invalid_NoSpecialChar() {
        assertFalse(Login.checkPasswordComplexity("kyle1234"));
    }

    // CELLPHONE NUMBER TESTS
    @Test
    public void testCheckCellPhoneNumber_Valid() {
        assertTrue(Login.checkCellPhoneNumber("+27838966155"));
    }

    @Test
    public void testCheckCellPhoneNumber_Invalid_MissingPlus() {
        assertFalse(Login.checkCellPhoneNumber("27838966553"));
    }

    @Test
    public void testCheckCellPhoneNumber_Invalid_TooShort() {
        assertFalse(Login.checkCellPhoneNumber("+278966553"));
    }

    // REGISTRATION TESTS
    @Test
    public void testRegisterUser_Success() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838966553");
        assertEquals("User registered successfully.", result);
    }

    @Test
    public void testRegisterUser_InvalidUsername() {
        String result = login.registerUser("john", "Ch&&sec@ke99!", "+27838966553");
        assertTrue(result.contains("Username is not correctly formatted"));
    }

    @Test
    public void testRegisterUser_InvalidPassword() {
        String result = login.registerUser("kyl_1", "pass", "+27838966553");
        assertTrue(result.contains("Password is not correctly formatted"));
    }

    @Test
    public void testRegisterUser_InvalidCell() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "0821234567");
        assertTrue(result.contains("Cell phone number is not correctly formatted"));
    }

    // === LOGIN TESTS ===
    @Test
    public void testLoginUser_Success() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838966553");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginUser_Failure() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838966553");
        assertFalse(login.loginUser("wrongUser", "wrongPass123!"));
    }

    // LOGIN STATUS FOR MESSAGES
    @Test
    public void testReturnLoginStatus_Success() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838966553");
        String message = login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!");
        assertEquals("Welcome Kyle Miguel, it is great to see you again!", message);
    }

    @Test
    public void testReturnLoginStatus_Failure() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838966553");
        String message = login.returnLoginStatus("kyl_1", "wrongPassword!");
        assertEquals("Username or password incorrect. Please try again.", message);
    }
    
}
