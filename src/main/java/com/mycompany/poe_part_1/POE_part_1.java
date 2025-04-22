/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.poe_part_1;

import java.util.Scanner;

/**
 *
 * @author hilar
 */
public class POE_part_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the User Authentication System!");

        System.out.println("=== User Registration ===");
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Username (max 5 characters and must include '_'): ");
        String username = scanner.nextLine();

        System.out.print("Enter Password (min 8 characters, must include a capital letter, number, special character): ");
        String password = scanner.nextLine();

        System.out.print("Enter Phone Number (format: +27xxxxxxxxxx): ");
        String phoneNumber = scanner.nextLine();

        // Creating the Login object
        Login user = new Login(firstName, lastName, username, password, phoneNumber);

        // Registering the user
        String registrationMessage = user.registerUser(username, password, phoneNumber);
        System.out.println(registrationMessage);

        //Login 
        System.out.println("\n---- User Login----");
        System.out.println("Enter Username");
        String loginUsername = scanner.nextLine();

        System.out.println("Enter Password");
        String loginPassword = scanner.nextLine();

        //Cheking Login
        String loginMessage = user.returnLoginStatus(loginUsername, loginPassword);
        System.out.println(loginMessage);

        scanner.close();

    }
}
