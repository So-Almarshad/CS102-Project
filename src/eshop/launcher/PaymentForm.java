/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;

import eshop.users.Address;
import eshop.users.Card;
import eshop.users.Customer;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author abdul
 */
public class PaymentForm extends Menu{
    private Menu returnMenu;
    private Customer customer;
    private Card paymentcard;
    private Address shippingAddress;
    
    private String catdNumber = "";
    private String holderName = "";
    private String ccvNumber = "";
    private Date expiryDate;
    private String dateStr = "DD/MM/YYYY";
    
    private String buildingNumber = "";
    
    public PaymentForm(Eshop eshop, Menu returnMenu) {
        super(eshop, "ENTER PAYMENT INFORMATION");
        this.returnMenu = returnMenu;
        this.customer = (Customer)eshop.getActiveUser();
        this.paymentcard = customer.getPaymentCard();
        this.shippingAddress = customer.getShippingAddress();
    }

    @Override
    public void select(int optionNum) {
        switch(optionNum) {
            case 1:
                
        }
    }
    
    private void updateOptions() {
        setOptions("");
    }
    
//    private boolean isValidDate(String dateString) {
//        String[] dateArr = dateString.split("/");
//        int[] date
//        for(String s : dateArr) {
//            if(s)
//        }
//    }
    
    private Date stringToDate(String str) {
        String[] dateArr = str.split("/");
        int day = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]);
        int year = Integer.parseInt(dateArr[2]);
        Calendar calendar = new GregorianCalendar(year, month, day);
        return Date.from(calendar.toInstant());
    }
}
