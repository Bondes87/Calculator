package com.shpp.dbondarenko;

/**
 * File: Main.java
 * Created by Dmitro Bondarenko on 08.05.2017.
 */
public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String formula = "min(-2,-3)";
        double result;
        try {
            result = calculator.calculate(formula);
            System.out.println(formula + " = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
