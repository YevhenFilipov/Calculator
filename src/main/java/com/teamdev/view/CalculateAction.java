package com.teamdev.view;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.MathExpressionCalculator;
import com.teamdev.calculator.impl.StateMachineCalculator;

public class CalculateAction implements Action {
    @Override
    public String execute(String mathExpression) throws EvaluationException {

        MathExpressionCalculator mathExpressionCalculator = new StateMachineCalculator();
        Double resultDouble = mathExpressionCalculator.evaluate(mathExpression);
        String resultString = resultDouble.toString();
        return resultString;
    }
}
