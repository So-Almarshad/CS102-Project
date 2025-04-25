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
        Scanner sc = new Scanner(System.in);
        
        File catalogFile = new File("cTest1.dat");
        File userFile = new File("uTest.dat");
        catalogFile.delete();
        userFile.delete();
        
        Eshop eshop = new Eshop(userFile, catalogFile);
        eshop.launch(sc);
    }
}
