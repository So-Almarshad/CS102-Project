/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.view;

import eshop.launcher.Eshop;
import eshop.launcher.Menu;
import eshop.launcher.PaymentForm;
import eshop.products.Catalog;
import eshop.products.Product;
import eshop.users.Basket;
import eshop.users.BasketItem;
import eshop.users.Customer;
import eshop.util.Util;
import java.util.List;

/**
 *
 * @author abdul
 */
public class BasketViewer extends Menu {
    
    private Customer customer;
    private Basket basket;
    private Catalog catalog;
    private List<BasketItem> itemList;
    private Menu returnMenu;
    private int initialSize;
    private int pageNum;
    private int lastPage;
    private static final String TABLE_HEADER = String.format("%-8s%-15s%-20s%-20s%-20s%-40s%-15s%-15s%s",
            "Num.", "ID", "Category", "Brand", "Name", "Description", "Price", "In basket", "Quantity");
    private static final String SEPERATOR = "-".repeat(TABLE_HEADER.length());
    
    public BasketViewer(Eshop eshop, Menu returnMenu) {
        super(eshop);
        this.returnMenu = returnMenu;
        this.customer = (Customer)eshop.getActiveUser();
        this.basket = customer.getBasket();
        this.catalog = eshop.getCatalog();
        header = "BASKET";
        itemList = basket.getList();
        initialSize = basket.getBasketTable().size();
        pageNum = 1;
        for(BasketItem item : itemList) {
            if(catalog.get(item.getProductId()) == null)
                basket.remove(item.getProductId());
            else
                item.setProduct(catalog.get(item.getProductId()));
        }
        updateOptions();
    }
    
