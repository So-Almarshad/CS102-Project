/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package eshop.users;

import eshop.util.Util;
/**
 *
 * @author SAUD
 */
public class Address {
    
    
    private String streetName;
    private String buildingNumber;
    private String postCode;

    public Address(String streetName, String buildingNumber, String postCode) {
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        
        if (Util.isNumeric(postCode))
            this.postCode = postCode;
        else throw new IllegalArgumentException("Post Code should be numeric");
    }

    /******GETTERS & SETTERS******/
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        if (Util.isNumeric(postCode))
            this.postCode = postCode;
        else throw new IllegalArgumentException("Post Code should be numeric");
    }
}
