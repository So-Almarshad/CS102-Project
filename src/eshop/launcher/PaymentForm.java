/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eshop.launcher;

import eshop.users.Address;
import eshop.users.Card;
import eshop.users.Customer;
import eshop.util.Util;
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
    
    private String cardNumber = "";
    private String holderName = "";
    private String ccvNumber = "";
    private Date expirationDate;
    private String dateStr = "MM/YY";
    
    private String streetName= "";
    private String buildingNumber = "";
    private String postCode = "";
    
    public PaymentForm(Eshop eshop, Menu returnMenu) {
        super(eshop, "ENTER PAYMENT INFORMATION");
        this.returnMenu = returnMenu;
        this.customer = (Customer)eshop.getActiveUser();
        
    }

    @Override
    public void select(int optionNum) {
        switch(optionNum) {
            case 1:
                System.out.print("Enter card number: ");
                String cardNum = input.nextLine().trim();
                if(cardNum.length() != 16 || !Util.isNumeric(cardNum)) {
                    System.out.println("Card number should be a 16-digit integer");
                    Util.pause(input);
                    break;
                }
                cardNumber = cardNum;
                break;
            case 2:
                System.out.print("Enter CCV number: ");
                String ccvNum = input.nextLine().trim();
                if(ccvNum.length() != 3 || !Util.isNumeric(ccvNum)) {
                    System.out.println("CCV number should be a 3-digit integer");
                    Util.pause(input);
                    break;
                }
                ccvNumber = ccvNum;
                break;
            case 3:
                System.out.print("Enter holder name: ");
                holderName = input.nextLine().trim();
                break;
            case 4:
                System.out.print("Enter expiration date (MM/YY): ");
                String dateString = input.nextLine();
                if(isValidDate(dateString)) {
                    dateStr = dateString;
                    expirationDate = stringToDate(dateStr);
                }
                else {
                    System.out.println("Date must follow the listed format");
                    Util.pause(input);
                }
                break;
            case 5:
                System.out.print("Enter street name: ");
                streetName  = input.nextLine().trim();
                break;
            case 6:
                System.out.print("Enter building number: ");
                String buildingNum = input.nextLine().trim();
                if(!Util.isNumeric(buildingNum)) {
                    System.out.println("Building number should be numeric");
                    Util.pause(input);
                    break;
                }
                buildingNumber = buildingNum;
                break;
            case 7:
                System.out.print("Enter post code: ");
                String postCodeStr = input.nextLine().trim();
                if(!Util.isNumeric(postCodeStr)) {
                    System.out.println("post code number should be numeric");
                    Util.pause(input);
                    break;
                }
                postCode = postCodeStr;
                break;
            case 8:
                if(missingFields()) {
                    System.out.println("Some fields are missing");
                    Util.pause(input);
                    break;
                }
                customer.setPaymentCard(new Card(cardNumber, holderName, ccvNumber, expirationDate));
                customer.setShippingAddress(new Address(streetName, buildingNumber, postCode));
                System.out.println("Payment information added");
                Util.pause(input);
                eshop.setActiveMenu(returnMenu);
                break;
            case 9:
                eshop.setActiveMenu(returnMenu);
                break;
            default:
                System.out.println("That is not an option");
                Util.pause(input);
        }
    }

    @Override
    public void display() {
        updateOptions();
        StringBuilder optionsString = new StringBuilder();
        for(int i = 0; i < options.length; i++) {
            optionsString.append((i + 1) + ". " + options[i] + '\n');
        }
        System.out.println(header + "\nCard information:" + optionsString);
    }
    
    private void updateOptions() {
        setOptions("Card number: " + cardNumber, "CCV number: " + ccvNumber,
                 "Holder name: " + holderName, "Expiration date: " + dateStr + "\n\nShipping address:",
                 "Street name: " + streetName, "Building number: " + buildingNumber,
                 "Post code: " + postCode + '\n', "Confirm", "Back");
    }
    
    //Checks if a string is in the correct format to convert into a date
    private boolean isValidDate(String dateString) {
        String[] dateArr = dateString.split("/");
        if(dateArr.length != 2)
            return false;
        for(String s : dateArr) {
            if(!Util.isInteger(s) || (s.length() != 2 && s.length() != 1))
                return false;
        }
        return true;
    }
    
    //Converts a string in the format MM/YY into a date object of the last second
    //of that part of the day
    private static Date stringToDate(String str) {
        String[] dateArr = str.split("/");
        int month = Integer.parseInt(dateArr[0]);
        int year = Integer.parseInt(dateArr[1]) + 2000;
        Calendar calendar = new GregorianCalendar(year, month - 1, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return Date.from(calendar.toInstant());
    }
    
    //Converts a date object to a string in the format of MM/YY
    private String dateToString(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String month = Integer.toString(calendar.get(Calendar.MONTH));
        String year = Integer.toBinaryString(calendar.get(Calendar.YEAR));
        return month + '/' + year;
    }
    
    private boolean missingFields() {
        boolean missingCardNumber = cardNumber == "";
        boolean missingHolderName = holderName == "";
        boolean missingCCVNumber = ccvNumber == "";
        boolean missingDate = expirationDate == null;
        boolean missingStreetName = streetName == "";
        boolean missingBuildingNumber = buildingNumber == "";
        boolean missingPostCode = postCode == "";
        return missingCardNumber || missingHolderName || missingCCVNumber ||
                missingDate || missingStreetName || missingBuildingNumber ||
                missingPostCode;
    }
}   
