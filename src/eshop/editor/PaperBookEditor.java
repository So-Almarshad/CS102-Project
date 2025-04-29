/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.editor;

import eshop.launcher.Eshop;
import eshop.products.PaperBook;
import eshop.util.Util;

/**
 *
 * @author abdul
 */
public final class PaperBookEditor extends ProductEditor {
    public PaperBookEditor(Eshop eshop, ProductSpec spec, boolean updating) {
        super(eshop, spec, updating);
        extraOptions = new String[PAPER_BOOK_OPTION_COUNT];
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
                if(tempIsbn.length() == 13 && Util.isInteger(tempIsbn)) {
                    spec.setGenre(input.nextLine());
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
                System.out.print("Enter weight: ");
                String tempWeight = input.nextLine().trim();
                if(Util.isDecimal(tempWeight)) {
                    spec.setWeightStr(tempWeight);
                    spec.setWeight(Integer.parseInt(tempWeight));
                }
                else {
                    System.out.println("Weight should be a positive decimal");
                    Util.pause(input);
                }
                break;
            case 8:
                System.out.print("Enter type of cover (H, S): ");
                String tempCover = input.nextLine().trim().toUpperCase();
                switch(tempCover) {
                    case "H": spec.setTypeOfCover(PaperBook.HARD_COVER); break;
                    case "S": spec.setTypeOfCover(PaperBook.SOFT_COVER); break;
                    default:
                        System.out.println("Type of cover should be hard-cover "
                                + "(enter \"H\") or soft-cover (enter \"S\")");
                        Util.pause(input);
                }
            default:
                System.out.println("That is not an option");
                Util.pause(input);
                break;
        }
    }
    
    @Override
    protected void addProduct() {
        PaperBook paperBook = new PaperBook(catalog, spec.getProductId(), 
                        spec.getBrand(), spec.getName(), spec.getDescription(), 
                        spec.getPrice(), spec.getQuantity(), spec.getTitle(), 
                        spec.getAuthor(), spec.getPublisher(), spec.getGenre(), 
                        spec.getIsbn(), spec.getNumberOfPages(), spec.getWeight(), 
                        spec.getTypeOfCover());
        catalog.add(paperBook);
    }
    
    @Override
    protected void updateProduct() {
        PaperBook paperBook = new PaperBook(catalog, spec.getProductId(), 
                        spec.getBrand(), spec.getName(), spec.getDescription(), 
                        spec.getPrice(), spec.getQuantity(), spec.getTitle(), 
                        spec.getAuthor(), spec.getPublisher(), spec.getGenre(), 
                        spec.getIsbn(), spec.getNumberOfPages(), spec.getWeight(), 
                        spec.getTypeOfCover());
        catalog.update(paperBook.getProductId(), paperBook);
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
        boolean missingWeight = spec.getWeight() == -1;
        boolean missingTypeOfCover = spec.getTypeOfCover().equals("");
        
        return missingGeneral || missingTitle || missingAuthor || missingPublisher ||
                missingGenre || missingIsbn || missingNumberOfPages || missingWeight ||
                missingTypeOfCover;
    }
    
}
