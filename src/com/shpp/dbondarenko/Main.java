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
            if (args.length > 1) {
                System.out.print(args[0] + " = " + calculator.calculate(args) + " ");
                for (int i = 1; i < args.length; i++) {
                    System.out.print(args[i] + " ");
                }
            } else {
                System.out.println(args[0] + " = " + calculator.calculate(args));
            }
        } catch (Exception e) {
            System.out.println(args[0] + " = " + "\n" + e.getMessage());
            e.printStackTrace();
        }
    }
}
