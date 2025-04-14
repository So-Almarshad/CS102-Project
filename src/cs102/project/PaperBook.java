/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package CS102.Project;

/**
 *
 * @author SAUD
 */
public class PaperBook extends Book{

    private double weight;
    private String typeOfCover;
    private final static String HARD_COVER = "hard-cover";
    private final static String SOFT_COVER = "soft-cover";

    public PaperBook(String brand, String name, String description, 
            double price, int quantity, String title, String author, 
            String publisher, String genre, String isbn, int numberOfPages, 
            double weight, String typeOfCover) {
        super("Paper Book", brand, name, description, price, quantity, title, 
                author, publisher, genre, isbn, numberOfPages);
        
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
}
