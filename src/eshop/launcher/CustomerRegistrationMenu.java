/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;

import eshop.users.Customer;
import eshop.users.CustomerDatabase;
import eshop.users.Card;
import eshop.users.Address;
import eshop.util.Util;
import java.util.Map;

/**
 *
 * @author abdul
 */
public class CustomerRegistrationMenu extends Menu {
    private CustomerDatabase customerDatabase;
    private String username = "";
    private String password = "";
    private String censored = "";
    private String name = "";
    private String ageStr = "";
    private int age = -1;
    
    public CustomerRegistrationMenu(Eshop eshop) {
        super(eshop, "CREATE NEW ACCOUNT", "Username: ", "Password: ", "Name: ", "Age: ", "Confirm", "Back");
        customerDatabase = eshop.getCustomerDatabase();
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
                    Customer customer = new Customer(username, password, name, age, null, null);
                    customerDatabase.register(customer);
                    eshop.setActiveMenu(new MainMenu(eshop));
                }
                else {
                    Util.pause(input);
                }
                break;
            case 6:
                eshop.setActiveMenu(new MainMenu(eshop)); 
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
        String adminUsername = eshop.getAdmin().getUsername();
        Map<String, Customer> customerTable = customerDatabase.getCustomerTable();
        boolean isUniqueUsername = !(customerTable.containsKey(username) || username.equals(adminUsername));
        boolean isValidUsername = Util.isAlphanumeric(username);
        boolean isValidPassword = Util.authorizePassword(password);
        boolean isValidAge = age > 15;
        if(!isUniqueUsername)
            System.out.println("Username is taken");
        if(!isValidUsername)
            System.out.println("Username should be alphanumerical");
        if(!isValidPassword) {
            System.out.println("Password should be more than six digits long, "
                    + "contains a letter, contains a number, contains a special "
                    + "character");
        }
        if(!isValidAge)
            System.out.println("Age should be greater than 15");
        
        return isUniqueUsername && isValidUsername && isValidPassword && isValidAge;
    }
}
