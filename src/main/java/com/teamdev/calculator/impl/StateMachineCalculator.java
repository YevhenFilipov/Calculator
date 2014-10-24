package com.teamdev.calculator.impl;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.MathExpressionCalculator;
import com.teamdev.fsm.FiniteStateMachine;

public class StateMachineCalculator extends FiniteStateMachine<State, EvaluationContext, Double>
        implements MathExpressionCalculator {

    @Override
    public double evaluate(String mathExpression) throws EvaluationException {
        return run(new EvaluationContext(mathExpression));
    }

    @Override
    protected void deadlock(EvaluationContext context, State currentState) {
        throw new IllegalStateException("Deadlock in state " + currentState + " at position " +
                context.getExpressionParsingIndex());
    }

    @Override
    protected Double finish(EvaluationContext context) {
        while (!context.getEvaluationStack().getOperationStack().isEmpty()) {

            Operation currentOperation = context.getEvaluationStack().getOperationStack().removeLast();
            currentOperation.execute(context.getEvaluationStack());
        }
        return context.getEvaluationStack().getOperandStack().pop();
    }

    public static void main(String[] args) throws Exception {
        final StateMachineCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate("1");
        System.out.println("result = " + result);
    }
}
