/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.users;

import eshop.products.Product;
import java.io.Serializable;

/**
 *
 * @author abdul
 */
public class BasketItem implements Serializable{
    private String productId;
    private Product product;
    private int amountToBuy;
    
    public BasketItem(Product product, int amountToBuy) {
        this.product = product;
        this.productId = product.getProductId();
        this.amountToBuy = amountToBuy;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmountToBuy() {
        return amountToBuy;
    }

    public void setAmountToBuy(int amountToBuy) {
        if(amountToBuy < product.getQuantity())
            this.amountToBuy = amountToBuy;
        else
            this.amountToBuy = product.getQuantity();
    }

    public String getProductId() {
        return productId;
    }

    public boolean isAvailable() {
        return product != null && amountToBuy <= product.getQuantity();
    }
}
