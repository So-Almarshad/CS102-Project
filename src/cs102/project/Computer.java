/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs102.project;

/**
 *
 * @author Saud
 */
public class Computer extends Product {
    private int memorySize;
    private int processorSpeed;
    private int hardDiskSize;

    public Computer(int memorySize, int processorSpeed, int hardDiskSize, String productId, String brand, String description, double price, int quantity) {
        super(productId, brand, description, price, quantity);
        if (memorySize>0)
            this.memorySize = memorySize;
        else throw new IllegalArgumentException("Memory Size should be strictly positive");
        
        if (processorSpeed>0)
            this.processorSpeed = processorSpeed;
        else throw new IllegalArgumentException("Processor speed should be stictly positive");
        
        if (hardDiskSize>0)
           this.hardDiskSize = hardDiskSize;
        else    throw new IllegalArgumentException("Hard disk Size should be strictly positive ");
    }
    
    
    /******GETTERS & SETTERS******/
    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        if (memorySize>0)
            this.memorySize = memorySize;
        else throw new IllegalArgumentException("Memory Size should be strictly positive");
    }

    public int getProcessorSpeed() {
        return processorSpeed;
    }

    public void setProcessorSpeed(int processorSpeed) {
        if (processorSpeed>0)
            this.processorSpeed = processorSpeed;
        else throw new IllegalArgumentException("Processor speed should be stictly positive");
    }

    public int getHardDiskSize() {
        return hardDiskSize;
    }

    public void setHardDiskSize(int hardDiskSize) {
       if (hardDiskSize>0)
           this.hardDiskSize = hardDiskSize;
        else    throw new IllegalArgumentException("Hard disk Size should be strictly positive ");
    }
    
    /******METHODS******/
    @Override
    public int compareTo(Product p){
        //compare two computers by memorySize
        if (p==null) throw new IllegalArgumentException("Cannot compare null");
        Computer c=(Computer)p;
        return (int)(this.getMemorySize()-c.getMemorySize())*10000;
    }
    
}
