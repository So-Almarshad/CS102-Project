/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.editor;

import eshop.launcher.AdminProductMenu;
import eshop.launcher.Eshop;
import eshop.launcher.Menu;
import eshop.util.Util;
import eshop.products.*;
/**
 *
 * @author abdul
 */
public class ProductEditor extends Menu {
    
    private final String CATEGORY_OPTIONS = "1. Cloth\n"
                                          + "2. Computer\n"
                                          + "3. Paper book\n"
                                          + "4. E-book\n"
                                          + "5. None\n"
                                          + "6. Back";
    
    protected final int GENERAL_OPTION_COUNT = 6;
    protected final int STATIC_OPTION_COUNT = 3;
    protected final int CLOTH_OPTION_COUNT = 3;
    protected final int COMPUTER_OPTION_COUNT = 3;
    protected final int PAPER_BOOK_OPTION_COUNT = 8;
    protected final int EBOOK_OPTION_COUNT = 7;
    
    protected String[] extraOptions;
    protected ProductSpec spec;
    protected Catalog catalog;
    protected boolean updating;
    
    //Constructer for a completely new product editor to add products
    public ProductEditor(Eshop eshop) {
        this(eshop, new ProductSpec());
    }
    
    //Constructer for a product editor to add products, where product
    //specifications have already been defined
    public ProductEditor(Eshop eshop, ProductSpec spec) {
        this(eshop, spec, false);
    }
    
    //Constructor for a product editor that specifies whether it is updating an 
    //existing product or adding a new one
    public ProductEditor(Eshop eshop, ProductSpec spec, boolean updating) {
        super(eshop, 
                (updating ? "UPDATE PRODUCT: " + spec.getProductId() : "ADD PRODUCT"), 
                "Product Type: ", "Brand: ", "Name: ", "Description: ", "Price: ", 
                (updating ? "Quantity: " : "Amount to add: "), 
                (updating ? "Update product" : "Add product"),
                "Back");
        this.spec = spec;
        this.updating = updating;
        catalog = eshop.getCatalog();
        extraOptions = new String[0];
        updateOptions();
    }

    //Display method that inserts category specific options between "Name" and
    //"Description"
    @Override
    public void display() {
        StringBuilder optionsBuilder = new StringBuilder();
        int i;
        int j;
        int k;
        for(i = 0; i < 3; i++) {
            String option = (i + 1) + ". " + getOptions()[i] + '\n';
            optionsBuilder.append(option);
        }
        for(j = 0; j < extraOptions.length; j++) {
            String option = (i + j + 1) + ". " + extraOptions[j] + '\n';
            optionsBuilder.append(option);
        }
        for(k = i; k < getOptions().length; k++) {
            String option = (k + j + 1) + ". " + getOptions()[k] + '\n';
            optionsBuilder.append(option);
        }
        System.out.println(getHeader() + optionsBuilder);
    }
    
    @Override
    public void select(int optionNum) {
        selectGeneralOptions(optionNum);
        updateOptions();
        updateExtraOptions();
    }
    
    //Selects from the first three general options, and defaults to the general
    //options whose positions change depending on the product's category
    protected void selectGeneralOptions(int optionNum) {
        switch(optionNum) {
            case 1:
                System.out.println();
                System.out.println(CATEGORY_OPTIONS);
                System.out.println();
                System.out.print("Select category: ");
                selectCategory();
                break;
            case 2:
                System.out.print("Enter brand: ");
                spec.setBrand(input.nextLine());
                break;
            case 3:
                System.out.print("Enter name: ");
                spec.setName(input.nextLine());
                break;
            default:
                selectDynamicGeneralOptions(optionNum);
                break;
        }
        updateOptions();
    }
    
    //Selects from the last five options, taking into account that category 
    //specific options will change their positions
    protected void selectDynamicGeneralOptions(int optionNum) {
        switch(optionNum - STATIC_OPTION_COUNT - extraOptions.length) {
            case 1:
                System.out.print("Enter description: ");
                spec.setDescription(input.nextLine());
                break;
            case 2:
                System.out.print("Enter price: ");
                String temp = input.nextLine().trim();
                if(Util.isDecimal(temp)) {
                    spec.setPriceStr(temp);
                    spec.setPrice(Double.parseDouble(temp));
                }
                else {
                    System.out.println("Price should be a positive decimal");
                    Util.pause(input);
                }
                break;
            case 3:
                System.out.print("Enter quantity: ");
                String tempQuantity = input.nextLine().trim();
                if(Util.isInteger(tempQuantity)) {
                    spec.setQuantityStr(tempQuantity);
                    spec.setQuantity(Integer.parseInt(tempQuantity));
                }
                else {
                    System.out.println("Quantity should be a positive integer");
                    Util.pause(input);
                }
                break;
            case 4: 
                if(hasMissingFields()) {
                    System.out.println("Some fields are missing"); 
                    Util.pause(input); 
                }
                else if(updating) {
                    updateProduct();
                    System.out.println("Product successfully updated");
                    Util.pause(input);
                }
                else {
                    spec.setProductId(generateId());
                    addProduct();
                    System.out.println("Product successfully added: " + spec.getProductId());
                    eshop.setActiveMenu(new ProductEditor(eshop));
                    Util.pause(input);
                }
                break;
            case 5: 
                eshop.setActiveMenu(new AdminProductMenu(eshop)); 
                break;
            default:
                selectTypeSpecificOptions(optionNum - 3);
        }
    }
    
