/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.view;

import eshop.editor.ProductEditor;
import eshop.launcher.AdminProductMenu;
import eshop.launcher.CustomerMenu;
import eshop.launcher.Eshop;
import eshop.launcher.Menu;
import eshop.products.*;
import eshop.users.*;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import eshop.util.Util;
import eshop.util.Comparators;
import java.util.Collections;

/**
 *
 * @author abdul
 */
public class ProductBrowser extends Menu{
    
    private static final String SEARCH_OPTIONS = "1. Category\n"
                                               + "2. Product ID\n"
                                               + "3. Brand\n"
                                               + "4. Name\n"
                                               + "5. Description\n"
                                               + "6. Done\n";
    private static final String SORT_OPTIONS = "1. Category\n"
                                             + "2. ID\n"
                                             + "3. Brand\n"
                                             + "4. Name\n"
                                             + "5. Description\n"
                                             + "6. Price\n"
                                             + "7. Memory size\n"
                                             + "8. Processor speed\n"
                                             + "9. Hard disk size\n"
                                             + "10. Title\n"
                                             + "11. Author\n"
                                             + "12. Switch order\n"
                                             + "13. Back\n";
    
    private static final String TABLE_HEADER = String.format("%-8s%-15s%-20s%-20s%-20s%-40s%-15s%s",
            "Num.", "ID", "Category", "Brand", "Name", "Description", "Price", "Quantity");
    private static final String SEPERATOR = "-".repeat(TABLE_HEADER.length());
    private User user;
    private Catalog catalog;
    private List<Product> productList;
    private Set<String> filter;
    private int initialSize;
    private int pageNum;
    private int lastPage;
    private boolean adminAccess;
    
    public ProductBrowser(Eshop eshop) {
        super(eshop);
        
        header = "BROWSE PRODUCTS";
        user = eshop.getActiveUser();
        adminAccess = user instanceof Administrator;
        catalog = eshop.getCatalog();
        filter = new HashSet<>();
        productList = catalog.getList();
        initialSize = catalog.getProductTable().size();
        pageNum = 1;
        updateOptions();
    }
    
    @Override
    public void display() {
        updateOptions();
        if(initialSize < catalog.getProductTable().size()) 
            productList = catalog.getList();
        initialSize = catalog.getProductTable().size();
        System.out.println(header);
        System.out.println(SEPERATOR);
        System.out.println(TABLE_HEADER);
        System.out.println(SEPERATOR);
        if(pageNum > lastPage)
            pageNum = lastPage;
        if(productList.isEmpty()) {
            System.out.println("No products found" + "\n".repeat(19));
        }
        else {
            int lower = pageNum * 10 - 10;
            int upper;
            if(pageNum == lastPage && productList.size() % 10 != 0)
                upper = pageNum * 10 + (productList.size() % 10) - 10;
            else
                upper = pageNum * 10;
            
            for(int i = lower; i < upper; i++) {
                System.out.println(rowString(i + 1, productList.get(i)));
            }
        }
        if(pageNum == lastPage && productList.size() % 10 != 0)
            System.out.println("\n".repeat((10 - productList.size() % 10) * 2 - 1));
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
        if(adminAccess)
            selectAdminOptions(optionNum); 
        else
            selectCustomerOptions(optionNum);
    }
    
