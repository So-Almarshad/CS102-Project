/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.util;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;
/**
 *
 * @author abdul
 */

//This class provides general helper methods
public final class Util {
    
    private Util() {}
    
    //Checks if a string only contains positive numbers and is in the integer range
    public static boolean isInteger(String num) {
        if(num.isEmpty()) return false;
        int length = num.length();
        for(int i = 0; i < length; i++) {
            if(!Character.isDigit(num.charAt(i)))
                return false;
        }
        String maxInt = ((Integer)Integer.MAX_VALUE).toString();
        BigInteger bigInteger = new BigInteger(num);
        return bigInteger.compareTo(new BigInteger(maxInt)) == -1
                && !bigInteger.equals(BigInteger.ZERO);
    }
    
    //Checks if a string only contains positive numbers and is in the long range
    public static boolean isLong(String num) {
        if(num.isEmpty()) return false;
        int length = num.length();
        for(int i = 0; i < length; i++) {
            if(!Character.isDigit(num.charAt(i)))
                return false;
        }
        String maxInt = ((Long)Long.MAX_VALUE).toString();
        BigInteger bigInteger = new BigInteger(num);
        return bigInteger.compareTo(new BigInteger(maxInt)) == -1
                && !bigInteger.equals(BigInteger.ZERO);
    }
    
    //Checks if a string is a decimal number
    public static boolean isDecimal(String num) {
        if(num.isEmpty()) return false;
        int length = num.length();
        boolean includesDecimalPoint = false;
        for(int i = 0; i < length; i++) {
            char c = num.charAt(i);
            if(c == '.' && !includesDecimalPoint) {
                includesDecimalPoint = true;
                continue;
            }
            if(!Character.isDigit(c))
                return false;
        }
        if(num.charAt(0) == '.' || num.charAt(length - 1) == '.')
            return false;
        String maxDec = ((Double)Double.MAX_VALUE).toString();
        BigDecimal bigDecimal = new BigDecimal(num);
        return bigDecimal.compareTo(new BigDecimal(maxDec)) == -1
                && !bigDecimal.equals(BigDecimal.ZERO);
    }
    
    //Method to check if a string only contains numbers and letters
    public static boolean isAlphanumeric(String num){
        for (int i = 0; i < num.length(); i++) {
            if (!(Character.isDigit(num.charAt(i))||Character.isAlphabetic(num.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
    
    //Method to check if password is 6 digits long and if it contains a number
    // a letter and a special character
    public static boolean authorizePassword(String password){
        
        int numCount = 0, letterCount = 0, specialCount = 0;
        
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i)))
                numCount++;
            else if (Character.isAlphabetic(password.charAt(i)))
                letterCount++;
            else
                specialCount++;
        }
        if (specialCount > 0 && letterCount > 0 && numCount > 0 && password.length() >= 6) 
            return true;
        else
            return false;
    }
    
    //Method that prompts the user to press enter to continue
    //Required to notify the user of something before the console clears
    public static void pause(Scanner input) {
        System.out.println("Press enter to continue");
        input.nextLine();
    }
    
    //Omits any part of the string that exceeds the length and replaces the last
    //three characters before the length with "..."
    public static String cut(String str, int length) {
        if(str.length() > length) {
            return str.substring(0, length - 3) + "...";
        }
        return str;
    }
    
    
}
