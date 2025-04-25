/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.users;

import java.util.*;
import eshop.launcher.Eshop;
/**
 *
 * @author abdul
 */
public class CustomerDatabase {
    
    private Eshop eshop;
    private Map<String, Customer> customerTable;
    
    public CustomerDatabase(Eshop eshop, Map<String, Customer> customerTable) {
        this.eshop = eshop;
        this.customerTable = customerTable;
        
    }
    
    /******GETTERS & SETTERS******/
    public Map<String, Customer> getCustomerTable() {
        return customerTable;
    }
    
    public void setCustomerTable(Map<String, Customer> customerTable) {
        this.customerTable = customerTable;
    }
    
    /******UTILITIES******/
    
    //Registers a new customer
    //If a customer with the same username exists, returns false and doesn't register the customer
    public boolean register(Customer customer) {
        String username = customer.getUsername();
        Set<String> usernameSet = eshop.getUsernameSet();
        if(usernameSet.contains(username)) {
            return false;
        }
        customerTable.put(customer.getEmail(), customer);
        usernameSet.add(username);
        return true;
    }
    
    public void remove(String username) {
        customerTable.remove(username);
    }
}
