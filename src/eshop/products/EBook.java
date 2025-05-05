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
public class EBook extends Book{
    protected long size;

    public EBook(Catalog catalog, String brand, String name, String description, 
            double price, int quantity, String title, String author, String publisher, 
            String genre, String isbn, int numberOfPages, long size) {
        super(catalog, EBOOK, brand, name, description, price, quantity, 
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

    @Override
    protected String getMiddleString() {
        return super.getMiddleString()
             + "Size: " + size + '\n';
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
        final EBook other = (EBook) obj;
        return super.equals(obj) && this.size == other.size;
    }
   
    @Override
    public int hashCode() {
        return Objects.hash(category, productId, brand, name, description, 
                price, quantity, title, author, publisher, genre, isbn, 
                numberOfPages, size);
    }
}
