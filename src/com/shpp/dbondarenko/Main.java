package com.shpp.dbondarenko;

/**
 * File: Main.java
 * Created by Dmitro Bondarenko on 08.05.2017.
 */
public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        double result = calculator.calculate("14+14+2");
        System.out.println(result);
    }
}
