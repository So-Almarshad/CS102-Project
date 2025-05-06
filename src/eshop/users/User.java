/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package eshop.users;

import java.io.Serializable;
import eshop.util.Util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author SAUD
 */
public class User implements Serializable{

    //Missing this requirement "Actors should have distinct usernames."
    
    private String username;
    private byte[] password;
    private String name;
    private int age;

    public User(String username, String password, String name, int age) {
        if (Util.isAlphanumeric(username))
            this.username = username;
        else
            throw new IllegalArgumentException("Username should be alphanumerical");
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] passwordByteArray = password.getBytes();
            if (Util.authorizePassword(password))
                this.password = messageDigest.digest(passwordByteArray);
            else
                throw new IllegalArgumentException("Password should be more than"
                    + " six digits long, contains a letter, contains a number, contains a special character");
        } catch(NoSuchAlgorithmException e) {e.printStackTrace();}
        
        this.name = name;
        
        if (age>15)
            this.age = age;
        else
            throw new IllegalArgumentException("Age should be greater than 15");
    }

    /******GETTERS & SETTERS******/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (Util.isAlphanumeric(username))
            this.username = username;
        else
            throw new IllegalArgumentException("Username should be alphanumerical");
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] passwordByteArray = password.getBytes();
            if (Util.authorizePassword(password))
                this.password = messageDigest.digest(passwordByteArray);
            else
                throw new IllegalArgumentException("Password should be more than"
                    + " six digits long, contains a letter, contains a number, contains a special character");
        } catch(NoSuchAlgorithmException e) {e.printStackTrace();}
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
        if (age > 15)
            this.age = age;
        else
            throw new IllegalArgumentException("Age should be greater than 15");
    }
    
}
