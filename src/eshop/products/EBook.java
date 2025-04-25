/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.products;

/**
 *
 * @author SAUD
 */
public class EBook extends Book{
    private int size;

    public EBook(Catalog catalog, String brand, String name, String description, double price, 
            int quantity, int size, String title, String author, 
            String publisher,String genre, String isbn, int numberOfPages) {
        super(catalog, "EBook", brand, name, description, price, quantity, title, 
                author, publisher, genre, isbn, numberOfPages);
        if(size > 0)
            this.size = size;
        else
            throw new IllegalArgumentException("Size should be strictly positive");
    }

    /******GETTERS AND SETTERS******/
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if(size > 0)
            this.size = size;
        else
            throw new IllegalArgumentException("Size should be strictly positive");
    }
   
}
