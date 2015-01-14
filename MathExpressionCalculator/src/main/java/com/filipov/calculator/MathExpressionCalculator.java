package com.filipov.calculator;

public interface MathExpressionCalculator {

    double evaluate(String mathExpression) throws EvaluationException;
}
