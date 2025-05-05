/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.users;

import java.util.Objects;

/**
 *
 * @author abdul
 */
public class Customer extends User{
    
    private Card paymentCard;
    private Address shippingAddress;
    private Basket basket;
    
    public Customer(String username, String password, String name, int age, Card paymentCard, Address shippingAddress) {
        super(username, password, name, age);
        this.paymentCard = paymentCard;
        this.shippingAddress = shippingAddress;
        this.basket = new Basket(this);
    }
    
    public Card getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(Card paymentCard) {
        this.paymentCard = paymentCard;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Basket getBasket() {
        return basket;
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentCard, shippingAddress);
    }
}
