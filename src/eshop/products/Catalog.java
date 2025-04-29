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
    
    private Map<String, Product> productTable;
    
    public Catalog(Map<String, Product> products) {
        this.productTable = products;
    }
    
    /******GETTERS & SETTERS******/
    
    public Map<String, Product> getProductTable() {
        return productTable;
    }

    public void setProductTable(Map<String, Product> productTable) {
        this.productTable = productTable;
    }
    
    /******UTILITIES******/
    
    //Adds a product to the catalog if it has not been added yet
    //Otherwise, increases its quantity by one
    
    public Product get(String productId) {
        return productTable.get(productId);
    }
    
    public void add(Product product) {
        int quantity = product.getQuantity();
        if(this.contains(product.getProductId()))
            product.setQuantity(quantity + 1);
        else
            productTable.put(product.getProductId(), product);
    }
    
    //Adds n products to the catalog and increases its quantity by n
    public void add(Product product, int n) {
        int quantity = product.getQuantity();
        if(this.contains(product.getProductId())) {
            product.setQuantity(quantity + n);
        }
        else {
            productTable.put(product.getProductId(), product);
            product.setQuantity(quantity + n);
        }
    }
    
    public void update(String productId, Product product) {
        if(this.contains(productId)) {
            productTable.put(productId, product);
        }
        else
            throw new RuntimeException("Product does not exist");
    }
    
    //Removes a product from the catalog and sets its quantity to 0
    public void delete(String productID) {
        if(this.contains(productID)) {
            productTable.get(productID).setQuantity(0);
            productTable.remove(productID);
        }
        else throw new RuntimeException("Product does not exist");
    }
    
    public boolean contains(String productID) {
        return productTable.containsKey(productID);
    }
}
