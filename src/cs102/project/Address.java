/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package CS102.Project;

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
        
        if (isNumeric(postCode))
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
        if (isNumeric(postCode))
            this.postCode = postCode;
        else throw new IllegalArgumentException("Post Code should be numeric");
    }
    
    /******METHODS******/
    private static boolean isNumeric(String num){
        //Method to check if a string only contains numbers
        for (int i = 0; i < num.length(); i++) {
            if (!(Character.isDigit(num.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
}
