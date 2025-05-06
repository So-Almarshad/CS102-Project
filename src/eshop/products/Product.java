/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.products;

import java.io.Serializable;
import java.util.*;
import eshop.util.Util;
/**
 *
 * @author Saud
 */
public class Product implements Serializable{
    protected Catalog catalog;
    protected String category;
    protected String productId;
    protected String brand;
    protected String name;
    protected String description;
    protected double price;
    protected int quantity;
    
    public static final String CLOTH = "Cloth";
    public static final String COMPUTER = "Computer";
    public static final String PAPER_BOOK = "Paper Book";
    public static final String EBOOK = "E-Book";
    
    public static final String CATEGORY = "Category";
    public static final String PRODUCT_ID = "Product ID";
    public static final String BRAND = "Brand";
    public static final String NAME = "Name";
    public static final String DESCRIPTION = "Description";
    
    public Product(Catalog catalog, String category, String brand, String name, String description, double price, int quantity) {
        this.catalog = catalog;
        
        this.category = category;
        
        this.brand = brand;
        
        this.name = name;
        
        this.description = description;
        
        if(price > 0)
            this.price = price;
        else throw new IllegalArgumentException("Price should be strictly positive");
        
        if(quantity > 0)
            this.quantity = quantity;
        else throw new IllegalArgumentException("Quantity should be positive");
    }

    /******GETTERS & SETTERS******/
    
    public String getProductId(){
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategory() {
        return category;
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
        if (quantity > 0)
            this.quantity = quantity;
        else throw new IllegalArgumentException("quantity should be positive");
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Catalog getCatalog() {
        return catalog;
    }
    
    /******METHODS******/
    
    public void generateId() {
        long idNum = (long)(1000000000L + catalog.getProductCount());
        String id = ((Long)idNum).toString();
        productId = id;
    }
    
    public boolean meetsCriteria(Set<String> criteria, String searchStr) {
        Set<String> productDetails = new HashSet<>();
        if(criteria.isEmpty()) {
            productDetails.add(category.toLowerCase());
            productDetails.add(productId);
            addMultipleWords(productDetails, brand.toLowerCase());
            addMultipleWords(productDetails, name.toLowerCase());
            addMultipleWords(productDetails, description.toLowerCase());
            return productDetails.contains(searchStr.toLowerCase());
        }
        if(criteria.contains(CATEGORY))
            productDetails.add(category.toLowerCase());
        if(criteria.contains(PRODUCT_ID))
            productDetails.add(productId);
        if(criteria.contains(BRAND))
            addMultipleWords(productDetails, brand.toLowerCase());
        if(criteria.contains(NAME))
            addMultipleWords(productDetails, name.toLowerCase());
        if(criteria.contains(DESCRIPTION))
            addMultipleWords(productDetails, description.toLowerCase());
        return productDetails.contains(searchStr.toLowerCase());
    }
    
    private void addMultipleWords(Set<String> set, String str) {
        String[] descriptionWords = str.split(" ");
        for(String s : descriptionWords) {
            set.add(s);
        }
    }
    
    //divides the description into lines of length n and returns the result
    public static String splitDescriptionIntoLines(String str, int n) {
        StringBuilder builder = new StringBuilder(str);
        builder.insert(0, '\t');
        int count = 0;
        for(int i = 0; i < str.length(); i++) {
            if(builder.charAt(i) == ' ' && builder.charAt(i + 1) != ' ' )
                count++;
            if(count == n) {
                builder.insert(i + 1, '\n');
                count = 0;
            }
        }
        return builder.toString();
    }
    
    //Returns the top section of the product's fields
    private String getStartString() {
        return "ID: " + productId + '\n'
             + "Category: " + category + '\n'
             + "Brand: " + brand + '\n'
             + "Name: " + name + '\n';
    }
    
    //Returns the middle section of the product's fields, including category
    //specific fields. Overriden by subtypes.
    protected String getMiddleString() {
        return "";
    }
    
    //Returns the end section of the product's fields
    private String getEndString() {
        return "Description:\n" + splitDescriptionIntoLines(description, 23) + "\n\n"
             + "Price: " + price + '\n'
             + "Quantity: " + quantity + '\n';
    }
    
    @Override
    public String toString() {
        return getStartString() + getMiddleString() + getEndString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.brand, other.brand)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.description, other.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, productId, brand, name, description, price, quantity);
    }
    
    
}
