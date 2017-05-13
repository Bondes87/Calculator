package com.shpp.dbondarenko;

import java.util.Objects;

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
        result = calculateOfFunction();
        if (!Objects.equals(formula, "") && formula.charAt(0) == '(') {
            formula = formula.substring(1, formula.length());
            result = addNumbers();
            if (formula.charAt(0) == ')') {
                formula = formula.substring(1, formula.length());
            }
        } else if (!Objects.equals(formula, "") && Character.isDigit(formula.charAt(0))) {
            result = parseNumber();
        }
        return result;
    }

    private double calculateOfFunction() throws Exception {
        String nameFunction;
        int numberOfLetters = 0;
        for (int i = 0; i < formula.length(); i++) {
            if (Character.isLetter(formula.charAt(i))) {
                numberOfLetters++;
            } else {
                break;
            }
        }
        if (numberOfLetters > 0) {
            nameFunction = formula.substring(0, numberOfLetters);
            formula = formula.substring(numberOfLetters, formula.length());
            result = selectFunction(nameFunction);
        }

        return result;
    }

    private double selectFunction(String nameFunction) throws Exception {
        result = operationsInBrackets();
        switch (nameFunction) {
            case "sqrt":
                if (result >= 0) {
                    result = Math.sqrt(result);
                } else {
                    throw new Exception("Incorrectly written formula. " +
                            "A square root with a negative number does not exist.");
                }
                break;
            case "sin":
                if (result % 180 != 0) {
                    result = Math.sin(Math.toRadians(result));
                } else {
                    result = 0;
                }
                break;
            case "cos":
                if (result % 90 != 0) {
                    result = Math.cos(Math.toRadians(result));
                } else {
                    result = 0;
                }
                break;
            case "tan":
                if (result % 180 != 90) {
                    result = Math.tan(Math.toRadians(result));
                } else {
                    throw new Exception("Incorrectly written formula. " +
                            "A trigonometric function tg with such a value does not exist.");
                }
                break;
            case "abs":
                result = Math.abs(result);
                break;
            case "log":
                result = Math.log(result);
                break;
            case "exp":
                result = Math.exp(result);
                break;
            case "pow":
                double exponent = 0;
                if (formula.charAt(0) == ',') {
                    formula = formula.substring(1, formula.length());
                    exponent = addNumbers();
                    formula = formula.substring(1, formula.length());
                }
                result = Math.pow(result, exponent);
                break;
            case "min":
                if (formula.charAt(0) == ',') {
                    formula = formula.substring(1, formula.length());
                }
                result = Math.min(result, addNumbers());
                break;
        }
        return result;
    }

    private double parseNumber() throws Exception {
        double result;
        int numberOfDigits = 0;
        int numberOfPoints = 0;
        boolean isNegativeNumber = false;
        if (formula.charAt(0) == '-') {
            isNegativeNumber = true;
            formula = formula.substring(1, formula.length());
        }
        for (int i = 0; i < formula.length(); i++) {
            if (Character.isDigit(formula.charAt(i))) {
                numberOfDigits++;
            } else if (formula.charAt(i) == '.') {
                numberOfPoints++;
            } else {
                break;
            }
        }
        if (numberOfDigits != 0 && formula.charAt(0) != '.'
                && formula.charAt(numberOfDigits + numberOfPoints - 1) != '.'
                && numberOfPoints < 2) {
            result = Double.parseDouble(formula.substring(0, numberOfDigits + numberOfPoints));
            System.out.println(result);
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
