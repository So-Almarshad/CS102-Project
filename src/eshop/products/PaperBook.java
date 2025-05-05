/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package eshop.products;

import java.util.Objects;

/**
 *
 * @author SAUD
 */
public class PaperBook extends Book{

    protected double weight;
    protected String typeOfCover;
    public final static String HARD_COVER = "hard-cover";
    public final static String SOFT_COVER = "soft-cover";

    public PaperBook(Catalog catalog, String brand, String name, 
            String description, double price, int quantity, String title, String author, 
            String publisher, String genre, String isbn, int numberOfPages, 
            double weight, String typeOfCover) {
        super(catalog, PAPER_BOOK, brand, name, description, price, 
                quantity, title, author, publisher, genre, isbn, numberOfPages);
        
        if(weight > 0)
            this.weight = weight;
        else
            throw new IllegalArgumentException("Weight should be strictly "
                    + "positive");
        
        if(typeOfCover.equals(HARD_COVER) || typeOfCover.equals(SOFT_COVER))
            this.typeOfCover = typeOfCover;
        else 
            throw new IllegalArgumentException("Type of cover should be "
                    + "hard-cover or soft-cover");
    }
    
    /******GETTERS & SETTERS******/
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if(weight > 0)
            this.weight = weight;
        else
            throw new IllegalArgumentException("Weight should be strictly "
                    + "positive");
    }

    public String getTypeOfCover() {
        return typeOfCover;
    }

    public void setTypeOfCover(String typeOfCover) {
        if(typeOfCover.equals(HARD_COVER) || typeOfCover.equals(SOFT_COVER))
            this.typeOfCover = typeOfCover;
        else 
            throw new IllegalArgumentException("Type of cover must be "
                    + "hard-cover or soft-cover");
    }
    
    @Override
    protected String getMiddleString() {
        return super.getMiddleString()
             + "Weight: " + weight + '\n'
             + "Type of cover: " + typeOfCover + '\n';
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
        final PaperBook other = (PaperBook) obj;
        if (Double.doubleToLongBits(this.weight) != Double.doubleToLongBits(other.weight)) {
            return false;
        }
        return super.equals(obj) && Objects.equals(this.typeOfCover, other.typeOfCover);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(category, productId, brand, name, description, 
                price, quantity, title, author, publisher, genre, isbn, 
                numberOfPages, weight, typeOfCover);
    }
}
