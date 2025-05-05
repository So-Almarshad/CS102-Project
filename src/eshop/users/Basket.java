/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.users;

import eshop.products.Product;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author abdul
 */
public class Basket implements Serializable{
    private Customer customer;
    private Map<String, BasketItem> basketTable;
    
    public Basket(Customer customer) {
        this(new HashMap<String, BasketItem>(), customer);
    }
    
    public Basket(Map<String, BasketItem> basketTable, Customer customer) {
        this.basketTable = basketTable;
        this.customer = customer;
    }
    
    //Adds any amount of products to the basket. If the amount exceeds the product's
    //quantity in the catalog, then the amount to buy will default to the product's
    //quantity
    public void add(Product product, int amountToBuy) {
        String productId = product.getProductId();
        if(this.contains(productId)) {
            int amountInBasket = basketTable.get(productId).getAmountToBuy();
            int total = amountToBuy + amountInBasket;
            if(total <= product.getQuantity())
                basketTable.get(productId).setAmountToBuy(total);
            else
                basketTable.get(productId).setAmountToBuy(product.getQuantity());
        }
        else {
            if(amountToBuy <= product.getQuantity())
                basketTable.put(productId, new BasketItem(product, amountToBuy));
            else
                basketTable.put(productId, new BasketItem(product, product.getQuantity()));
        }
    }
    
    public void remove(String productId) {
        basketTable.remove(productId);
    }
    
    //removes any amount of products from the basket. if the amount to remove
    //exceeds or is equal to the amount in basket, removes the product completely
//    public void remove(String productId, int amountToRemove) {
//        BasketItem basketItem = basketTable.get(productId);
//        int amountInBasket = basketItem.getAmountToBuy();
//        if(amountToRemove < amountInBasket)
//            basketTable.get(productId).setAmountToBuy(amountInBasket - amountToRemove);
//        else
//            basketTable.remove(productId);
//    }
    
    //Purchases all items in the basket. 
    //Expects that customer payment information is filled.
    public boolean purchase() {
        if(customer.getPaymentCard() == null || customer.getShippingAddress() == null)
            throw new RuntimeException("Purchase without payment information");
        List<BasketItem> list = new ArrayList<>(basketTable.values());
        for(BasketItem item : list) {
            if(!item.isAvailable())
                return false;
        }
        for(BasketItem item : list) {
            Product product = item.getProduct();
            if(item.getAmountToBuy() == product.getQuantity())
                product.getCatalog().delete(product.getProductId());
            else
                product.setQuantity(product.getQuantity() - item.getAmountToBuy());
            basketTable.remove(item.getProductId());
        }
        return true;
    }
    
    public BasketItem get(String productId) {
        return basketTable.get(productId);
    }
    
    public Product getProduct(String productId) {
        return basketTable.get(productId).getProduct();
    }
    
    public boolean contains(String productId) {
        return basketTable.containsKey(productId);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<String, BasketItem> getBasketTable() {
        return basketTable;
    }

    public void setBasketTable(Map<String, BasketItem> basketTable) {
        this.basketTable = basketTable;
    }
    
    public List<BasketItem> getList() {
        return new ArrayList<>(basketTable.values());
    }
}
