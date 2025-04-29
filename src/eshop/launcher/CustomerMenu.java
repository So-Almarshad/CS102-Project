/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;

/**
 *
 * @author abdul
 */
public class CustomerMenu extends Menu {
    public CustomerMenu(Eshop eshop) {
        super(eshop, "CUSTOMER MENU", "View products", "Search products", "Add to basket", "Proceed to checkout ", "Logout");
    }

    @Override
    public void select(int optionNum) {
        switch(optionNum) {
            case 5:
                eshop.setActiveUser(null);
                eshop.setActiveMenu(new MainMenu(eshop));
        }
    }
}
