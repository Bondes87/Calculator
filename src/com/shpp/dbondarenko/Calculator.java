package com.shpp.dbondarenko;

import java.util.HashMap;
import java.util.Objects;

/**
 * File: Calculator.java
 * The class that performs calculation of mathematical expressions.
 * Created by Dmitro Bondarenko on 08.05.2017.
 */
public class Calculator {
    private HashMap<String, Double> variables;
    private String formula;

    /**
     * Starts the calculator.
     *
     * @param args An array that contains a mathematical expression and the value of variables.
     * @return Returns the result of evaluating a mathematical expression.
     */

    public double calculate(String[] args) throws Exception {
        formula = args[0];
        parseVariables(args);
        return addNumbers();
    }

    /**
     * Parses the value of variables. Adds the value of constants to an associative array.
     *
     * @param args An array that contains a mathematical expression and the value of variables.
     */
    private void parseVariables(String[] args) {
        variables = new HashMap<>();
        if (args.length > 1) {
            for (int i = 1; i < args.length; i++) {
                String[] variableAndValue = args[1].split("=");
                if (!variableAndValue[0].equals("") && !variableAndValue[1].equals("")) {
                    variables.put(variableAndValue[0], Double.parseDouble(variableAndValue[1]));
                }
            }
        }
        addConstants();
    }

    /**
     * Adds the value of constants to an associative array.
     */
    private void addConstants() {
        variables.put("e", 2.71828182846);
        variables.put("pi", 3.14159265358);
    }

    /**
     * Performs adding of numbers.
     *
     * @return Returns the result of the addition, if the operation was carried out, or the number.
     */
    private double addNumbers() throws Exception {
        double result = subtractionNumbers();
        while (formula.length() > 0 && formula.charAt(0) == '+') {
            if (isFormulaTwoSignsTogether(formula)) {
                throw new Exception("Incorrectly written formula. The formula has two signs in a row");
            }
            formula = formula.substring(1, formula.length());
            result += subtractionNumbers();
        }
        return result;
    }

    /**
     * Performs subtraction of numbers.
     *
     * @return Returns the result of the subtraction, if the operation was carried out, or the number.
     */
    private double subtractionNumbers() throws Exception {
        double result = multiplicationNumbers();
        while (formula.length() > 0 && formula.charAt(0) == '-') {
            if (isFormulaTwoSignsTogether(formula)) {
                throw new Exception("Incorrectly written formula. The formula has two signs in a row");
            }
            formula = formula.substring(1, formula.length());
            result -= multiplicationNumbers();
        }
        return result;
    }

    /**
     * Performs multiplication of numbers.
     *
     * @return Returns the result of the multiplication, if the operation was carried out, or the number.
     */
    private double multiplicationNumbers() throws Exception {
        double result = divisionNumbers();
        while (formula.length() > 0 && formula.charAt(0) == '*') {
            if (isFormulaTwoSignsTogether(formula)) {
                throw new Exception("Incorrectly written formula. The formula has two signs in a row");
            }
            formula = formula.substring(1, formula.length());
            result *= divisionNumbers();
        }
        return result;
    }

    /**
     * Performs division of numbers.
     *
     * @return Returns the result of the division, if the operation was carried out, or the number.
     */
    private double divisionNumbers() throws Exception {
        double result = operationsInBrackets();
        while (formula.length() > 0 && formula.charAt(0) == '/') {
            if (isFormulaTwoSignsTogether(formula)) {
                throw new Exception("Incorrectly written formula. The formula has two signs in a row");
            }
            formula = formula.substring(1, formula.length());
            double divisor = operationsInBrackets();
            if (divisor != 0) {
                result /= divisor;
            } else {
                throw new Exception("Sorry, it's impossible to make a calculation. " +
                        "Division by zero is forbidden.");
            }
        }
        return result;
    }

    /**
     * Performs operations in brackets.
     *
     * @return Returns the result of the performs operations in brackets,
     * if the operation was carried out, or the number.
     */
    private double operationsInBrackets() throws Exception {
        double result = calculateOfFunction();
        if (!Objects.equals(formula, "") && formula.charAt(0) == '(') {
            formula = formula.substring(1, formula.length());
            result = addNumbers();
            if (!Objects.equals(formula, "") && formula.charAt(0) == ')') {
                formula = formula.substring(1, formula.length());
            }
        } else if (!Objects.equals(formula, "") && Character.isDigit(formula.charAt(0))) {
            result = parseNumber();
        }
        return result;
    }

