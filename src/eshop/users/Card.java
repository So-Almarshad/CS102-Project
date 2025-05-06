/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package eshop.users;

import eshop.util.Util;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author SAUD
 */
public class Card implements Serializable{

    private byte[] cardNumber;
    private String holderName;
    private byte[] ccvNumber;
    private Date expirationDate;

    public Card(String cardNumber, String holderName, String ccvNumber, Date expireyDate) {
        
        if(cardNumber.length() != 16)
            throw new IllegalArgumentException("Card number should be 16-digit");
        if(!Util.isNumeric(cardNumber))
            throw new IllegalArgumentException("Card number should only contain integer values 0-9");
        if(ccvNumber.length() != 3)
            throw new IllegalArgumentException("CCV number should be 3-digit");
        if(!Util.isNumeric(ccvNumber))
            throw new IllegalArgumentException("CCV number should only contain integer values 0-9");
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] cardNumberDigest = messageDigest.digest(cardNumber.getBytes());
            byte[] ccvDigest = messageDigest.digest(ccvNumber.getBytes());
            this.cardNumber = cardNumberDigest;
            this.ccvNumber = ccvDigest;
        } catch(NoSuchAlgorithmException e) {e.printStackTrace();}
        
        this.holderName = holderName;
        this.expirationDate = expireyDate;
    }
    
    public byte[] getCardNumber() {
        return cardNumber;
    }
    
    public String getHolderName() {
        return holderName;
    }
    
    public byte[] getCcvNumber() {
        return ccvNumber;
    }
    
    public Date getExpirationDate() {
        return (Date)expirationDate.clone();
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, holderName, ccvNumber, expirationDate);
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
        return Objects.equals(this.expirationDate, other.expirationDate);
    }
    
    
}
