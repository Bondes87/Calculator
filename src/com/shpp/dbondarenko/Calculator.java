package com.shpp.dbondarenko;

/**
 * File: Calculator.java
 * Created by Dmitro Bondarenko on 08.05.2017.
 */
public class Calculator {
    private String formula;

    public double calculate(String formula) {
        this.formula = formula;
        return addNumber();
    }

    private double addNumber() {
        double result = parseNumber();
        if (formula.length() > 0 && formula.charAt(0) == '+') {
            formula = formula.substring(1, formula.length());
            result += parseNumber();
        }
        return result;
    }

    private double parseNumber() {
        double result;
        int numberOfDigits = 0;
        for (int i = 0; i < formula.length(); i++) {
            if ((Character.isDigit(formula.charAt(i)))) {
                numberOfDigits++;
            } else {
                break;
            }
        }
        result = Double.parseDouble(formula.substring(0, numberOfDigits));
        formula = formula.substring(numberOfDigits, formula.length());
        return result;
    }

}
