/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.util;

import eshop.products.*;
import java.util.Comparator;
/**
 *
 * @author abdul
 */
public class Comparators {
    
    public static final Comparator<Product> CATEGORY_COMPARATOR = (a, b) -> a.getCategory().compareTo(b.getCategory());
    public static final Comparator<Product> ID_COMPARATOR = (a, b) -> a.getProductId().compareTo(b.getProductId());
    public static final Comparator<Product> BRAND_COMPARATOR = (a, b) -> a.getBrand().compareTo(b.getBrand());
    public static final Comparator<Product> NAME_COMPARATOR = (a, b) -> a.getName().compareTo(b.getName());
    public static final Comparator<Product> DESCRIPTION_COMPARATOR = (a, b) -> a.getDescription().compareTo(b.getDescription());
    public static final Comparator<Product> PRICE_COMPARATOR = (a, b) -> ((Double)a.getPrice()).compareTo(b.getPrice());
    public static final Comparator<Product> ASCENDING_MEMORY_SIZE_COMPARATOR = (a, b) -> {
        if(!(a instanceof Computer))
            return 1;
        if(!(b instanceof Computer))
            return -1;
        Computer c = (Computer)a;
        Computer d = (Computer)b;
        return ((Long)c.getMemorySize()).compareTo(d.getMemorySize());
    };
    public static final Comparator<Product> ASCENDING_PROCESSOR_SPEED_COMPARATOR = (a, b) -> {
        if(!(a instanceof Computer))
            return 1;
        if(!(b instanceof Computer))
            return -1;
        Computer c = (Computer)a;
        Computer d = (Computer)b;
        return ((Double)c.getProcessorSpeed()).compareTo(d.getProcessorSpeed());
    };
    public static final Comparator<Product> ASCENDING_HARD_DISK_SIZE_COMPARATOR = (a, b) -> {
        if(!(a instanceof Computer))
            return 1;
        if(!(b instanceof Computer))
            return -1;
        Computer c = (Computer)a;
        Computer d = (Computer)b;
        return ((Long)c.getHardDiskSize()).compareTo(d.getHardDiskSize());
    };
    public static final Comparator<Product> ASCENDING_TITLE_COMPARATOR = (a, b) -> {
        if(!(a instanceof Book))
            return 1;
        if(!(b instanceof Book))
            return -1;
        Book c = (Book)a;
        Book d = (Book)b;
        return c.getTitle().compareTo(d.getTitle());
    };
    public static final Comparator<Product> ASCENDING_AUTHOR_COMPARATOR = (a, b) -> {
        if(!(a instanceof Book))
            return 1;
        if(!(b instanceof Book))
            return -1;
        Book c = (Book)a;
        Book d = (Book)b;
        return c.getAuthor().compareTo(d.getAuthor());
    };
    public static final Comparator<Product> DESCENDING_MEMORY_SIZE_COMPARATOR = (a, b) -> {
        if(!(a instanceof Computer))
            return 1;
        if(!(b instanceof Computer))
            return -1;
        Computer c = (Computer)a;
        Computer d = (Computer)b;
        return ((Long)d.getMemorySize()).compareTo(c.getMemorySize());
    };
    public static final Comparator<Product> DESCENDING_PROCESSOR_SPEED_COMPARATOR = (a, b) -> {
        if(!(a instanceof Computer))
            return 1;
        if(!(b instanceof Computer))
            return -1;
        Computer c = (Computer)a;
        Computer d = (Computer)b;
        return ((Double)d.getProcessorSpeed()).compareTo(c.getProcessorSpeed());
    };
    public static final Comparator<Product> DESCENDING_HARD_DISK_SIZE_COMPARATOR = (a, b) -> {
        if(!(a instanceof Computer))
            return 1;
        if(!(b instanceof Computer))
            return -1;
        Computer c = (Computer)a;
        Computer d = (Computer)b;
        return ((Long)d.getHardDiskSize()).compareTo(c.getHardDiskSize());
    };
    public static final Comparator<Product> DESCENDING_TITLE_COMPARATOR = (a, b) -> {
        if(!(a instanceof Book))
            return 1;
        if(!(b instanceof Book))
            return -1;
        Book c = (Book)a;
        Book d = (Book)b;
        return d.getTitle().compareTo(c.getTitle());
    };
    public static final Comparator<Product> DESCENDING_AUTHOR_COMPARATOR = (a, b) -> {
        if(!(a instanceof Book))
            return 1;
        if(!(b instanceof Book))
            return -1;
        Book c = (Book)a;
        Book d = (Book)b;
        return d.getAuthor().compareTo(c.getAuthor());
    };
    private Comparators(){}
    
}
