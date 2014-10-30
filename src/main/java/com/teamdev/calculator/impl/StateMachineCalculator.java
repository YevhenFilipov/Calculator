package com.teamdev.calculator.impl;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.MathExpressionCalculator;
import com.teamdev.fsm.FiniteStateMachine;

public class StateMachineCalculator extends FiniteStateMachine<State, EvaluationContext, Double, EvaluationException>
        implements MathExpressionCalculator {

    @Override
    public double evaluate(String mathExpression) throws EvaluationException {
        return run(new EvaluationContext(mathExpression));
    }

    @Override
    protected void deadlock(EvaluationContext context, State currentState) throws EvaluationException {
        throw new EvaluationException("Incorrect expression format.",
                context.getMathExpressionReader().getIndex());
    }

    @Override
    protected Double finish(EvaluationContext context) {
        return context.getEvaluationStack().popNumber();
    }

    public static void main(String[] args) throws EvaluationException {
        final StateMachineCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate("1+1-(4-(2+3)-3+5");
        System.out.println("result = " + result);
    }
}
