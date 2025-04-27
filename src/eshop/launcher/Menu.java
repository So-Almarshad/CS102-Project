/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;
import eshop.util.Util;
import java.util.Scanner;
/**
 *
 * @author abdul
 */
public abstract class Menu {
    protected Eshop eshop;
    protected Scanner input;
    private String header;
    private String[] options;
    
    public Menu(Eshop eshop, String header, String... options) {
        this.eshop = eshop;
        this.input = eshop.getInput();
        this.header = header + '\n' + "-".repeat(header.length()) + '\n';
        this.options = options;
    }

    public Eshop getEshop() {
        return eshop;
    }

    public void setEshop(Eshop eshop) {
        this.eshop = eshop;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String[] getOptions() {
        return options;
    }
    
    public void setOptions(String... options) {
        this.options = options;
    }
    
    public void display() {
        StringBuilder optionsString = new StringBuilder();
        for(int i = 0; i < options.length; i++) {
            optionsString.append((i + 1) + ". " + options[i] + '\n');
        }
        System.out.println(header + optionsString);
    }
    
    public abstract void select(int optionNum);
}
