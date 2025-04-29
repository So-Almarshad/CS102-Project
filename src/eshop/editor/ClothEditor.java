/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.editor;

import eshop.launcher.Eshop;
import eshop.util.Util;
import eshop.products.Cloth;

/**
 *
 * @author abdul
 */
public final class ClothEditor extends ProductEditor {
    public ClothEditor(Eshop eshop, ProductSpec spec, boolean updating) {
        super(eshop, spec, updating);
        extraOptions = new String[CLOTH_OPTION_COUNT];
        updateExtraOptions();
    }

    @Override
    protected void selectTypeSpecificOptions(int optionNum) {
        switch(optionNum) {
            case 1:
                System.out.print("Enter size: ");
                String temp = input.nextLine().trim();
                if(Util.isInteger(temp)) {
                    spec.setClothSizeStr(temp);
                    spec.setClothSize(Integer.parseInt(temp));
                }
                else {
                    System.out.println("Size should be a positive integer");
                    Util.pause(input);
                }
                break;
            case 2:
                System.out.print("Enter color: ");
                spec.setColor(input.nextLine());
                break;
            case 3:
                System.out.print("Enter type (M, W, C): ");
                String typeStr = input.nextLine().trim().toUpperCase();
                switch(typeStr) {
                    case "M": spec.setClothType("Men"); break;
                    case "W": spec.setClothType("Women"); break;
                    case "C": spec.setClothType("Children"); break;
                    default:
                        System.out.println("Type should be \"Men\",\"Women\",\"Children\"");
                        Util.pause(input);
                }
                break;
            default:
                System.out.println("That is not an option");
                Util.pause(input);
                break;
        }
    }
    
    @Override
    protected void addProduct() {
        Cloth cloth = new Cloth(catalog, spec.getProductId(), 
                spec.getBrand(), spec.getName(), spec.getDescription(), 
                spec.getPrice(), spec.getQuantity(), spec.getClothSize(), 
                spec.getColor(), spec.getClothType());
        catalog.add(cloth, spec.getQuantity());
    }
    
    @Override
    protected void updateProduct() {
        Cloth cloth = new Cloth(catalog, spec.getProductId(), 
                spec.getBrand(), spec.getName(), spec.getDescription(), 
                spec.getPrice(), spec.getQuantity(), spec.getClothSize(), 
                spec.getColor(), spec.getClothType());
        catalog.update(cloth.getProductId(), cloth);
    }

    @Override
    protected void updateExtraOptions() {
        setExtraOptions("Size: " + spec.getClothSizeStr(), 
                        "Color: " + spec.getColor(), 
                        "Type: " + spec.getClothType());
    }
    
    @Override
    protected boolean hasMissingFields() {
        boolean missingGeneral = super.hasMissingFields();
        boolean missingSize = spec.getClothSize() == -1;
        boolean missingColor = spec.getColor().equals("");
        boolean missingType = spec.getClothType().equals("");
        
        return missingGeneral || missingSize || missingColor || missingType;
    }
}
