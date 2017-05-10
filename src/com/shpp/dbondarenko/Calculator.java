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
        return addNumbers();
    }

    private double addNumbers() throws Exception {
        result = subtractionNumbers();
        while (formula.length() > 0 && formula.charAt(0) == '+') {
            if (isFormulaTwoSignsTogether(formula)) {
                throw new Exception("Incorrectly written formula. The formula has two signs in a row");
            }
            formula = formula.substring(1, formula.length());
            result += subtractionNumbers();
        }
        return result;
    }

    private double subtractionNumbers() throws Exception {
        result = multiplicationNumbers();
        while (formula.length() > 0 && formula.charAt(0) == '-') {
            if (isFormulaTwoSignsTogether(formula)) {
                throw new Exception("Incorrectly written formula. The formula has two signs in a row");
            }
            formula = formula.substring(1, formula.length());
            result -= multiplicationNumbers();
        }
        return result;
    }

    private double multiplicationNumbers() throws Exception {
        result = divisionNumbers();
        while (formula.length() > 0 && formula.charAt(0) == '*') {
            if (isFormulaTwoSignsTogether(formula)) {
                throw new Exception("Incorrectly written formula. The formula has two signs in a row");
            }
            formula = formula.substring(1, formula.length());
            result *= divisionNumbers();
        }
        return result;
    }

    private double divisionNumbers() throws Exception {
        result = operationsInBrackets();
        while (formula.length() > 0 && formula.charAt(0) == '/') {
            if (isFormulaTwoSignsTogether(formula)) {
                throw new Exception("Incorrectly written formula. The formula has two signs in a row");
            }
            formula = formula.substring(1, formula.length());
            result /= operationsInBrackets();
        }
        return result;
    }

    private double operationsInBrackets() throws Exception {
        if (formula.charAt(0) == '(') {
            formula = formula.substring(1, formula.length());
            result = addNumbers();
            if (formula.charAt(0) == ')') {
                formula = formula.substring(1, formula.length());
            }
        } else {
            result = parseNumber();
        }
        return result;
    }

    private double parseNumber() throws Exception {
        double result;
        int numberOfDigits = 0;
        boolean isNegativeNumber = false;
        if (formula.charAt(0) == '-') {
            isNegativeNumber = true;
            formula = formula.substring(1, formula.length());
        }
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
        if (isNegativeNumber) {
            result = -result;
        }
        return result;
    }

    private boolean isFormulaTwoSignsTogether(String formula) {
        String signs = "-+*/";
        for (char firstSing : signs.toCharArray()) {
            if (firstSing == formula.charAt(0)) {
                for (char secondSing : signs.toCharArray()) {
                    if (secondSing == formula.charAt(1)) {
                        System.out.println("Error: " + formula.charAt(0) + "" + formula.charAt(1));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
