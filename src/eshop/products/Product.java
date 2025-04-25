/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.products;

import java.io.Serializable;

/**
 *
 * @author Saud
 */
public class Product implements Comparable<Product>, Serializable{
    private Catalog catalog;
    private final String CATEGORY;
    private String productId;
    private String brand;
    private String name;
    private String description;
    private double price;
    private int quantity;
    
    public Product(Catalog catalog, String CATAGORY, String brand, String name, String description, double price, int quantity) {
        this.catalog = catalog;
        
        this.CATEGORY = CATAGORY;
        
        this.brand = brand;
        
        this.name = name;
        
        this.description = description;
        
        if (price>0)
            this.price = price;
        else throw new IllegalArgumentException("Price should be strictly positive");
        
        if (quantity>=0)
            this.quantity = quantity;
        else throw new IllegalArgumentException("quantity should be positive");
    }

    /******GETTERS & SETTERS******/
    
    public String getProductId(){
        return productId;
    }

    public String getCategory() {
        return CATEGORY;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0)
            this.price = price;
        else throw new IllegalArgumentException("Price should be strictly positive");
    }

    public int getQuantity() {
        return quantity;
    }

    
    public void setQuantity(int quantity) {
        if (quantity >= 0)
            this.quantity = quantity;
        else throw new IllegalArgumentException("quantity should be positive");
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    /******METHODS******/
    
    @Override
    public int compareTo(Product p){
        if (p == null) throw new IllegalArgumentException("Cannot compare null");
        return ((Integer)this.getQuantity()).compareTo(p.getQuantity());
    }
}
