package com.teamdev.calculator.impl;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.MathExpressionCalculator;
import com.teamdev.fsm.FiniteStateMachine;

public class StateMachineCalculator extends FiniteStateMachine<State, EvaluationContext, Double>
        implements MathExpressionCalculator {

    @Override
    public double evaluate(String mathExpression) throws Exception {
        return run(new EvaluationContext(mathExpression));
    }

    @Override
    protected void deadlock(EvaluationContext context, State currentState) throws EvaluationException {
        throw new EvaluationException("Error after evaluating: "
                + context.getMathExpression().charAt(context.getExpressionParsingIndex() - 1)
                + ". Deadlock in state " + currentState + " at position "
                + context.getExpressionParsingIndex(),
                context.getExpressionParsingIndex() - 1);
    }

    @Override
    protected Double finish(EvaluationContext context) {

        return context.getEvaluationStack().getOperandStack().pop().pop();
    }

    public static void main(String[] args) throws Exception {
        final StateMachineCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate("(1+2)+3");
        System.out.println("result = " + result);
    }
}
