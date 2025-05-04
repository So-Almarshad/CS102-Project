/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.editor;

import eshop.products.*;

/**
 *
 * @author abdul
 */
public class ProductSpec {
    
    //General fields
    private String productId = "";
    private String category = "";
    private String brand = "";
    private String name = "";
    private String priceStr = "";
    private double price = -1;
    private String description = "";
    private String quantityStr = "";
    private int quantity = -1;
    
    //Cloth fields
    private String clothSizeStr = "";
    private int clothSize = -1;
    private String color = "";
    private String clothType = "";
    
    //Computer fields
    private String memorySizeStr = "";
    private long memorySize = -1;
    private String processorSpeedStr = "";
    private double processorSpeed = -1;
    private String hardDiskSizeStr = "";
    private long hardDiskSize = -1;
    
    //Book fields
    private String title = "";
    private String author = "";
    private String publisher = "";
    private String genre = "";
    private String isbn = "";
    private String numberOfPagesStr = "";
    private int numberOfPages = -1;
    
    //Paper book fields
    private String weightStr = "";
    private double weight = -1;
    private String typeOfCover = "";
    
    //E-book fields
    private String ebookSizeStr = "";
    private long ebookSize = -1;
    
    public ProductSpec() {}
    
    public ProductSpec(Product product) {
        initializeGeneralFields(product);
        if(product instanceof Cloth cloth) {
            clothSize = cloth.getSize();
            clothSizeStr = ((Integer)clothSize).toString();
            color = cloth.getColor();
            clothType = cloth.getType();
        }
        if(product instanceof Computer computer) {
            memorySize = computer.getMemorySize();
            memorySizeStr = ((Long)memorySize).toString();
            processorSpeed = computer.getProcessorSpeed();
            processorSpeedStr = ((Double)processorSpeed).toString();
            hardDiskSize = computer.getHardDiskSize();
            hardDiskSizeStr = ((Long)hardDiskSize).toString();
        }
        if(product instanceof Book book) {
            initializeBookFields(book);
            if(book instanceof PaperBook paperBook) {
                weight = paperBook.getWeight();
                weightStr = ((Double)weight).toString();
                typeOfCover = paperBook.getTypeOfCover();
            }
            if(book instanceof EBook eBook) {
                ebookSize = eBook.getSize();
                ebookSizeStr = ((Long)ebookSize).toString();
            }
        }
    }
    
    public ProductSpec(Cloth cloth) {
        initializeGeneralFields(cloth);
        clothSize = cloth.getSize();
        clothSizeStr = ((Integer)clothSize).toString();
        color = cloth.getColor();
        clothType = cloth.getType();
    }
    
    public ProductSpec(Computer computer) {
        initializeGeneralFields(computer);
        memorySize = computer.getMemorySize();
        memorySizeStr = ((Long)memorySize).toString();
        processorSpeed = computer.getProcessorSpeed();
        processorSpeedStr = ((Double)processorSpeed).toString();
        hardDiskSize = computer.getHardDiskSize();
        hardDiskSizeStr = ((Long)hardDiskSize).toString();
        
    }
    
    public ProductSpec(PaperBook paperBook) {
        initializeGeneralFields(paperBook);
        initializeBookFields(paperBook);
        weight = paperBook.getWeight();
        weightStr = ((Double)weight).toString();
        typeOfCover = paperBook.getTypeOfCover();
    }
    
    public ProductSpec(EBook eBook) {
        initializeGeneralFields(eBook);
        initializeBookFields(eBook);
        ebookSize = eBook.getSize();
        ebookSizeStr = ((Long)ebookSize).toString();
    }
    
    private void initializeGeneralFields(Product product) {
        category = product.getCategory();
        productId = product.getProductId();
        brand = product.getBrand();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        priceStr = ((Double)price).toString();
        quantity = product.getQuantity();
        quantityStr = ((Integer)quantity).toString();
    }
    
    private void initializeBookFields(Book book) {
        title = book.getTitle();
        author = book.getAuthor();
        publisher = book.getPublisher();
        genre = book.getGenre();
        isbn = book.getIsbn();
        numberOfPages = book.getNumberOfPages();
        numberOfPagesStr = ((Integer)numberOfPages).toString();
    }
    
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceStr() {
        return priceStr;
    }

    public void setPriceStr(String priceStr) {
        this.priceStr = priceStr;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantityStr() {
        return quantityStr;
    }

    public void setQuantityStr(String quantityStr) {
        this.quantityStr = quantityStr;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getClothSizeStr() {
        return clothSizeStr;
    }

    public void setClothSizeStr(String clothSizeStr) {
        this.clothSizeStr = clothSizeStr;
    }

    public int getClothSize() {
        return clothSize;
    }

    public void setClothSize(int clothSize) {
        this.clothSize = clothSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getClothType() {
        return clothType;
    }

    public void setClothType(String type) {
        this.clothType = type;
    }

    public String getMemorySizeStr() {
        return memorySizeStr;
    }

    public void setMemorySizeStr(String memorySizeStr) {
        this.memorySizeStr = memorySizeStr;
    }

    public long getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(long memorySize) {
        this.memorySize = memorySize;
    }

    public String getProcessorSpeedStr() {
        return processorSpeedStr;
    }

    public void setProcessorSpeedStr(String processorSpeedStr) {
        this.processorSpeedStr = processorSpeedStr;
    }

    public double getProcessorSpeed() {
        return processorSpeed;
    }

    public void setProcessorSpeed(double processorSpeed) {
        this.processorSpeed = processorSpeed;
    }

    public String getHardDiskSizeStr() {
        return hardDiskSizeStr;
    }

    public void setHardDiskSizeStr(String hardDiskSizeStr) {
        this.hardDiskSizeStr = hardDiskSizeStr;
    }

    public long getHardDiskSize() {
        return hardDiskSize;
    }

    public void setHardDiskSize(long hardDiskSize) {
        this.hardDiskSize = hardDiskSize;
    }

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
        this.isbn = isbn;
    }

    public String getNumberOfPagesStr() {
        return numberOfPagesStr;
    }

    public void setNumberOfPagesStr(String numberOfPagesStr) {
        this.numberOfPagesStr = numberOfPagesStr;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getWeightStr() {
        return weightStr;
    }

    public void setWeightStr(String weightStr) {
        this.weightStr = weightStr;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getTypeOfCover() {
        return typeOfCover;
    }

    public void setTypeOfCover(String typeOfCover) {
        this.typeOfCover = typeOfCover;
    }

    public String getEbookSizeStr() {
        return ebookSizeStr;
    }

    public void setEbookSizeStr(String ebookSizeStr) {
        this.ebookSizeStr = ebookSizeStr;
    }

    public long getEbookSize() {
        return ebookSize;
    }

    public void setEbookSize(long ebookSize) {
        this.ebookSize = ebookSize;
    }
}
