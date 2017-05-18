package com.shpp.dbondarenko;

/**
 * File: Main.java
 * The program that performs mathematical calculations. This is the program start class.
 * Created by Dmitro Bondarenko on 08.05.2017.
 */
public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        try {
            System.out.println(args[0] + " = " + calculator.calculate(args));
        } catch (Exception e) {
            System.out.println(args[0] + " = " + "\n" + e.getMessage());
        }
    }
}
