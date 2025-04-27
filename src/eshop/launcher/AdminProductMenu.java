/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;

/**
 *
 * @author abdul
 */
public class AdminProductMenu extends Menu{
    public AdminProductMenu(Eshop eshop) {
        super(eshop, "ADMIN MENU: PRODUCTS", "View products", "Search products", "Sort products", "Add Product", "Remove Product", "Update Product", "Back");
    }

    @Override
    public void select(int optionNum) {
        switch(optionNum) {
            case 7: eshop.setActiveMenu(new AdminMenu(eshop)); break;
        }
    }  
}
