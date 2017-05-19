package com.shpp.dbondarenko.Test;

import com.shpp.dbondarenko.Calculator;

/**
 * File: CalculatorTest.java
 * The class that tests the calculator.
 * Created by Dmitro Bondarenko on 19.05.2017.
 */
public class CalculatorTest {
    private final static boolean isCorrectFormula = true;
    private final static boolean isIncorrectFormula = false;
    private final static boolean isVariablesArePresent = false;

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        if (isCorrectFormula) {
            checkTestsWithCorrectFormulas(calculator);
        }
        if (isIncorrectFormula) {
            checkTestsWithIncorrectFormulas(calculator);
        }
        if (isVariablesArePresent) {
            checkTestsWithVariables(calculator);
        }
    }

    private static void checkTestsWithVariables(Calculator calculator) {
        String[][] formulas = new String[][]{
                "sin(a) a=45".split(" "),
                "5*(3+a)-b a=1 b=6".split(" "),
                "pow(a,b) a=3 b=2".split(" "),
                "sqrt(25-16)*m m=3".split(" "),
                "a a=9".split(" "),
                "a+5 a=3".split(" "),
                "1/a a=2".split(" "),
        };
        double[] values = new double[]{
                0.7071067811865475,
                14.0,
                9.0,
                9.0,
                9.0,
                8.0,
                0.5,
        };
        for (int i = 0; i < formulas.length; i++) {
            String variables = null;
            for (int j = 1; j < formulas[i].length; j++) {
                variables = formulas[i][j] + " ";
            }
            try {
                System.out.println((i + 1) + ": " + formulas[i][0] +
                        " " + variables +
                        ": Expected result " +
                        values[i] + ", received " +
                        calculator.calculate(formulas[i]));
            } catch (Exception e) {
                System.out.println((i + 1) + ": " + e.getMessage());
            }
        }
    }

    private static void checkTestsWithIncorrectFormulas(Calculator calculator) {
        String[] formulas = new String[]{
                "sin()",
                "2**2",
                "(2+2",
                "2+2)",
                "1+1q",
                "sqrt(-4)",
                "2/0",
                "sin(90",
                "sin90)",
                "5+a a=",
                "5+a ",
                "5(8+9)",
                "5sin(90)",
                "-4/0..1",
                "-4+.1",
                "-4+1.",
                "tan(90)",
                "log(-10)"
        };
        String value = "Exception";
        for (int i = 0; i < formulas.length; i++) {
            try {
                System.out.println((i + 1) + ": " + formulas[i] +
                        ": Expected result " +
                        value + ", received " +
                        calculator.calculate(new String[]{formulas[i]}));
            } catch (Exception e) {
                System.out.println((i + 1) + ": " + e.getMessage());
            }
        }
    }

    private static void checkTestsWithCorrectFormulas(Calculator calculator) {
        String[] formulas = {
                "5+4",
                "5-4",
                "5*4",
                "5/4",
                "(9-3)*(5+1)/(2/4)",
                "((2-3*2)*sqrt(16))/4",
                "(cos(90)*2)+(sin(90)*2)",
                "sqrt(pow(4,2)+pow(3,2))",
                "cos(45)",
                "cos(90)",
                "sin(180)",
                "tan(45)",
                "tan(-135)",
                "(pow(3.1,2*2-2)+sin(90)*6/2+sqrt(45+45+1))*2",
                "pow(pow(pow(pow(pow(3,2),2),2),2),2)"
        };
        double[] values = {
                9.0,
                1.0,
                20.0,
                1.25,
                72.0,
                -4.0,
                2.0,
                5.0,
                0.7071067811865476,
                0.0,
                0.0,
                1.0,
                -1.0,
                44.298784028338915,
                1853020188851841.0
        };
        for (int i = 0; i < formulas.length; i++) {
            try {
                System.out.println((i + 1) + ": " + formulas[i] +
                        ": Expected result " +
                        values[i] + ", received " +
                        calculator.calculate(new String[]{formulas[i]}));
            } catch (Exception e) {
                System.out.println((i + 1) + ": " + e.getMessage());
            }
        }
    }
}
