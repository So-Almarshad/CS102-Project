/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testing;

import java.time.Instant;
import java.util.*;
/**
 *
 * @author abdul
 */
public class CalendarTest {
    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(2025, 2, 50);
        Date date = Date.from(calendar.toInstant());
        System.out.println(date);
    }
}
