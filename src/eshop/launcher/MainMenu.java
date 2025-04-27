/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;

/**
 *
 * @author abdul
 */
public class MainMenu extends Menu {
    
    public MainMenu(Eshop eshop) {
        super(eshop, "MAIN MENU", "Admin login", "Customer login", "Create new account", "Exit");
    }
    
    @Override
    public void select(int optionNum) {
        switch(optionNum) {
            case 1: eshop.setActiveMenu(new AdminLoginMenu(eshop)); break;
            case 2: eshop.setActiveMenu(new CustomerLoginMenu(eshop)); break;
            case 3: eshop.setActiveMenu(new CustomerRegistrationMenu(eshop)); break;
            case 4: try { eshop.close(); } catch (Exception e) {System.err.println("Save failed");} break;
        }
    }
}
