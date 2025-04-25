/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;

import eshop.users.*;
import eshop.products.Catalog;
import eshop.products.Product;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

public class Eshop {
    
    private Catalog catalog;
    private CustomerDatabase customerDatabase;
    private File catalogFile;
    private File customerFile;
    private File adminFile;
    private Administrator admin;
    private User activeUser;
    private Menu activeMenu;
    private Scanner input;
    private boolean isRunning;
    
    public Eshop(File adminFile, File customerFile, File catalogFile) throws Exception{
        this.adminFile = adminFile;
        this.customerFile = customerFile;
        this.catalogFile = catalogFile;
        loadData();
    }
    
    public void launch(Scanner input) {
        if(isRunning) {
            System.out.println("This Eshop is already running");
            return; //maybe throw an exception?
        }
        isRunning = true;
        this.input = input;
        activeMenu = new MainMenu(this);
        while(activeMenu != null) {
            activeMenu.display();
            activeMenu.select(input.nextInt());
        }
    }
    
    public void close() {
        this.input = null;
        this.activeMenu = null;
        isRunning = false;
    }
    
    /******GETTERS & SETTERS******/
    public Administrator getAdmin() {
        return admin;
    }
    
    public CustomerDatabase getCustomerDatabase() {
        return customerDatabase;
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
    public void saveAdminData() throws Exception {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(adminFile))) {
            out.writeObject(adminFile);
        }
    }
    
    public final void loadAdminData() throws Exception {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(adminFile))) {
            admin = (Administrator)in.readObject();
        }
    }
    
    public void saveCustomerData() throws Exception {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(customerFile))) {
            out.writeObject(customerDatabase);
        }
    }
    
    public final void loadCustomerData() throws Exception {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(customerFile))) {
            customerDatabase = (CustomerDatabase)in.readObject();
        }
    }
    
    public void saveCatalogData() throws Exception {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(catalogFile))) {
            out.writeObject(catalog);
        }
    }
    
    public final void loadCatalogData() throws Exception {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(catalogFile))) {
            catalog = (Catalog)in.readObject();
        }
    }
    
    public void saveData() throws Exception {
        saveAdminData();
        saveCustomerData();
        saveCatalogData();
    }
    
    public final void loadData() throws Exception {
        loadAdminData();
        loadCustomerData();
        loadCatalogData();
    }
}
