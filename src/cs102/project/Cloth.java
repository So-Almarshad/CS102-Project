/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CS102.Project;

/**
 *
 * @author Saud
 */
public class Cloth extends Product{
    private int size;
    private String color;
    private String type;

    public Cloth(int size, String color, String type, String productId, 
            String brand, String name, String description, double price, 
            int quantity) {
        super(productId, brand, name, description, price, quantity);
        if (size > 0) 
            this.size = size;
        else 
            throw new IllegalArgumentException("Size should be positive");
        
        this.color = color;
        
        if (type.equals("Men") || type.equals("Women") || type.equals("Children"))
           this.type = type;
        else 
            throw new IllegalArgumentException("Type should be \"Men\",\"Women\",\"Children\"");
    }
    
    /******GETTERS AND SETTERS******/
    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        if (size>0) 
            this.size = size;
        else
            throw new IllegalArgumentException("Size should be positive");
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        if (type.equals("Men")|| type.equals("Women")|| type.equals("Children"))
           this.type = type;
        else 
           throw new IllegalArgumentException("Type should be \"Men\",\"Women\",\"Children\"");
    }
}
