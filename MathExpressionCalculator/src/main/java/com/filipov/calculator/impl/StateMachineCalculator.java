package com.filipov.calculator.impl;

import com.filipov.calculator.EvaluationException;
import com.filipov.calculator.MathExpressionCalculator;
import com.filipov.fsm.FiniteStateMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StateMachineCalculator extends FiniteStateMachine<State, EvaluationContext, Double, EvaluationException>
        implements MathExpressionCalculator {

    private static final Logger LOGGER = LoggerFactory.getLogger(StateMachineCalculator.class);

    @Override
    public double evaluate(String mathExpression) throws EvaluationException {
        if (LOGGER.isInfoEnabled())
            LOGGER.info("Try to evaluate expression: " + mathExpression);
        return run(new EvaluationContext(mathExpression));
    }

    @Override
    protected void deadlock(EvaluationContext context, State currentState) throws EvaluationException {
        final int errorIndex = context.getMathExpressionReader().getIndex();
        if (LOGGER.isErrorEnabled())
            LOGGER.error("Incorrect expression format at position " + errorIndex);
        throw new EvaluationException("Incorrect expression format at position " + errorIndex, errorIndex);
    }

    @Override
    protected Double finish(EvaluationContext context) {
        final Double result = context.getEvaluationStack().popResult();
        if (LOGGER.isInfoEnabled())
            LOGGER.info("Calculations finished, the result is: " + result.toString());
        return result;
    }

}
