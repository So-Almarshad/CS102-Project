/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;

import eshop.util.Util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 *
 * @author abdul
 */
public class AdminLoginMenu extends Menu {
    private String username = "";
    private String password = "";
    private String censored = "";

    public AdminLoginMenu(Eshop eshop) {
        super(eshop, "ADMIN LOGIN", "Username: ", "Password: ", "Confirm", "Back");
    }

    @Override
    public void select(int optionNum) {
        switch(optionNum) {
            case 1: 
                System.out.print("Enter username: ");
                username = input.nextLine();
                break;
            case 2:
                System.out.print("Enter password: ");
                password = input.nextLine();
                censored = "*".repeat(password.length());
                break;
            case 3:
                if(isValidCredentials()) {
                    eshop.setActiveUser(eshop.getAdmin());
                    System.out.println("Login successful");
                    eshop.setActiveMenu(new AdminMenu(eshop));
                }
                else {
                    System.out.println("Incorrect username or password");
                    Util.pause(input);
                }
                break;
            case 4:
                eshop.setActiveMenu(new MainMenu(eshop));
                break;
            default: 
                System.out.println("That is not an option");
                Util.pause(input);
        }
        setOptions("Username: " + username, "Password: " + censored, "Confirm", "Back");
    }
    private boolean isValidCredentials() {
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] digest = messageDigest.digest(password.getBytes());
            boolean validUsername = username.equals(eshop.getAdmin().getUsername());
            boolean validPassword = Arrays.equals(digest, eshop.getAdmin().getPassword());
            return validUsername && validPassword; 
        } catch(NoSuchAlgorithmException e) {e.printStackTrace();}
        return false;
    }
}
