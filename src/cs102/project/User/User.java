/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package cs102.project.User;

/**
 *
 * @author SAUD
 */
public class User {

    //Missing this requirement "Actors should have distinct usernames."
    
    private String username;
    private String password;
    private String name;
    private int age;

    public User(String username, String password, String name, int age) {
        if (isAlphanumeric(username))
            this.username = username;
        else    throw new IllegalArgumentException("Username should be alphanumerical");
        
        if (authorizePassword(password))
            this.password = password;
        else    throw new IllegalArgumentException("Password should be more than"
                + " six digits long, contains a letter, contains a number, contains a special character");
        
        this.name = name;
        
        if (age>15)
            this.age = age;
        else    throw new IllegalArgumentException("Age should be greater than 15");
    }

    /******GETTERS & SETTERS******/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (isAlphanumeric(username))
            this.username = username;
        else    throw new IllegalArgumentException("Username should be alphanumerical");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (authorizePassword(password))
            this.password = password;
        else    throw new IllegalArgumentException("Password should be more than"
                + " six digits long, contains a letter, contains a number, contains a special character");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age>15)
            this.age = age;
        else    throw new IllegalArgumentException("Age should be greater than 15");
    }
    
    
    /******METHODS******/
    private static boolean isAlphanumeric(String num){
        //Method to check if a string only contains numbers and letters
        for (int i = 0; i < num.length(); i++) {
            if (!(Character.isDigit(num.charAt(i))||Character.isAlphabetic(num.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean authorizePassword(String password){
        //Method to check if password is 6 digits long and if it contains a number
        // a letter and a special character
        int numCount=0,letterCount=0,specialCount=0;
        
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i)))
                numCount++;
            else if (Character.isAlphabetic(password.charAt(i)))
                letterCount++;
            else
                specialCount++;
        }
        if (specialCount>0 && letterCount>0 && numCount>0 && password.length()>=6) 
            return true;
        
        else
            return false;
    }
}
