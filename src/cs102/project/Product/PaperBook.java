/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package cs102.project.Product;

/**
 *
 * @author SAUD
 */
public class PaperBook {

    private double weight;
    private String typeOfCover;

    public PaperBook(double weight, String typeOfCover) {
        this.weight = weight;
        this.typeOfCover = typeOfCover;
    }
    
    
    /******GETTERS & SETTERS******/
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getTypeOfCover() {
        return typeOfCover;
    }

    public void setTypeOfCover(String typeOfCover) {
        this.typeOfCover = typeOfCover;
    }
    
    
}
