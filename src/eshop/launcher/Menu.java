/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;

/**
 *
 * @author abdul
 */
public abstract class Menu {
    private Eshop eshop;
    
    public Menu(Eshop eshop) {
        this.eshop = eshop;
    }

    public Eshop getEshop() {
        return eshop;
    }

    public void setEshop(Eshop eshop) {
        this.eshop = eshop;
    }
    
    public abstract void display();

    public abstract void select(int optionNum);
}