    //Selects category specific options. Overriden by specific product
    //editors to factor for the additional options. Does nothing if no category
    //is specified.
    protected void selectTypeSpecificOptions(int optionNum) {
        System.out.println("That is not an option");
        Util.pause(input);
    }
    
    //Selects the product category and switches to the appropriate editor
    protected void selectCategory() {
        boolean stillChoosing = true;
        while(stillChoosing) {
            stillChoosing = false;
            String inputStr = input.nextLine().trim();
            
            if(!Util.isInteger(inputStr)) {
                stillChoosing = true;
                System.out.println("Option num. should be a positive integer");
                Util.pause(input);
                continue;
            }
            
            switch(Integer.parseInt(inputStr)) {
                case 1: 
                    spec.setCategory(Product.CLOTH);
                    eshop.setActiveMenu(new ClothEditor(eshop, spec, updating));
                    break;
                case 2: 
                    spec.setCategory(Product.COMPUTER);
                    eshop.setActiveMenu(new ComputerEditor(eshop, spec, updating));
                    break;
                case 3: 
                    spec.setCategory(Product.PAPER_BOOK);
                    eshop.setActiveMenu(new PaperBookEditor(eshop, spec, updating));
                    break;
                case 4: 
                    spec.setCategory(Product.EBOOK); 
                    eshop.setActiveMenu(new PaperBookEditor(eshop, spec, updating));
                    break;
                case 5: 
                    if(!(spec.getCategory().equals(""))) {
                        spec.setCategory("");
                        eshop.setActiveMenu(new ProductEditor(eshop, spec, updating));
                    }
                    break;
                case 6: 
                    break;
                default:
                    stillChoosing = true;
                    System.out.println("That is not an option");
                    Util.pause(input);
            }
        }
        ((ProductEditor)eshop.getActiveMenu()).updateExtraOptions();
    }
    
    //Adds a product to the catalog. overriden by specific editors to account
    //for category specific fields.
    protected void addProduct() {
        throw new RuntimeException("ERROR: Cannot add product without filling all fields");
    }
    
    //Updates a product to the catalog. overriden by specific editors to account
    //for category specific fields. Does not change the products ID.
    protected void updateProduct() {
        throw new RuntimeException("ERROR: Cannot update product without filling all fields");
    }
    
    //Updates the options to show the user's entries
    protected final void updateOptions() {
        setOptions("Category: " + spec.getCategory(), "Brand: " + spec.getBrand(), 
            "Name: " + spec.getName(), "Description: " + spec.getDescription(), 
            "Price: " + spec.getPriceStr(), 
            (updating ? "Quantity: " : "Amount to add: ") + spec.getQuantityStr(), 
            (updating ? "Update product" : "Add product"),
            "Back");
    }
    
    //Updates the category specific options
    protected void updateExtraOptions() {}
    
    protected boolean hasMissingFields() {
        boolean missingCategory = spec.getCategory().equals("");
        boolean missingBrand = spec.getBrand().equals("");
        boolean missingName = spec.getName().equals("");
        boolean missingDescription = spec.getDescription().equals("");
        boolean missingPrice = spec.getPrice() == -1;
        boolean missingQuantity = spec.getQuantity() == -1;
        
        return missingCategory || missingBrand || missingName || 
                missingDescription || missingPrice || missingQuantity;
    }
    
    protected void setExtraOptions(String... extraOptions) {
        this.extraOptions = extraOptions;
    }
    
    private String generateId() {
        String id = "";
        do {
            long idNum = (long)(1000000000L + Math.random() * 9000000000L);
            id = ((Long)idNum).toString();
        } while(catalog.contains(id));
        return id;
    }
    
    public ProductSpec getSpec() {
        return spec;
    }

    public void setSpec(ProductSpec spec) {
        this.spec = spec;
    }
    
    public void setUpdating(boolean updating) {
        this.updating = updating;
    }
}
