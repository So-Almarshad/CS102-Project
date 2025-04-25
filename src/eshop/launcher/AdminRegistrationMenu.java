/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;
import java.util.Scanner;
import eshop.users.Administrator;
/**
 *
 * @author abdul
 */
public class AdminRegistrationMenu extends Menu {
    private Scanner input;
    private Administrator admin;
    private String username = "";
    private String password = "";
    private String censored = "";
    private String name = "";
    private String age = "";
    private String registrationOptions;
    
    public AdminRegistrationMenu(Eshop eshop) {
        super(eshop);
        input = eshop.getInput();
        admin = eshop.getAdmin();
        registrationOptions = "CREATE ADMIN ACCOUNT\n" +
                              "1. Username:\n" +
                              "2. Password:\n" +
                              "3. Name:\n" +
                              "4. Age:\n" +
                              "5. Confirm";
    }
    
    @Override
    public void display() {
        System.out.println(registrationOptions);
    }
    
    //TODO edit this to account for password encryption
    @Override
    public void select(int optionNum) {
        input.nextLine();
        try {
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
                    age = input.nextLine();
                    break;
                case 5:
                    if(username.equals("") || password.equals("") || name.equals("") || age.equals(""))
                        System.out.println("Some fields are missing");
                    else
                        admin = new Administrator(username, password, name, Integer.parseInt(age));
                        getEshop().setActiveMenu(null);
                    break;
                default: System.out.println("That is not an option");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        registrationOptions = "*****CREATE ADMIN ACCOUNT*****\n" +
                              "1. Username: " + username + '\n' +
                              "2. Password: " + censored + '\n' +
                              "3. Name: " + name + '\n' +
                              "4. Age: " + age + '\n' +
                              "5. Confirm";
        System.out.println("Press enter to continue");
        input.nextLine();
    }
}
