/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.products;

import java.util.*;
import java.io.*;
/**
 *
 * @author abdul
 */
public class Catalog implements Serializable{
    
    private Map<String, Product> products;
    
    public Catalog(Map<String, Product> products) {
        this.products = products;
    }
    
    /******GETTERS & SETTERS******/
    
    public Map<String, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }
    
    /******UTILITIES******/
    
    //Adds a product to the catalog if it has not been added yet
    //Otherwise, increases its quantity by one
    public void add(Product product) {
        int quantity = product.getQuantity();
        if(quantity == 0)
            products.put(product.getProductId(), product);
        else
            product.setQuantity(quantity + 1);
    }
    
    //Adds n products to the catalog and increases its quantity by n
    public void add(Product product, int n) {
        int quantity = product.getQuantity();
        if(quantity == 0) {
            products.put(product.getProductId(), product);
            product.setQuantity(n);
        }
        else
            product.setQuantity(quantity + n);
    }
    
    //Removes a product from the catalog and sets its quantity to 0
    public void delete(String productID) {
        if(products.containsKey(productID)) {
            products.get(productID).setQuantity(0);
            products.remove(productID);
        }
    }
}
