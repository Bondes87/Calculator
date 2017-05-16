package com.shpp.dbondarenko;

import java.util.HashMap;

/**
 * File: Main.java
 * Created by Dmitro Bondarenko on 08.05.2017.
 */
public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String formula = args[0];
        System.out.println(args.length);
        HashMap<String, Double> variables = new HashMap<>();
        if (args.length > 1) {
            for (int i = 1; i < args.length; i++) {
                String[] variableAndValue = args[1].split("=");
                if (!variableAndValue[0].equals("") && !variableAndValue[1].equals("")) {
                    variables.put(variableAndValue[0], Double.parseDouble(variableAndValue[1]));

                }
            }
        }
        double result;
        try {
            result = calculator.calculate(formula, variables);
            System.out.println(formula + " = " + result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getStackTrace());
        }
    }
}
