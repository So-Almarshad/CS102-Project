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
    private long size;

    public EBook(Catalog catalog, String productId, String brand, String name, String description, 
            double price, int quantity, String title, String author, String publisher, 
            String genre, String isbn, int numberOfPages, long size) {
        super(catalog, productId, EBOOK, brand, name, description, price, quantity, 
                title, author, publisher, genre, isbn, numberOfPages);
        if(size > 0)
            this.size = size;
        else
            throw new IllegalArgumentException("Size should be strictly positive");
    }

    /******GETTERS AND SETTERS******/
    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        if(size > 0)
            this.size = size;
        else
            throw new IllegalArgumentException("Size should be strictly positive");
    }
   
}
