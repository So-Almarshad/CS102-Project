/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.util;

import eshop.products.Book;
import java.util.Comparator;

/**
 *
 * @author Saud
 */
public class AuthorComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2){
        //compare two books by author alphabetically
        if (b1==null || b2==null) throw new IllegalArgumentException("Cannot compare null");
        int length=0;
        if (b1.getAuthor().length()>b2.getAuthor().length()) 
            length=b2.getAuthor().length();
        else length=b1.getAuthor().length();
        for (int i = 0; i < length; i++) {
            if (Character.toLowerCase(b1.getAuthor().charAt(i))-Character.toLowerCase(b2.getAuthor().charAt(i))!=0) {
                return Character.toLowerCase(b1.getAuthor().charAt(i))-Character.toLowerCase(b2.getAuthor().charAt(i));
            }
            
        }
        if (b1.getAuthor().length()<b2.getAuthor().length()) {
            return 1;
        }
        else if (b1.getAuthor().length()>b2.getAuthor().length()) {
            return -1;
        }
        return 0;
        //abc  abcd
        //test
    }
}
