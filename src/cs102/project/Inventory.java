/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CS102.Project;;

import java.util.*;
import java.io.*;
/**
 *
 * @author abdul
 */
public class Inventory implements Serializable{
    private HashMap<String, Product> catalog;
    private File inventoryFile;
    
    public Inventory(String dir) throws Exception {
        this.inventoryFile = new File(dir);
        loadInventoryData();
    }
    
    /******GETTERS & SETTERS******/
    public File getInventoryFile() {
        return inventoryFile;
    }
    
    public void setInventoryFile(File inventoryFile) {
        this.inventoryFile = inventoryFile;
    }
    
    public final void loadInventoryData() throws Exception {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(inventoryFile))) {
            catalog = (HashMap<String, Product>)in.readObject();
        }
    }
    
    public final void saveInventoryData() throws Exception {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(inventoryFile))) {
            out.writeObject(catalog);
        }
    }
    
    /******UTILITIES******/
    
    /* 
    * Adds a product to the catalog if it has not been added yet
    * Otherwise, increases its quantity by one
    */
    public void add(Product product) {
        int quantity = product.getQuantity();
        if(quantity == 0)
            catalog.put(product.getProductId(), product);
        else
            product.setQuantity(quantity + 1);
    }
    
    /* 
    * Adds n products to the catalog
    */
    public void add(Product product, int n) {
        int quantity = product.getQuantity();
        if(quantity == 0) {
            catalog.put(product.getProductId(), product);
            product.setQuantity(n);
        }
        else
            product.setQuantity(quantity + n);
    }
    
    //Removes a product from the catalog and sets its quantity to 0
    public void delete(String productID) {
        catalog.get(productID).setQuantity(0);
        catalog.remove(productID);
    }
    
    public void update(String productID, String attribute, String newValue) {
        
    }
    
    public void close() throws Exception {
        saveInventoryData();
    }
}