    @Override
    public void display() {
        updateOptions();
        if(initialSize < basket.getBasketTable().size()) 
            itemList = basket.getList();
        initialSize = basket.getBasketTable().size();
        System.out.println(header);
        System.out.println(SEPERATOR);
        System.out.println(TABLE_HEADER);
        System.out.println(SEPERATOR);
        if(pageNum > lastPage)
            pageNum = lastPage;
        if(itemList.isEmpty()) {
            System.out.println("No items found" + "\n".repeat(19));
        }
        else {
            int lower = pageNum * 10 - 10;
            int upper;
            if(pageNum == lastPage && itemList.size() % 10 != 0)
                upper = pageNum * 10 + (itemList.size() % 10) - 10;
            else
                upper = pageNum * 10;
            
            for(int i = lower; i < upper; i++) {
                System.out.println(rowString(i + 1, itemList.get(i)));
            }
        }
        if(pageNum == lastPage && itemList.size() % 10 != 0)
            System.out.println("\n".repeat((10 - itemList.size() % 10) * 2 - 1));
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
                if(basket.getBasketTable().isEmpty()) {
                    System.out.println("Basket is empty");
                    Util.pause(input);
                    break;
                }
                if(customer.getPaymentCard() == null || customer.getShippingAddress() == null) {
                    eshop.setActiveMenu(new PaymentForm(eshop, this));
                    break;
                }
                boolean canPurchase = true;
                for(BasketItem item : itemList) {
                    if(!item.isAvailable()) {
                        System.out.println("One or more items is no longer available");
                        Util.pause(input);
                        canPurchase = false;
                        break;
                    }
                }
                if(!canPurchase)
                    break;
                for(BasketItem item : itemList) {
                    Product product = catalog.get(item.getProductId());
                    int amountToBuy = item.getAmountToBuy();
                    int quantity = product.getQuantity();
                    int newQuantity = quantity - amountToBuy;
                    if(item.getAmountToBuy() == product.getQuantity())
                        catalog.delete(product.getProductId());
                    else
                        product.setQuantity(newQuantity);
                    basket.remove(item.getProductId());
                }
                System.out.println("Purchase successful");
                Util.pause(input);
                break;
            case 2:
                System.out.print("Enter product ID or listing num.: ");
                String selection1 = input.nextLine().trim();
                if(!Util.isLong(selection1)) {
                    System.out.println("Selection num. must be a positive integer");
                    Util.pause(input);
                    break;
                }
                if(getProductFromSelection(selection1) == null) {
                    System.out.println("Product not found");
                    Util.pause(input);
                    break;
                }
                Product product1 = getProductFromSelection(selection1);
                eshop.setActiveMenu(new ProductViewer(eshop, product1, this));
                break;
            case 3:
                System.out.print("Enter product ID or listing num.: ");
                String selection2 = input.nextLine().trim();
                if(!Util.isLong(selection2)) {
                    System.out.println("Selection num. must be a positive integer");
                    Util.pause(input);
                    break;
                }
                if(getBasketItemFromSelection(selection2) == null) {
                    System.out.println("Product not found");
                    Util.pause(input);
                    break;
                }
                System.out.print("Enter new amount: ");
                String newAmount = input.nextLine().trim();
                if(!Util.isInteger(newAmount)) {
                    System.out.println("Amount to buy must be a positive integer");
                    Util.pause(input);
                    break;
                }
                getBasketItemFromSelection(selection2).setAmountToBuy(Integer.parseInt(newAmount));
                break;
                
            case 4:
                System.out.print("Enter product ID or listing num.: ");
                String selection3 = input.nextLine().trim();
                if(!Util.isLong(selection3)) {
                    System.out.println("Selection num. must be a positive integer");
                    Util.pause(input);
                    break;
                }
                if(getBasketItemFromSelection(selection3) == null) {
                    System.out.println("Product not found");
                    Util.pause(input);
                    break;
                }
                basket.remove(getBasketItemFromSelection(selection3).getProductId());
                System.out.println("Product removed from basket");
                Util.pause(input);
                break;
            case 5:
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
            case 6:
                if(pageNum > 1)
                    pageNum--;
                break;
            case 7:
                if(pageNum < lastPage)
                    pageNum++;
                break;
            case 8:
                if(returnMenu instanceof ProductBrowser productBrowser)
                    productBrowser.setProductList(catalog.getList());
                eshop.setActiveMenu(returnMenu);
                break;
            default:
                System.out.println("That is not an option");
                Util.pause(input);
        }
    }
    
    
    
    private void updateOptions() {
        setOptions("Purchase", "Select product", "Set amount to purchase", "Remove from cart", "Select page", "Previous page", "Next page", "Back");
        itemList = basket.getList();
        if(!itemList.isEmpty())
            lastPage = (int)Math.ceil(itemList.size() / 10.0);
        else
            lastPage = 1;
    }
    private String rowString(int rowNum, BasketItem item) {
        Product product = item.getProduct();
        String brand = Util.cut(product.getBrand(), 15);
        String name = Util.cut(product.getName(), 15);
        String description = Util.cut(product.getDescription(), 35);
        String rowNumStr = Integer.toString(rowNum) + ". ";
        String row = String.format("%-8s%-15s%-20s%-20s%-20s%-40s%-15.2f%-15d%d\n", 
                rowNumStr, product.getProductId(), product.getCategory(), 
                brand, name, description, product.getPrice(), item.getAmountToBuy(),
                product.getQuantity());
        return row;
    }
    
    private Product getProductFromSelection(String selection) {
        long selectionNum = Long.parseLong(selection);
        if(selection.length() == 10 && basket.contains(selection)) {
            return basket.getProduct(selection);
        }
        else if(selectionNum <= itemList.size()){
            return itemList.get((int)selectionNum - 1).getProduct();
        }
        else {
            return null;
        }
    }
    
    private BasketItem getBasketItemFromSelection(String selection) {
        long selectionNum = Long.parseLong(selection);
        if(selection.length() == 10 && basket.contains(selection)) {
            return basket.get(selection);
        }
        else if(selectionNum <= itemList.size()){
            return itemList.get((int)selectionNum - 1);
        }
        else {
            return null;
        }
    }
}
