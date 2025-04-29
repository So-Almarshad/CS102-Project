/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;
import java.util.Scanner;
import eshop.users.Administrator;
import eshop.util.Util;
/**
 *
 * @author abdul
 */
public class AdminRegistrationMenu extends Menu {
    private String username = "";
    private String password = "";
    private String censored = "";
    private String name = "";
    private String ageStr = "";
    private int age = -1;
    
    public AdminRegistrationMenu(Eshop eshop) {
        super(eshop, "REGISTER NEW ADMIN", "Username: ", "Password: ", "Name: ", "Age: ", "Confirm");
    }
    
    //TODO edit this to account for password encryption
    @Override
    public void select(int optionNum) {
//        try {
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
                System.out.print("Enter name: ");
                name = input.nextLine();
                break;
            case 4:
                System.out.print("Enter age: ");
                String temp = input.nextLine().trim();
                if(Util.isInteger(temp)) {
                    ageStr = temp;
                    age = Integer.parseInt(temp);
                }
                else {
                    System.out.println("Age should be a positive integer");
                    Util.pause(input);
                }
                break;
            case 5:
                if(username.equals("") || password.equals("") || name.equals("") || age == -1) {
                    System.out.println("Some fields are missing");
                    Util.pause(input);
                }
                else if(isValidFields()) {
                    eshop.setAdmin(new Administrator(username, password, name, age));
                    eshop.setActiveMenu(null);
                }
                else {
                    Util.pause(input);
                }
                break;
            default: 
                System.out.println("That is not an option");
                Util.pause(input);
        }
//        } catch (Exception e) {
//            e.printStackTrace();
//            Util.pause(input);
//        }
        setOptions("Username: " + username, "Password: " + censored, "Name: " + name, "Age: " + ageStr, "Confirm");
    }
    
    //returns whether all the fields are valid, and prints which fields are invalid
    private boolean isValidFields() {
        boolean isValidUsername = Util.isAlphanumeric(username);
        boolean isValidPassword = Util.authorizePassword(password);
        boolean isValidAge = age > 15;
        if(!isValidUsername)
            System.out.println("Username should be alphanumerical");
        if(!isValidPassword) {
            System.out.println("Password should be more than six digits long, "
                    + "contains a letter, contains a number, contains a special "
                    + "character");
        }
        if(!isValidAge)
            System.out.println("Age should be greater than 15");
        
        return isValidUsername && isValidPassword && isValidAge;
    }
}
