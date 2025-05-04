/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.editor;

import eshop.launcher.Eshop;
import eshop.launcher.Menu;
import eshop.products.EBook;
import eshop.util.Util;

/**
 *
 * @author abdul
 */
public class EBookEditor extends ProductEditor {
    public EBookEditor(Eshop eshop, ProductSpec spec, Menu returnMenu, boolean updating) {
        super(eshop, spec, returnMenu, updating);
        extraOptions = new String[EBOOK_OPTION_COUNT];
        updateExtraOptions();
    }

    @Override
    protected void selectTypeSpecificOptions(int optionNum) {
        switch(optionNum) {
            case 1:
                System.out.print("Enter title: ");
                spec.setTitle(input.nextLine());
                break;
            case 2:
                System.out.print("Enter author: ");
                spec.setAuthor(input.nextLine());
                break;
            case 3:
                System.out.print("Enter publisher: ");
                spec.setPublisher(input.nextLine());
                break;
            case 4:
                System.out.print("Enter genre: ");
                spec.setGenre(input.nextLine());
                break;
            case 5:
                System.out.print("Enter ISBN: ");
                String tempIsbn = input.nextLine().trim();
                if(tempIsbn.length() == 13 && Util.isLong(tempIsbn)) {
                    spec.setIsbn(tempIsbn);
                }
                else {
                    System.out.println("ISBN should be 13 digits");
                    Util.pause(input);
                }
                break;
            case 6:
                System.out.print("Enter number of pages: ");
                String tempNumPages = input.nextLine().trim();
                if(Util.isInteger(tempNumPages)) {
                    spec.setNumberOfPagesStr(tempNumPages);
                    spec.setNumberOfPages(Integer.parseInt(tempNumPages));
                }
                else {
                    System.out.println("Number of pages should be a positive integer");
                    Util.pause(input);
                }
                break;
            case 7:
                System.out.print("Enter size (kB): ");
                String tempSize = input.nextLine().trim();
                if(Util.isLong(tempSize)) {
                    spec.setEbookSizeStr(tempSize);
                    spec.setEbookSize(Long.parseLong(tempSize));
                }
                else {
                    System.out.println("Size should be a positive integer");
                    Util.pause(input);
                }
                break;
            default:
                System.out.println("That is not an option");
                Util.pause(input);
                break;
        }
    }
    
    @Override
    protected void addProduct() {
        EBook eBook = new EBook(catalog, 
                            spec.getBrand(), spec.getName(), spec.getDescription(), 
                            spec.getPrice(), spec.getQuantity(), spec.getTitle(), 
                            spec.getAuthor(), spec.getPublisher(), 
                            spec.getGenre(), spec.getIsbn(), spec.getNumberOfPages(), 
                            spec.getEbookSize());
        catalog.add(eBook);
    }
    
    @Override
    protected void updateProduct() {
        EBook eBook = new EBook(catalog, 
                            spec.getBrand(), spec.getName(), spec.getDescription(), 
                            spec.getPrice(), spec.getQuantity(), spec.getTitle(), 
                            spec.getAuthor(), spec.getPublisher(), 
                            spec.getGenre(), spec.getIsbn(), spec.getNumberOfPages(), 
                            spec.getEbookSize());
        eBook.setProductId(spec.getProductId());
        product = eBook;
        catalog.update(eBook.getProductId(), eBook);
    }

    @Override
    protected void updateExtraOptions() {
        setExtraOptions("Title: " + spec.getTitle(), 
                        "Author: " + spec.getAuthor(), 
                        "Publisher: " + spec.getPublisher(), 
                        "Genre: " + spec.getGenre(), 
                        "ISBN: " + spec.getIsbn(), 
                        "Number of pages: " + spec.getNumberOfPagesStr(), 
                        "Weight: " + spec.getWeightStr(), 
                        "Type of cover: " + spec.getTypeOfCover());
    }
    
    @Override
    protected boolean hasMissingFields() {
        boolean missingGeneral = super.hasMissingFields();
        boolean missingTitle = spec.getTitle().equals("");
        boolean missingAuthor = spec.getAuthor().equals("");
        boolean missingPublisher = spec.getPublisher().equals("");
        boolean missingGenre = spec.getGenre().equals("");
        boolean missingIsbn = spec.getIsbn().equals("");
        boolean missingNumberOfPages = spec.getNumberOfPages() == -1;
        boolean missingSize = spec.getEbookSize() == -1;
        
        return missingGeneral || missingTitle || missingAuthor || missingPublisher ||
                missingGenre || missingIsbn || missingNumberOfPages || missingSize;
    }
    
}
