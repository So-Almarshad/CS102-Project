/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;

/**
 *
 * @author abdul
 */
public class AdminCustomerMenu extends Menu {
    public AdminCustomerMenu(Eshop eshop) {
        super(eshop, "ADMIN MENU: CUSTOMERS", "View Customers", "Search Customers", "Sort Customers", "Back");
    }

    @Override
    public void select(int optionNum) {
        switch(optionNum) {
            case 4: eshop.setActiveMenu(new AdminMenu(eshop)); break;
        }
    }
}
