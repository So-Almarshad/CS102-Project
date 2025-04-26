/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.util;
import java.util.Scanner;
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
    
    public static boolean isAlphanumeric(String num){
        //Method to check if a string only contains numbers and letters
        for (int i = 0; i < num.length(); i++) {
            if (!(Character.isDigit(num.charAt(i))||Character.isAlphabetic(num.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean authorizePassword(String password){
        //Method to check if password is 6 digits long and if it contains a number
        // a letter and a special character
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
    
    public static void pause(Scanner input) {
        System.out.println("Press enter to continue");
        input.nextLine();
    }
    
}
