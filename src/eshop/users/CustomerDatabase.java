/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.users;

import java.util.*;

/**
 *
 * @author abdul
 */
public class CustomerDatabase {
    
    private Map<String, Customer> customers;
    
    public CustomerDatabase(Map<String, Customer> customers) {
        this.customers = customers;
    }
    
    /******GETTERS & SETTERS******/
    public Map<String, Customer> getCustomers() {
        return customers;
    }
    
    public void setCustomers(Map<String, Customer> customers) {
        this.customers = customers;
    }
    
    /******UTILITIES******/
    
    //Registers a new customer
    //If a customer with the same username exists, returns false and doesn't register the customer
    public boolean register(Customer customer) {
        if(usernameTaken(customer.getName())){
            return false;
        }
        customers.put(customer.getEmail(), customer);
        return true;
    }
    
    public void remove(String username) {
        customers.remove(username);
    }
    
    private boolean usernameTaken(String username) {
        List<Customer> list = new ArrayList<>(customers.values());
        for(Customer customer : list) 
            if(customer.getName().equals(username))
                return true;
        return false;
    }
}
