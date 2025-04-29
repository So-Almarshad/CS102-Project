package eshop.util;

import java.util.*;
import eshop.products.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author abdul
 */

//This class provides helper methods for the catalog
public final class CatalogUtil {
    private CatalogUtil() {}
    
    // Returns a sorted catalog as a LinkedHashMap using a comparator (for admin)
    public static LinkedHashMap<String, Product> getSortedProducts(Catalog catalog, Comparator<Product> c) {
        List<Product> list = new ArrayList<>(catalog.getProductTable().values());
        Collections.sort(list, c);
        LinkedHashMap<String, Product> sortedCatalog = new LinkedHashMap<>();
        for(Product p : list) {
            sortedCatalog.put(p.getProductId(), p);
        }
        return sortedCatalog;
    }
    
    // Returns the search results of a sorted catalog as a LinkedHashMap (for admin)
    public static LinkedHashMap<String, Product> searchSortedProducts(Catalog sortedCatalog, 
            Product key, Comparator<Product> c) {
        List<Product> list = new ArrayList<>(sortedCatalog.getProductTable().values());
        LinkedHashMap<String, Product> result = new LinkedHashMap<>();
        
        for(Product product : list) {
            if(c.compare(product, key) == 0) 
                result.put(product.getProductId(), product);
        }
        return result;
    }
    
    // Returns the search results of a catalog as a HashMap (for customer)
    public static Map<String, Product> searchProducts(Catalog catalog, 
            Product key, Comparator<Product> c) {
        List<Product> list = new ArrayList<>(catalog.getProductTable().values());
        HashMap<String, Product> result = new HashMap<>();
        
        for(Product product : list) {
            if(c.compare(product, key) == 0) 
                result.put(product.getProductId(), product);
        }
        return result;
    }
//    
//    public static Map getView(Map<String, Product> catalog)
}