    /**
     * Performs counting of function.
     *
     * @return Returns the result of the performs counting of function,
     * if the function was found, or the variable value, if the variable was found,
     * or zero.
     */
    private double calculateOfFunction() throws Exception {
        double result = 0;
        int numberOfLetters = 0;
        // Count the number of letters, if any.
        for (int i = 0; i < formula.length() && Character.isLetter(formula.charAt(i)); i++) {
            numberOfLetters++;
        }
        // If the letters are greater than 2, then this is a function.
        if (numberOfLetters > 2) {
            String nameFunction = formula.substring(0, numberOfLetters);
            formula = formula.substring(numberOfLetters, formula.length());
            result = selectFunction(nameFunction);
        } else if (numberOfLetters > 0) { // If the letters are less than 2 and greater than zero, then this is a variable
            String nameVariable = formula.substring(0, numberOfLetters);
            formula = formula.substring(numberOfLetters, formula.length());
            result = getValueOfVariable(nameVariable);
        }
        return result;
    }

    /**
     * Get the value of a variable if it exists in an associative array.
     *
     * @return Returns the value of a variable.
     */
    private double getValueOfVariable(String nameVariable) throws Exception {
        if (!variables.containsKey(nameVariable)) {
            throw new Exception("Incorrectly written formula. Value of variable not found");
        }
        return variables.get(nameVariable);
    }


    /**
     * Selecting and calculating the function.
     *
     * @param nameFunction The name of the function
     * @return Returns the result of a function.
     */
    private double selectFunction(String nameFunction) throws Exception {
        double result = operationsInBrackets();
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
                if (result % 180 != 90 && result % 180 != -90) {
                    result = Math.cos(Math.toRadians(result));
                } else {
                    result = 0;
                }
                break;
            case "tan":
                if (result % 180 != 90 && result % 180 != -90) {
                    if (result % 180 == 45 || result % 180 == -45) {
                        result = 1;
                    } else if (result % 180 == 135 || result % 180 == -135) {
                        result = -1;
                    } else {
                        result = Math.tan(Math.toRadians(result));
                    }
                } else {
                    throw new Exception("Incorrectly written formula. " +
                            "A trigonometric function tan() with such a value does not exist.");
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
                double base = result;
                double exponent;
                exponent = getSecondArgumentOfFunction();
                result = Math.pow(base, exponent);
                break;
            case "min":
                double firstArgument = result;
                double secondArgument;
                secondArgument = getSecondArgumentOfFunction();
                result = Math.min(firstArgument, secondArgument);
                break;
            case "max":
                firstArgument = result;
                secondArgument = getSecondArgumentOfFunction();
                result = Math.max(firstArgument, secondArgument);
                break;
        }
        return result;
    }


    /**
     * Calculates the second argument of the function.
     *
     * @return Returns the calculation result of the second argument of the function.
     */
    private double getSecondArgumentOfFunction() throws Exception {
        double secondArgument;
        if (!Objects.equals(formula, "") && formula.charAt(0) == ',') {
            formula = formula.substring(1, formula.length());
            secondArgument = addNumbers();
            formula = formula.substring(1, formula.length());
        } else {
            throw new Exception("Incorrectly written formula. " +
                    "The second argument of the function is not specified.");
        }
        return secondArgument;
    }


    /**
     * Parse a string to a number.
     *
     * @return Returns the number of a double type.
     */
    private double parseNumber() throws Exception {
        double result;
        int numberOfDigits = 0;
        int numberOfPoints = 0;
        boolean isNegativeNumber = false;
        // Check or negative number.
        if (formula.charAt(0) == '-') {
            isNegativeNumber = true;
            formula = formula.substring(1, formula.length());
        }
        // Counts how many numbers and dots.
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
            formula = formula.substring(numberOfDigits + numberOfPoints, formula.length());
        } else {
            throw new Exception("Incorrectly written formula. The number entry is invalid.");
        }
        if (isNegativeNumber) {
            result = -result;
        }
        return result;
    }

    /**
     * Checks for the presence of two characters in a row.
     *
     * @return Returns the true if two characters in a row, otherwise - a false.
     */
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
