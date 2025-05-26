/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe_part_1;

/**
 *
 * @author hilar
 */
public class Login {
    
            //The main ttributes
    private final String firstName;
    private final String lastName;
    private static String username;
    private String password;
    private String cellNumber;


    //The Full Constructor
    public Login(String firstName, String lastName, String username, String password, String cellNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
    }

    //Checking username
    static boolean checkUserName(String username) {
        return username.length() <= 5 && username.contains("_");
    }
    //checking password requirements
    static boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[!@#_$%^&()].*");
    }
    //checking cellphone number
    static boolean checkCellPhoneNumber(String phoneNumber) {
        String pattern = "^\\+27\\d{10}$";
        return phoneNumber.matches(pattern);
    }
    //registering the user
    public String registerUser(String username, String password, String phoneNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted. It should contain an underscore and be no more than 5 characters long.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted. It must be at least 8 characters long, contain a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cell phone number is not correctly formatted. It must start with +27 followed by 10 digits.";
        }

        // Proceed with the program after meeting all requirements
        this.username = username;
        this.password = password;
        this.cellNumber = phoneNumber;

        return "User registered successfully.";
    }
    //checking the detailsto login
    public boolean loginUser(String inputUsername, String inputPassword) {
        return inputUsername.equals(username) && inputPassword.equals(this.password);
    }
    //Return login status
    String returnLoginStatus(String inputUsername, String inputPassword) {
        if (loginUser(inputUsername, inputPassword)) {
            return "Welcome " + this.firstName + " " + this.lastName + ", it is great to see you again!";
        } else {
            return "Username or password incorrect. Please try again.";
        }
    }
    
}
