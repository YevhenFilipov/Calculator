package com.filipov.view;

import com.filipov.calculator.EvaluationException;
import com.filipov.calculator.MathExpressionCalculator;
import com.filipov.calculator.impl.StateMachineCalculator;

public final class CalculateAction implements Action {
    @Override
    public String execute(String mathExpression) throws EvaluationException {

        MathExpressionCalculator mathExpressionCalculator = new StateMachineCalculator();
        Double resultDouble = mathExpressionCalculator.evaluate(mathExpression);
        return resultDouble.toString();
    }
}
