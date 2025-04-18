/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.users;

/**
 *
 * @author abdul
 */
public class Customer extends User{
    
    private Card paymentCard;
    private Address shippingAddress;

    public Customer(Card paymentCard, Address shippingAddress, String username, String password, String name, int age) {
        super(username, password, name, age);
        this.paymentCard = paymentCard;
        this.shippingAddress = shippingAddress;
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
}
