/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.products;
import eshop.util.Util;
import java.util.Objects;
/**
 *
 * @author Saud
 */
public class Book extends Product{
    private String title;
    private String author;
    private String publisher;
    private String genre;
    private String isbn;
    private int numberOfPages;

    public Book(Catalog catalog, String CATEGORY, 
            String brand, String name, String description, double price,
            int quantity, 
            String title, String author, String publisher, 
            String genre, String isbn, int numberOfPages) {
        super(catalog, CATEGORY, brand, name, description, price, quantity);
        
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        
        if(isbn.length() == 13 && Util.isLong(isbn))
            this.isbn = isbn;
        else 
            throw new IllegalArgumentException("ISBN should be 13 digits");
        
        if (numberOfPages > 0)
            this.numberOfPages = numberOfPages;
        else 
            throw new IllegalArgumentException("Number of pages should be "
                    + "strictly positive");
    }
    
    /******GETTERS & SETTERS******/
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if(isbn.length() == 13)
            this.isbn = isbn;
        else    throw new IllegalArgumentException("ISBN length should be 13");
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        if (numberOfPages>0)
            this.numberOfPages = numberOfPages;
        else throw new IllegalArgumentException("Number of pages should be "
                + "strictly positive");
    }
    
    /******METHODS******/
    @Override
    protected String getMiddleString() {
        return "Title: " + title + '\n'
             + "Author: " + author + '\n'
             + "Publisher: " + publisher + '\n'
             + "Genre: " + genre + '\n'
             + "ISBN: " + isbn + '\n'
             + "Number of pages: " + numberOfPages + '\n';
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
        final Book other = (Book) obj;
        if (this.numberOfPages != other.numberOfPages) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.publisher, other.publisher)) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        return super.equals(obj) && Objects.equals(this.isbn, other.isbn);
    }
    
    
    
}