    private void selectAdminOptions(int optionNum) {
        switch(optionNum) {
            case 1: 
                selectSearchCriteria();
                break;
            case 2:
                System.out.print("Enter search string: ");
                String searchStr = input.nextLine().trim();
                productList = catalog.search(filter, searchStr);
                pageNum = 1;
                break;
            case 3:
                selectSortOptions();
                break;
            case 4:
                productList = catalog.getList();
                pageNum = 1;
                break;
            case 5:
                System.out.print("Enter product ID or listing num.: ");
                String selection = input.nextLine().trim();
                if(!Util.isLong(selection)) {
                    System.out.println("Selection num. must be a positive integer");
                    Util.pause(input);
                    break;
                }
                long selectionNum = Long.parseLong(selection);
                if(selection.length() == 10 && catalog.contains(selection)) {
                    eshop.setActiveMenu(new ProductViewer(eshop, catalog.get(selection), this));
                }
                else if(selectionNum <= productList.size()){
                    eshop.setActiveMenu(new ProductViewer(eshop, productList.get((int)selectionNum - 1), this));
                }
                
                else {
                    System.out.println("Product not found");
                    Util.pause(input);
                }
                break;
            case 6:
                eshop.setActiveMenu(new ProductEditor(eshop, this)); 
                break;
            case 7:
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
            case 8:
                if(pageNum > 1)
                    pageNum--;
                break;
            case 9:
                if(pageNum < lastPage)
                    pageNum++;
                break;
            case 10:
                eshop.setActiveMenu(new AdminProductMenu(eshop));
                break;
            default:
                System.out.println("That is not an option");
                Util.pause(input);
        }
    }
//    setOptions("Filter: " + filterString(), "Search", "Reset view\n", "Select product", "View basket\n", "select page", "Previous page", "Next page", "Logout");
    private void selectCustomerOptions(int optionNum) {
        switch(optionNum) {
            case 1: 
                selectSearchCriteria();
                break;
            case 2:
                System.out.print("Enter search string: ");
                String searchStr = input.nextLine().trim();
                productList = catalog.search(filter, searchStr);
                pageNum = 1;
                break;
            case 3:
                productList = catalog.getList();
                pageNum = 1;
                break;
            case 4:
                System.out.print("Enter product ID or listing num.: ");
                String selection = input.nextLine().trim();
                if(!Util.isLong(selection)) {
                    System.out.println("Selection num. must be a positive integer");
                    Util.pause(input);
                    break;
                }
                Product product1 = getFromSelection(selection);
                if(product1 == null) {
                    System.out.println("Product not found");
                    Util.pause(input);
                    break;
                }
                eshop.setActiveMenu(new ProductViewer(eshop, product1, this));
                break;
            case 5: 
                System.out.print("Enter product ID or listing num.: ");
                String selection1 = input.nextLine().trim();
                if(!Util.isLong(selection1)) {
                    System.out.println("Selection num. must be a positive integer");
                    Util.pause(input);
                    break;
                }
                Product product2 = getFromSelection(selection1);
                if(product2 == null) {
                    System.out.println("Product not found");
                    Util.pause(input);
                    break;
                }
                
                System.out.print("Enter amount to add: ");
                String amount = input.nextLine().trim();
                if(!Util.isInteger(amount)) {
                    System.out.println("Amount to buy must be a positive integer");
                    Util.pause(input);
                    break;
                }
                int amountToAdd = Integer.parseInt(amount);
                ((Customer)user).getBasket().add(product2, amountToAdd);
                System.out.println("Product added to basket");
                Util.pause(input);
                break;
            case 6:
                eshop.setActiveMenu(new BasketViewer(eshop, this));
                break;
            case 7:
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
            case 8:
                if(pageNum > 1)
                    pageNum--;
                break;
            case 9:
                if(pageNum < lastPage)
                    pageNum++;
                break;
            case 10:
                eshop.setActiveMenu(new CustomerMenu(eshop));
                break;
            default:
                System.out.println("That is not an option");
                Util.pause(input);
        }
    }
    
