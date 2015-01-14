package com.filipov.view;

import com.filipov.calculator.EvaluationException;
import com.filipov.calculator.MathExpressionCalculator;
import com.filipov.calculator.impl.StateMachineCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class ConsoleView {

    public void consoleCalculate() {

        String exitCommand = "exit";

        System.out.println("Type math expression and press " + (char) 0x22 + "Enter" + (char) 0x22);
        System.out.println("To exit the program type " + (char) 0x22 + exitCommand + (char) 0x22 + " and press "
                + (char) 0x22 + "Enter" + (char) 0x22);

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String inputString = input.readLine();

                if (inputString.equals(exitCommand)) {
                    input.close();
                    System.exit(0);
                }

                MathExpressionCalculator mathExpressionCalculator = new StateMachineCalculator();
                Double result = mathExpressionCalculator.evaluate(inputString);
                System.out.println(inputString + "=");
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EvaluationException evaluationError) {
            evaluationError.printStackTrace();
        }
    }
}
