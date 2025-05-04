/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import eshop.launcher.Eshop;
import java.io.*;
import java.util.*;

/**
 *
 * @author abdul
 */
public class Main {
    public static void main(String[] args) throws Exception{
        Scanner input = new Scanner(System.in);
        
        File userFile = new File("User_Test.dat");
        File catalogFile = new File("ClothTest.dat");
        
        Eshop eshop = new Eshop(userFile, catalogFile);
        eshop.launch(input);
    }
}