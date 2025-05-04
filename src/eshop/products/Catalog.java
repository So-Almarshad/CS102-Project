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
    private long productCount;
    
    public Catalog() {
        this(new HashMap<>());
    }
    
    public Catalog(Map<String, Product> productTable) {
        this.productTable = productTable;
        this.productCount = 0;
    }
    
    /******GETTERS & SETTERS******/
    
    public Map<String, Product> getProductTable() {
        return productTable;
    }

    public void setProductTable(Map<String, Product> productTable) {
        this.productTable = productTable;
    }

    public long getProductCount() {
        return productCount;
    }
    
    /******UTILITIES******/
    
    public Product get(String productId) {
        return productTable.get(productId);
    }
    
    //Adds a product to the catalog if there are no identical products
    //Otherwise, increases the quantity of the existing product 
    //by the quantity of the product passed in the argument
    public void add(Product product) {
        int quantity = product.getQuantity();
        if(this.containsValue(product)) {
            Product existingProduct = findMatchingProduct(product);
            existingProduct.setQuantity(existingProduct.getQuantity() + quantity);
        }
        else {
            product.generateId();
            productCount++;
            productTable.put(product.getProductId(), product);
        }
    }
    
    //Maps a new product to an existing ID, overwriting it
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
            productTable.remove(productID);
        }
        else 
            throw new RuntimeException("Product does not exist");
    }
    
    //Returns true if the catalog contains the given ID and false otherwise
    public boolean contains(String productID) {
        return productTable.containsKey(productID);
    }
    
    //Returns true of the catalog contains the given product and false otherwise
    public boolean containsValue(Product product) {
        return productTable.containsValue(product);
    }
    
    //Returns a list of all products in the catalog
    public List<Product> getList() {
        return new ArrayList<Product>(productTable.values());
    }
    
    //Returns a list of products that satisfy the search conditions
    public List<Product> search(Set<String> criteria, String searchStr) {
        List<Product> searchResults = new ArrayList<>();
        for(Product product : productTable.values())
            if(product.meetsCriteria(criteria, searchStr))
                searchResults.add(product);
        return searchResults;
    }
    
    //Returns a product in the catalog that matches the product passed to the
    //argument
    public Product findMatchingProduct(Product product) {
        List<Product> list = new ArrayList<>(productTable.values());
        for(Product p : list) {
            if(product.equals(p))
                return p;
        }
        return null;
    }
}
