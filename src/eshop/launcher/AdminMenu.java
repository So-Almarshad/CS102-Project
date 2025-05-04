/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;

/**
 *
 * @author abdul
 */
public class AdminMenu extends Menu {
    public AdminMenu(Eshop eshop) {
        super(eshop, "ADMIN MENU", "Products", "Customers", "Logout");
    }

    @Override
    public void select(int optionNum) {
        switch(optionNum) {
            case 1: eshop.setActiveMenu(new AdminProductMenu(eshop)); break;
            case 2: eshop.setActiveMenu(new CustomerBrowser(eshop)); break;
            case 3: 
                eshop.setActiveUser(null);
                eshop.setActiveMenu(new MainMenu(eshop));
                break;
        }
    }
    
}
