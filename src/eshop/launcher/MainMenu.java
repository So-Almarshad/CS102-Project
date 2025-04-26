/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;

import java.util.Scanner;
/**
 *
 * @author abdul
 */
public class MainMenu extends Menu{
    
    public MainMenu(Eshop eshop) {
        super(eshop,"MAIN MENU", "Admin login", "Customer login", "Create new account", "Exit");
    }
    
    @Override
    public void select(int optionNum) {
        switch(optionNum) {
            case 1: 
        }
    }
    
    private void adminLogin() {
        Scanner sc = this.getEshop().getInput();
        
        while(true) {
            System.out.println("Enter admin username: ");
            String username = sc.nextLine();
        
            System.out.println("Enter admin password");
            String password = sc.nextLine();
        }
    }
    
    private boolean isValid(String username, String password) {
        return false;
    }
}
