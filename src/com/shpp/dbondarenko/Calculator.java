package com.shpp.dbondarenko;

/**
 * File: Calculator.java
 * Created by Dmitro Bondarenko on 08.05.2017.
 */
public class Calculator {
    private String formula;
    private double result;

    public double calculate(String formula) throws Exception {
        this.formula = formula;
        return addNumber();
    }

    private double addNumber() throws Exception {
        result = subtractionNumber();
        while (formula.length() > 0 && formula.charAt(0) == '+') {
            formula = formula.substring(1, formula.length());
            result += subtractionNumber();
        }
        return result;
    }

    private double subtractionNumber() throws Exception {
        result = multiplicationNumber();
        while (formula.length() > 0 && formula.charAt(0) == '-') {
            formula = formula.substring(1, formula.length());
            result -= multiplicationNumber();
        }
        return result;
    }

    private double multiplicationNumber() throws Exception {
        result = divisionNumber();
        while (formula.length() > 0 && formula.charAt(0) == '*') {
            formula = formula.substring(1, formula.length());
            result *= divisionNumber();
        }
        return result;
    }

    private double divisionNumber() throws Exception {
        result = parseNumber();
        while (formula.length() > 0 && formula.charAt(0) == '/') {
            formula = formula.substring(1, formula.length());
            result /= parseNumber();
        }
        return result;
    }

    private double parseNumber() throws Exception {
        double result;
        int numberOfDigits = 0;
        for (int i = 0; i < formula.length(); i++) {
            if ((Character.isDigit(formula.charAt(i)))) {
                numberOfDigits++;
            } else {
                break;
            }
        }
        if (numberOfDigits != 0) {
            result = Double.parseDouble(formula.substring(0, numberOfDigits));
            formula = formula.substring(numberOfDigits, formula.length());

        } else {
            throw new Exception("Incorrectly written formula");
        }
        return result;
    }
}
