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
    private double processorSpeed;
    private long hardDiskSize;

    public Computer(Catalog catalog, String brand, String name, 
            String description, double price, int quantity, long memorySize, 
            double processorSpeed, long hardDiskSize) {
        super(catalog, COMPUTER, brand, name, description, price, quantity);
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

    public double getProcessorSpeed() {
        return processorSpeed;
    }

    public void setProcessorSpeed(double processorSpeed) {
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
    protected String getMiddleString() {
        return "Memory size: " + memorySize + '\n'
             + "Processor speed: " + processorSpeed + '\n'
             + "Hard disk size: " + hardDiskSize + '\n';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Computer other = (Computer) obj;
        if (this.memorySize != other.memorySize) {
            return false;
        }
        if (this.processorSpeed != other.processorSpeed) {
            return false;
        }
        return super.equals(obj) && this.hardDiskSize == other.hardDiskSize;
    }
    
    
}
