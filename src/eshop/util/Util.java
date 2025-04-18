/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.util;

/**
 *
 * @author abdul
 */

//This class provides general helper methods
public final class Util {
    
    private Util() {}
    
    //Method to check if a string only contains numbers
    public static boolean isNumeric(String num) {
        int length = num.length();
        if(num.isEmpty()) return false;
        for(int i = 0; i < length; i++) {
            if(!Character.isDigit(num.charAt(i)))
                return false;
        }
        return true;
    }
}
