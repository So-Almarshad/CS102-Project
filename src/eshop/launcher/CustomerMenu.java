/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;

import eshop.view.BasketViewer;
import eshop.view.ProductBrowser;

/**
 *
 * @author abdul
 */
public class CustomerMenu extends Menu{
    public CustomerMenu(Eshop eshop) {
        super(eshop, "CUSTOMER MENU", "Browse products", "View basket", "Logout");
    }

    @Override
    public void select(int optionNum) {
        switch(optionNum) {
            case 1: 
                eshop.setActiveMenu(new ProductBrowser(eshop)); 
                break;
            case 2: 
                eshop.setActiveMenu(new BasketViewer(eshop, this)); 
                break;
            case 3: 
                eshop.setActiveUser(null);
                eshop.setActiveMenu(new MainMenu(eshop));
                break;
        }
    }
}
