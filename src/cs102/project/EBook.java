/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package CS102.Project;

/**
 *
 * @author SAUD
 */
public class EBook extends Book{
    private int size;

    public EBook(int size, String title, String author, String publisher, String genre, String isbn, int numberOfPages, String productId, String brand, String description, double price, int quantity) {
        super(title, author, publisher, genre, isbn, numberOfPages, productId, brand, description, price, quantity);
        this.size = size;
    }

    /******GETTERS AND SETTERS******/
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
   
}
