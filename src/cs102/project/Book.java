/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs102.project;

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

    public Book(String title, String author, String publisher, String genre, String isbn, int numberOfPages, String productId, String brand, String description, double price, int quantity) {
        super(productId, brand, description, price, quantity);
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        if(isbn.length()==13)
            this.isbn = isbn;
        else    throw new IllegalArgumentException("ISBN length should be 13");
        if (numberOfPages>0)
            this.numberOfPages = numberOfPages;
        else throw new IllegalArgumentException("Number of pages should be strictly positive");
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
        if(isbn.length()==13)
            this.isbn = isbn;
        else    throw new IllegalArgumentException("ISBN length should be 13");
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        if (numberOfPages>0)
            this.numberOfPages = numberOfPages;
        else throw new IllegalArgumentException("Number of pages should be strictly positive");
    }
    
    /******METHODS******/
    @Override
    public int compareTo(Product p){
        //compare to books by title alphabetically
        if (p==null) 
            throw new IllegalArgumentException("Cannot compare null");
        
        Book b=(Book)p;
        int length=0;
        if (b.getTitle().length()>this.getTitle().length()) 
            length=this.getTitle().length();
        else length=b.getTitle().length();
        for (int i = 0; i < length; i++) {
            if (Character.toLowerCase(this.getTitle().charAt(i))-Character.toLowerCase(b.getTitle().charAt(i))!=0) {
                return Character.toLowerCase(this.getTitle().charAt(i))-Character.toLowerCase(b.getTitle().charAt(i));
            }
        }
        if (this.getTitle().length()<b.getTitle().length()) {
            return 1;
        }
        else if (this.getTitle().length()>b.getTitle().length()) {
            return -1;
        }
        return 0;
    }
    
}
