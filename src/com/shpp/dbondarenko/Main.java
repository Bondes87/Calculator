package com.shpp.dbondarenko;

/**
 * File: Main.java
 * Created by Dmitro Bondarenko on 08.05.2017.
 */
public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String formula = "14+1*2-5*2";
        double result = 0;
        try {
            result = calculator.calculate(formula);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(formula + " = " + result);
    }
}