    private void selectSearchCriteria() {
        boolean stillChoosing = true;
        while(stillChoosing) {
            System.out.println(("\n").repeat(60));
            System.out.println("FILTER OPTIONS");
            System.out.println("--------------");
            System.out.println(SEARCH_OPTIONS);
            System.out.println("Filter: " + filterString());
            System.out.print("Enter filter num. to toggle: ");
            String inputStr = input.nextLine().trim();
            
            if(!Util.isInteger(inputStr)) {
                System.out.println("Option num. should be a positive integer");
                Util.pause(input);
                continue;
            }
            
            switch(Integer.parseInt(inputStr)) {
                case 1:
                    if(filter.contains(Product.CATEGORY))
                        filter.remove(Product.CATEGORY);
                    else
                        filter.add(Product.CATEGORY);
                    break;
                case 2:
                    if(filter.contains(Product.PRODUCT_ID))
                        filter.remove(Product.PRODUCT_ID);
                    else
                        filter.add(Product.PRODUCT_ID);
                    break;
                case 3:
                    if(filter.contains(Product.BRAND))
                        filter.remove(Product.BRAND);
                    else
                        filter.add(Product.BRAND);
                    break;
                case 4:
                    if(filter.contains(Product.NAME))
                        filter.remove(Product.NAME);
                    else
                        filter.add(Product.NAME);
                    break;
                case 5:
                    if(filter.contains(Product.DESCRIPTION))
                        filter.remove(Product.DESCRIPTION);
                    else
                        filter.add(Product.DESCRIPTION);
                    break;
                case 6:
                    stillChoosing = false;
                    break;
                default:
                    System.out.println("That is not an option");
                    Util.pause(input);
            }
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
                    productList.sort(descending ? Collections.reverseOrder(Comparators.CATEGORY_COMPARATOR) : Comparators.CATEGORY_COMPARATOR);
                    break;
                case 2:
                    productList.sort(descending ? Collections.reverseOrder(Comparators.ID_COMPARATOR) : Comparators.ID_COMPARATOR);
                    break;
                case 3:
                    productList.sort(descending ? Collections.reverseOrder(Comparators.BRAND_COMPARATOR) : Comparators.BRAND_COMPARATOR);
                    break;
                case 4:
                    productList.sort(descending ? Collections.reverseOrder(Comparators.PRODUCT_NAME_COMPARATOR) : Comparators.PRODUCT_NAME_COMPARATOR);
                    break;
                case 5:
                    productList.sort(descending ? Collections.reverseOrder(Comparators.DESCRIPTION_COMPARATOR) : Comparators.DESCRIPTION_COMPARATOR);
                    break;
                case 6:
                    productList.sort(descending ? Collections.reverseOrder(Comparators.PRICE_COMPARATOR) : Comparators.PRICE_COMPARATOR);
                    break;
                case 7:
                    productList.sort(descending ? Comparators.DESCENDING_MEMORY_SIZE_COMPARATOR : Comparators.ASCENDING_MEMORY_SIZE_COMPARATOR);
                    break;
                case 8:
                    productList.sort(descending ? Comparators.DESCENDING_PROCESSOR_SPEED_COMPARATOR : Comparators.ASCENDING_PROCESSOR_SPEED_COMPARATOR);
                    break;
                case 9:
                    productList.sort(descending ? Comparators.DESCENDING_HARD_DISK_SIZE_COMPARATOR : Comparators.ASCENDING_HARD_DISK_SIZE_COMPARATOR);
                    break;
                case 10:
                    productList.sort(descending ? Comparators.DESCENDING_TITLE_COMPARATOR : Comparators.ASCENDING_TITLE_COMPARATOR);
                    break;
                case 11:
                    productList.sort(descending ? Comparators.DESCENDING_AUTHOR_COMPARATOR : Comparators.ASCENDING_AUTHOR_COMPARATOR);
                    break;
                case 12:
                    stillChoosing = true;
                    descending = !descending;
                    break;
                case 13:
                    break;
                default:
                    stillChoosing = true;
                    System.out.println("That is not an option");
                    Util.pause(input);
            }
        }
    }
    
    private void updateOptions() {
        if(adminAccess) 
            setOptions("Filter: " + filterString(), "Search", "Sort view", "Reset view\n", "Select product", "Add product\n", "Select page", "Previous page", "Next page", "Back");
        else
            setOptions("Filter: " + filterString(), "Search", "Reset view\n", "Select product", "Add to basket", "View basket\n", "select page", "Previous page", "Next page", "Back");
        if(!productList.isEmpty())
            lastPage = (int)Math.ceil(productList.size() / 10.0);
        else
            lastPage = 1;
    }
    
    private String filterString() {
        StringBuilder builder = new StringBuilder();
        if(filter.isEmpty())
            return "None";
        for(String s : filter) {
            builder.append(s);
            builder.append(", ");
        }
        return builder.substring(0, builder.length() - 2);
    }
    
    private String rowString(int rowNum, Product product) {
        String brand = Util.cut(product.getBrand(), 15);
        String name = Util.cut(product.getName(), 15);
        String description = Util.cut(product.getDescription(), 35);
        String rowNumStr = Integer.toString(rowNum) + ". ";
        String row = String.format("%-8s%-15s%-20s%-20s%-20s%-40s%-15.2f%d\n", 
                rowNumStr, product.getProductId(), product.getCategory(), 
                brand, name, description, product.getPrice(),
                product.getQuantity());
        return row;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
    
    private Product getFromSelection(String selection) {
        long selectionNum = Long.parseLong(selection);
        if(selection.length() == 10 && catalog.contains(selection)) {
            return catalog.get(selection);
        }
        else if(selectionNum <= productList.size()){
            return productList.get((int)selectionNum - 1);
        }
        else {
            return null;
        }
    }
}
