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
public class Catalog implements Serializable{
    private HashMap<String, Product> catalog;
    private File inventoryFile;
    
    public Catalog(String dir) throws Exception {
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
    * Adds n products to the catalog and increases its quantity by n
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
        if(catalog.containsKey(productID)) {
            catalog.get(productID).setQuantity(0);
            catalog.remove(productID);
        }
    }
    
    // Returns a sorted catalog as a LinkedHashMap using a comparator=
    public Map<String, Product> getSorted(Comparator<Product> c) {
        List<Product> list = new ArrayList<>(catalog.values());
        Collections.sort(list, c);
        LinkedHashMap<String, Product> sortedCatalog = new LinkedHashMap<>();
        for(Product p : list) {
            sortedCatalog.put(p.getProductId(), p);
        }
        return sortedCatalog;
    }
    
    public Map<String, Product> searchSorted(Comparator<Product> c) {
        
        return null;
    }
    
    public Map<String, Product> search(Product key, Comparator<Product> c) {
        List<Product> list = new ArrayList<>(catalog.values());
        HashMap<String, Product> result = new HashMap<>();
        for(Product product : list)
            if(c.compare(product, key) == 0) 
                result.put(product.getProductId(), product);
        return result;
    }
    
    public void close() throws Exception {
        saveInventoryData();
    }
}
