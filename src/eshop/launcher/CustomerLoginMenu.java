/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;

import eshop.util.Util;
import eshop.users.Customer;
import eshop.users.CustomerDatabase;

/**
 *
 * @author abdul
 */
public class CustomerLoginMenu extends Menu {
    private CustomerDatabase customerDatabase;
    private String username = "";
    private String password = "";
    private String censored = "";

    public CustomerLoginMenu(Eshop eshop) {
        super(eshop, "CUSTOMER LOGIN", "Username: ", "Password: ", "Confirm", "Back");
        customerDatabase = eshop.getCustomerDatabase();
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
                    eshop.setActiveUser(customerDatabase.find(username));
                    System.out.println("Login successful");
                    eshop.setActiveMenu(new CustomerMenu(eshop));
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
        Customer customer = customerDatabase.getCustomerTable().get(username);
        if(customer == null)
            return false;
        return password.equals(customer.getPassword());
    }
}
