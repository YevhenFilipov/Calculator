package com.teamdev.view;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.MathExpressionCalculator;
import com.teamdev.calculator.impl.StateMachineCalculator;

public final class CalculateAction implements Action {
    @Override
    public String execute(String mathExpression) throws EvaluationException {

        MathExpressionCalculator mathExpressionCalculator = new StateMachineCalculator();
        Double resultDouble = mathExpressionCalculator.evaluate(mathExpression);
        return resultDouble.toString();
    }
}
