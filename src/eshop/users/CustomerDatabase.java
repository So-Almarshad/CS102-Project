/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.users;

import java.util.*;
import java.io.*;
import eshop.launcher.Eshop;
/**
 *
 * @author abdul
 */
public class CustomerDatabase implements Serializable {
    
    private transient Eshop eshop;
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
    
    public Eshop getEshop() {
        return eshop;
    }
    
    public void setEshop(Eshop eshop) {
        this.eshop = eshop;
    }
    
    /******UTILITIES******/
    
    //Registers a new customer
    //If a customer with the same username exists, returns false and doesn't register the customer
    public boolean register(Customer customer) {
        String username = customer.getUsername();
        if(customerTable.containsKey(username)) {
            return false;
        }
        customerTable.put(customer.getUsername(), customer);
        return true;
    }
    
    public void remove(String username) {
        customerTable.remove(username);
    }
    
    public Customer find(String username) {
        return customerTable.get(username);
    }
    
    public List<Customer> getList() {
        return new ArrayList<>(customerTable.values());
    }
}
