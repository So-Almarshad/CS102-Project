/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;

import eshop.view.ProductBrowser;
import eshop.editor.ComputerEditor;
import eshop.editor.ProductEditor;
import eshop.editor.PaperBookEditor;
import eshop.editor.ClothEditor;
import eshop.editor.ProductSpec;
import eshop.editor.EBookEditor;
import java.util.List;
import java.util.ArrayList;
import eshop.products.Product;
import eshop.products.Catalog;
import eshop.util.Util;

/**
 *
 * @author abdul
 */
public class AdminProductMenu extends Menu {
    private Catalog catalog;
    
    public AdminProductMenu(Eshop eshop) {
        super(eshop, "ADMIN MENU: PRODUCTS", "Browse products", "Add Product", 
                "Update Product", "Delete Product", "Back");
        catalog = eshop.getCatalog();
    }
    
    @Override
    public void select(int optionNum) {
        switch(optionNum) {
            case 1:
                eshop.setActiveMenu(new ProductBrowser(eshop));
                break;
            case 2: 
                eshop.setActiveMenu(new ProductEditor(eshop, this)); 
                break;
            case 3:
                System.out.print("Enter product ID: ");
                String id1 = input.nextLine();
                if(catalog.contains(id1)) {
                    ProductSpec spec = new ProductSpec(catalog.get(id1));
                    selectProductEditor(spec);
                }
                else {
                    System.out.println("Product not found");
                    Util.pause(input);
                }
                break;
            case 4:
                System.out.print("Enter product ID: ");
                String id2 = input.nextLine();
                if(catalog.contains(id2)) {
                    catalog.delete(id2);
                    System.out.println("Product successfully deleted");
                }
                else {
                    System.out.println("Product not found");
                }
                Util.pause(input);
                break;
            case 5: 
                eshop.setActiveMenu(new AdminMenu(eshop)); break;
            default:
                System.out.println("That is not an option");
                Util.pause(input);
        }
    }
    
    //Selects which product editor to use when updating a product, based on the
    //product's category
    private void selectProductEditor(ProductSpec spec) {
        switch(spec.getCategory()) {
            case Product.CLOTH: 
                eshop.setActiveMenu(new ClothEditor(eshop, spec, this, true));
                break;
            case Product.COMPUTER:
                eshop.setActiveMenu(new ComputerEditor(eshop, spec, this, true));
                break;
            case Product.PAPER_BOOK:
                eshop.setActiveMenu(new PaperBookEditor(eshop, spec, this, true));
                break;
            case Product.EBOOK:
                eshop.setActiveMenu(new EBookEditor(eshop, spec, this, true));
                break;
        }
    }
}
