/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.view;

import eshop.launcher.AdminMenu;
import eshop.launcher.Eshop;
import eshop.launcher.Menu;
import eshop.users.Customer;
import eshop.users.CustomerDatabase;
import eshop.util.Comparators;
import eshop.util.Util;
import java.util.List;

/**
 *
 * @author abdul
 */
public class CustomerListing extends Menu {
    private static final String SORT_OPTIONS = "1. Username\n"
                                             + "2. Name\n"
                                             + "3. Age\n"
                                             + "4. Switch order\n";
    
    private static final String TABLE_HEADER = String.format("%-8s%-30s%-30s%s",
            "Num.", "Username", "Name", "Age");
    private static final String SEPERATOR = "-".repeat(TABLE_HEADER.length());
    private CustomerDatabase database;
    private List<Customer> customerList;
    private int pageNum;
    private int lastPage;
    
    public CustomerListing(Eshop eshop) {
        super(eshop, "", "Sort view", "Reset view", "Select page", "Previous page", "Next page", "Back");
        
        header = "CUSTOMER LIST";
        database = eshop.getCustomerDatabase();
        customerList = database.getList();
        pageNum = 1;
        updateOptions();
    }
    
    @Override
    public void display() {
        updateOptions();
        System.out.println(header);
        System.out.println(SEPERATOR);
        System.out.println(TABLE_HEADER);
        System.out.println(SEPERATOR);
        if(pageNum > lastPage)
            pageNum = lastPage;
        if(customerList.isEmpty()) {
            System.out.println("No customers found" + "\n".repeat(19));
        }
        else {
            int lower = pageNum * 10 - 10;
            int upper;
            if(pageNum == lastPage && customerList.size() % 10 != 0)
                upper = pageNum * 10 + (customerList.size() % 10) - 10;
            else
                upper = pageNum * 10;
            
            for(int i = lower; i < upper; i++) {
                System.out.println(rowString(i + 1, customerList.get(i)));
            }
        }
        if(pageNum == lastPage && customerList.size() % 10 != 0)
            System.out.println("\n".repeat((10 - customerList.size() % 10) * 2 - 1));
        System.out.println(SEPERATOR);
        System.out.println("Page " + pageNum + " of " + lastPage + '\n');
        StringBuilder optionsString = new StringBuilder();
        for(int i = 0; i < options.length; i++) {
            optionsString.append((i + 1) + ". " + options[i] + '\n');
        }
        System.out.println(optionsString);
    }
    
    @Override
    public void select(int optionNum) {
        switch(optionNum) {
            case 1:
                selectSortOptions();
                break;
            case 2:
                customerList = database.getList();
                pageNum = 1;
                break;
            case 3:
                System.out.print("Enter page num.: ");
                String temp = input.nextLine().trim();
                if(Util.isInteger(temp)) {
                    int pageNumTemp = Integer.parseInt(temp);
                    pageNum = pageNumTemp < lastPage ? pageNumTemp : lastPage;
                }
                else {
                    System.out.println("Page num. should be a positive integer");
                    Util.pause(input);
                }
                break;
            case 4:
                if(pageNum > 1)
                    pageNum--;
                break;
            case 5:
                if(pageNum < lastPage)
                    pageNum++;
                break;
            case 6:
                eshop.setActiveMenu(new AdminMenu(eshop));
                break;
            default:
                System.out.println("That is not an option");
                Util.pause(input);
        }
    }
    
    private void selectSortOptions() {
        Boolean descending = false;
        boolean stillChoosing = true;
        while(stillChoosing) {
            stillChoosing = false;
            System.out.println(("\n").repeat(60));
            System.out.println("SELECT SORT OPTIONS");
            System.out.println("-------------------");
            System.out.println(SORT_OPTIONS);
            System.out.println("Order: " + (descending ? "Descending" : "Ascending"));
            System.out.print("Enter option num.: ");
            String inputStr = input.nextLine().trim();
            
            if(!Util.isInteger(inputStr)) {
                System.out.println("Option num. should be a positive integer");
                Util.pause(input);
                stillChoosing = true;
                continue;
            }
            
            switch(Integer.parseInt(inputStr)) {
                case 1: 
                    customerList.sort(descending ? Comparators.USERNAME_COMPARATOR.reversed() : Comparators.USERNAME_COMPARATOR);
                    break;
                case 2:
                    customerList.sort(descending ? Comparators.USERNAME_COMPARATOR.reversed() : Comparators.USERNAME_COMPARATOR);
                    break;
                case 3:
                    customerList.sort(descending ? Comparators.USERNAME_COMPARATOR.reversed() : Comparators.USERNAME_COMPARATOR);
                    break;
                case 4:
                    stillChoosing = true;
                    descending = !descending;
                    break;
                default:
                    stillChoosing = true;
                    System.out.println("That is not an option");
                    Util.pause(input);
            }
        }
    }
    
    private void updateOptions() {
        if(!customerList.isEmpty())
            lastPage = (int)Math.ceil(customerList.size() / 10.0);
        else
            lastPage = 1;
    }
    private String rowString(int rowNum, Customer customer) {
        String username = Util.cut(customer.getUsername(), 30);
        String name = Util.cut(customer.getName(), 30);
        String age = String.valueOf(customer.getAge());
        String rowNumStr = Integer.toString(rowNum) + ". ";
        return String.format("%-8s%-30s%-30s%s\n", rowNumStr, username, name, age);
    }
    
}
