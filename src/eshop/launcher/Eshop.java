/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;

import eshop.users.*;
import eshop.products.Catalog;
import eshop.products.Product;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.*;
import java.util.Scanner;
import eshop.util.Util;

public class Eshop {
    
    private Catalog catalog;
    private CustomerDatabase customerDatabase;
    private Set<String> usernameSet;
    private Administrator admin;
    private File catalogFile;
    private File userFile;
    private User activeUser;
    private Menu activeMenu;
    private Scanner input;
    private boolean isRunning;
    
    public Eshop(File userFile, File catalogFile) throws Exception{
        this.userFile = userFile;
        this.catalogFile = catalogFile;
        loadData();
    }
    
    public void launch(Scanner input) throws Exception {
        if(isRunning) {
            System.out.println("This Eshop is already running");
            return; //maybe throw an exception?
        }
        
        isRunning = true;
        this.input = input;
        
        if(admin == null) {
            activeMenu = new AdminRegistrationMenu(this);
            while(activeMenu != null) {
                try {
                    System.out.println(("\n").repeat(20));
                    activeMenu.display();
                    
                    System.out.print("Enter option num.: ");
                    String inputStr = input.nextLine().trim();
                    
                    if(Util.isNumeric(inputStr))
                        activeMenu.select(Integer.parseInt(inputStr));
                    else {
                        System.err.println("Option num. should be a positive integer");
                        Util.pause(input);
                    }
                } catch(InputMismatchException e) {
                    e.printStackTrace();
                    input.nextLine();
                    Util.pause(input);
                }
            }
            customerDatabase = new CustomerDatabase(this, new HashMap<>());
            usernameSet = new HashSet<>();
            usernameSet.add(admin.getName());
            saveUserData();
        }
        
        if(catalog == null) {
            catalog = new Catalog(new HashMap<>());
            saveCatalogData();
        }
        
        activeMenu = new MainMenu(this);
        while(activeMenu != null) {
            activeMenu.display();
            activeMenu.select(input.nextInt());
        }
    }
    
    public void close() throws Exception {
        this.input = null;
        this.activeMenu = null;
        saveData();
        isRunning = false;
    }
    
    /******GETTERS & SETTERS******/
    public Administrator getAdmin() {
        return admin;
    }
    
    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }
    
    public CustomerDatabase getCustomerDatabase() {
        return customerDatabase;
    }

    public Set<String> getUsernameSet() {
        return usernameSet;
    }
    
    public Catalog getCatalog() {
        return catalog;
    }
    
    public User getActiveUser() {
        return activeUser;
    }
    
    public Menu getActiveMenu() {
        return activeMenu;
    }
    
    public void setActiveMenu(Menu activeMenu) {
        this.activeMenu = activeMenu;
    }
    
    public Scanner getInput() {
        return input;
    }
    
    /******SAVE & LOAD******/
    public void saveUserData() throws Exception {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(userFile))) {
            out.writeObject(admin);
            out.writeObject(customerDatabase);
            out.writeObject(usernameSet);
        }
    }
    
    public final void loadUserData() throws Exception {
        if(userFile.exists()) {
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(userFile))) {
                admin = (Administrator)in.readObject();
                customerDatabase = (CustomerDatabase)in.readObject();
                usernameSet = (HashSet<String>)in.readObject();
                customerDatabase.setEshop(this);
            } catch(Exception e) {
                throw new IOException("Error: Invalid User File");
            }
        }
    }
    
    public void saveCatalogData() throws Exception {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(catalogFile))) {
            out.writeObject(catalog);
        }
    }
    
    public final void loadCatalogData() throws Exception {
        if(catalogFile.exists()) {
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(catalogFile))) {
                catalog = (Catalog)in.readObject();
            } catch(Exception e) {
                throw new IOException("Error: Invalid Catalog File");
            }
        }
    }
    
    public void saveData() throws Exception {
        saveUserData();
        saveCatalogData();
    }
    
    public final void loadData() throws Exception {
        loadUserData();
        loadCatalogData();
    }
}
