package com.teamdev;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.MathExpressionCalculator;
import com.teamdev.calculator.impl.StateMachineCalculator;
import com.teamdev.view.CalculatorView;

public class MainClass {
    public static void main(String[] args) {

        if (args.length > 0 && "calculate".equals(args[0])) {
            String mathExpression = args[1];
            MathExpressionCalculator mathExpressionCalculator = new StateMachineCalculator();
            try {
                Double result = mathExpressionCalculator.evaluate(mathExpression);
                System.out.println(mathExpression);
                System.out.println("result = " + result.toString());
            } catch (EvaluationException e) {
                System.out.println(e.getMessage());
                System.out.println("at position " + e.getErrorIndex());
                e.printStackTrace();
            }
        } else new CalculatorView();
    }
}
