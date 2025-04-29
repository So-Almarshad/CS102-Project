/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.products;

/**
 *
 * @author Saud
 */
public class Computer extends Product {
    private long memorySize;
    private long processorSpeed;
    private long hardDiskSize;

    public Computer(Catalog catalog, String productId, String brand, String name, 
            String description, double price, int quantity, long memorySize, 
            long processorSpeed, long hardDiskSize) {
        super(catalog, productId, COMPUTER, brand, name, description, price, quantity);
        if (memorySize > 0)
            this.memorySize = memorySize;
        else throw new IllegalArgumentException("Memory Size should be strictly positive");
        
        if (processorSpeed > 0)
            this.processorSpeed = processorSpeed;
        else throw new IllegalArgumentException("Processor speed should be stictly positive");
        
        if (hardDiskSize > 0)
           this.hardDiskSize = hardDiskSize;
        else    throw new IllegalArgumentException("Hard disk Size should be strictly positive ");
    }
    
    
    /******GETTERS & SETTERS******/
    public long getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(long memorySize) {
        if (memorySize > 0)
            this.memorySize = memorySize;
        else throw new IllegalArgumentException("Memory Size should be strictly positive");
    }

    public long getProcessorSpeed() {
        return processorSpeed;
    }

    public void setProcessorSpeed(long processorSpeed) {
        if (processorSpeed > 0)
            this.processorSpeed = processorSpeed;
        else throw new IllegalArgumentException("Processor speed should be stictly positive");
    }

    public long getHardDiskSize() {
        return hardDiskSize;
    }

    public void setHardDiskSize(long hardDiskSize) {
       if (hardDiskSize>0)
           this.hardDiskSize = hardDiskSize;
        else throw new IllegalArgumentException("Hard disk Size should be strictly positive");
    }
    
    /******METHODS******/
    @Override
    public int compareTo(Product p){
        //compare two computers by memorySize
        if (p == null) throw new IllegalArgumentException("Cannot compare null");
        Computer c = (Computer)p;
        return (int)(this.getMemorySize()-c.getMemorySize())*10000;
    }
}
