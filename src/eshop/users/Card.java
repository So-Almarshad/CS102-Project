/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package eshop.users;

import eshop.util.Util;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author SAUD
 */
public class Card implements Serializable{

    private String cardNumber;
    private String holderName;
    private String ccvNumber;
    private Date expireyDate;

    public Card(String cardNumber, String holderName, String ccvNumber, Date expireyDate) {
        
        if(cardNumber.length() != 16)
            throw new IllegalArgumentException("Card number should be 16-digit");
        if(!Util.isInteger(cardNumber))
            throw new IllegalArgumentException("Card number should only contain integer values 0-9");
        
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        
        if(ccvNumber.length() != 3)
            throw new IllegalArgumentException("CCV number should be 3-digit");
        if(!Util.isInteger(ccvNumber))
            throw new IllegalArgumentException("CCV number should only contain integer values 0-9");
        
        this.ccvNumber = ccvNumber;
        this.expireyDate = expireyDate;
    }
    
    public String getCardNumber() {
        return cardNumber;
    }
    
    public String getHolderName() {
        return holderName;
    }
    
    public String getCcvNumber() {
        return ccvNumber;
    }
    
    public Date getExpireyDate() {
        return (Date)expireyDate.clone();
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, holderName, ccvNumber, expireyDate);
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
        final Card other = (Card) obj;
        if (!Objects.equals(this.cardNumber, other.cardNumber)) {
            return false;
        }
        if (!Objects.equals(this.holderName, other.holderName)) {
            return false;
        }
        if (!Objects.equals(this.ccvNumber, other.ccvNumber)) {
            return false;
        }
        return Objects.equals(this.expireyDate, other.expireyDate);
    }
    
    
}
