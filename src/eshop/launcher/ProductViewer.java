/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;

import eshop.editor.ClothEditor;
import eshop.editor.ComputerEditor;
import eshop.editor.EBookEditor;
import eshop.editor.PaperBookEditor;
import eshop.editor.ProductEditor;
import eshop.editor.ProductSpec;
import eshop.products.*;
import eshop.users.Administrator;
import eshop.util.Util;
/**
 *
 * @author abdul
 */
public class ProductViewer extends Menu {
    
    private Product product;
    private Menu returnMenu;
    private Catalog catalog;
    private boolean adminAccess;
    private boolean updatedProduct;
    
    public ProductViewer(Eshop eshop, Product product, Menu returnMenu) {
        super(eshop);
        this.product = product;
        this.returnMenu = returnMenu;
        this.adminAccess = eshop.getActiveUser() instanceof Administrator;
        this.catalog = eshop.getCatalog();
        updatedProduct = false;
        header = "PRODUCT DETAILS";
        if(adminAccess) 
            setOptions("Update", "Delete", "Back");
        else
            setOptions("Add to cart", "Back");
    }

    @Override
    public void display() {
        System.out.println(header);
        System.out.println("-".repeat(146));
        System.out.println(product);
        System.out.println("-".repeat(146));
        StringBuilder optionsString = new StringBuilder();
        for(int i = 0; i < options.length; i++) {
            optionsString.append((i + 1) + ". " + options[i] + '\n');
        }
        System.out.println(optionsString);
    }
    
    @Override
    public void select(int optionNum) {
        if(adminAccess)
            selectAdminOptions(optionNum);
        else
            selectCustomerOptions(optionNum);
    }
    
    private void selectAdminOptions(int optionNum) {
        switch(optionNum) {
            case 1: 
                updatedProduct = true;
                ProductSpec spec = new ProductSpec(product);
                selectProductEditor(spec);
                break;
            case 2:
                catalog.delete(product.getProductId());
                System.out.println("Product successfully deleted");
                Util.pause(input);
                if(returnMenu instanceof ProductBrowser productBrowser)
                    productBrowser.getProductList().remove(product);
                eshop.setActiveMenu(returnMenu);
                break;
            case 3:
                if(updatedProduct && returnMenu instanceof ProductBrowser productBrowser)
                    productBrowser.setProductList(catalog.getList());
                eshop.setActiveMenu(returnMenu);
                break;
            default:
                System.out.println("That is not an option");
                Util.pause(input);
        }
    }
    
    private void selectCustomerOptions(int optionNum) {
        
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    
}
