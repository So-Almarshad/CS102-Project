/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.editor;

import eshop.launcher.Eshop;
import eshop.products.Computer;
import eshop.util.Util;

/**
 *
 * @author abdul
 */
public final class ComputerEditor extends ProductEditor {
    public ComputerEditor(Eshop eshop, ProductSpec spec, boolean updating) {
        super(eshop, spec, updating);
        extraOptions = new String[COMPUTER_OPTION_COUNT];
    }
    @Override
    protected void selectTypeSpecificOptions(int optionNum) {
        switch(optionNum) {
            case 1:
                System.out.print("Enter memory size: ");
                String memoryTemp = input.nextLine().trim();
                if(Util.isLong(memoryTemp)) {
                    spec.setMemorySizeStr(memoryTemp);
                    spec.setMemorySize(Integer.parseInt(memoryTemp));
                }
                else {
                    System.out.println("Memory size should be a positive integer");
                    Util.pause(input);
                }
                break;
            case 2:
                System.out.print("Enter processor speed: ");
                String processorTemp = input.nextLine().trim();
                if(Util.isLong(processorTemp)) {
                    spec.setProcessorSpeedStr(processorTemp);
                    spec.setProcessorSpeed(Integer.parseInt(processorTemp));
                }
                else {
                    System.out.println("Processor speed should be a positive integer");
                    Util.pause(input);
                }
                break;
            case 3:
                System.out.print("Enter hard disk size: ");
                String hddTemp = input.nextLine().trim();
                if(Util.isLong(hddTemp)) {
                    spec.setHardDiskSizeStr(hddTemp);
                    spec.setHardDiskSize(Integer.parseInt(hddTemp));
                }
                else {
                    System.out.println("Processor speed should be a positive integer");
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
        Computer computer = new Computer(catalog, spec.getProductId(), 
                spec.getBrand(), spec.getName(), spec.getDescription(), 
                spec.getPrice(), spec.getQuantity(), spec.getMemorySize(), 
                spec.getProcessorSpeed(), spec.getHardDiskSize());
        catalog.add(computer);
    }
    
    @Override
    protected void updateProduct() {
        Computer computer = new Computer(catalog, spec.getProductId(), 
                spec.getBrand(), spec.getName(), spec.getDescription(), 
                spec.getPrice(), spec.getQuantity(), spec.getMemorySize(), 
                spec.getProcessorSpeed(), spec.getHardDiskSize());
        catalog.update(computer.getProductId(), computer);
    }

    @Override
    protected void updateExtraOptions() {
        setExtraOptions("Memory size (kB): " + spec.getMemorySizeStr(), 
                        "Processor speed (GHz): " + spec.getProcessorSpeedStr(), 
                        "Hard disk size (kB): " + spec.getHardDiskSizeStr());
    }
    
    @Override
    protected boolean hasMissingFields() {
        boolean missingGeneral = super.hasMissingFields();
        boolean missingMemorySize = spec.getMemorySize() == -1;
        boolean missingProcessorSpeed = spec.getProcessorSpeed() == -1;
        boolean missingHardDiskSize = spec.getHardDiskSize() == -1;
        
        return missingGeneral || missingMemorySize || missingProcessorSpeed || missingHardDiskSize;
    }
    
}
