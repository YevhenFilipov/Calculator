package com.teamdev.calculator.impl;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.MathExpressionCalculator;
import com.teamdev.fsm.FiniteStateMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StateMachineCalculator extends FiniteStateMachine<State, EvaluationContext, Double, EvaluationException>
        implements MathExpressionCalculator {

    private final Logger logger = LoggerFactory.getLogger(StateMachineCalculator.class);

    @Override
    public double evaluate(String mathExpression) throws EvaluationException {
        logger.info("Try to evaluate expression: " + mathExpression);
        return run(new EvaluationContext(mathExpression));
    }

    @Override
    protected void deadlock(EvaluationContext context, State currentState) throws EvaluationException {
        throw new EvaluationException("Incorrect expression format.",
                context.getMathExpressionReader().getIndex());
    }

    @Override
    protected Double finish(EvaluationContext context) {
        final Double result = context.getEvaluationStack().popNumber();
        logger.info("Calculations finished, the result is: " + result.toString());
        return result;
    }

//    public static void main(String[] args) throws EvaluationException {
//        final StateMachineCalculator calculator = new StateMachineCalculator();
//        final double result = calculator.evaluate("1+1-(2+3)");
//        System.out.println("result = " + result);
//    }
}
