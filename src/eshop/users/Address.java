/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package eshop.users;

import eshop.util.Util;
import java.io.Serializable;
import java.util.Objects;
/**
 *
 * @author SAUD
 */
public class Address implements Serializable{
    
    private String streetName;
    private String buildingNumber;
    private String postCode;

    public Address(String streetName, String buildingNumber, String postCode) {
        this.streetName = streetName;
        
        if(Util.isNumeric(buildingNumber))
            this.buildingNumber = buildingNumber;
        else 
            throw new IllegalArgumentException("Building number should be numeric");
        
        if (Util.isNumeric(postCode))
            this.postCode = postCode;
        else 
            throw new IllegalArgumentException("Post Code should be numeric");
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
        if (Util.isInteger(postCode))
            this.postCode = postCode;
        else throw new IllegalArgumentException("Post Code should be numeric");
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, buildingNumber, postCode);
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
        final Address other = (Address) obj;
        if (!Objects.equals(this.streetName, other.streetName)) {
            return false;
        }
        if (!Objects.equals(this.buildingNumber, other.buildingNumber)) {
            return false;
        }
        return Objects.equals(this.postCode, other.postCode);
    }
    
    
}
