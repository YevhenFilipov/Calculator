package com.teamdev.calculator.impl;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.fsm.StateAcceptor;
import com.teamdev.fsm.StateMachineContext;
import com.teamdev.fsm.TransitionMatrix;

public class EvaluationContext implements StateMachineContext<State, EvaluationContext, EvaluationException> {

    private int lastOpeningBracketIndex = 0;

    private final EvaluationMatrix matrix = new EvaluationMatrix();
    private final EvaluationService evaluationService = new EvaluationService();
    private final EvaluationStack evaluationStack = new EvaluationStack();
    private final OperationFactory operationFactory = new OperationFactory();
    private final MathExpressionReader mathExpressionReader;

    public EvaluationContext(String mathExpression) {
        mathExpressionReader = new MathExpressionReader(mathExpression);
    }

    public MathExpressionReader getMathExpressionReader() {
        return mathExpressionReader;
    }

    public int getLastOpeningBracketIndex() {
        return lastOpeningBracketIndex;
    }

    public void setLastOpeningBracketIndex(int lastOpeningBracketIndex) {
        this.lastOpeningBracketIndex = lastOpeningBracketIndex;
    }

    public EvaluationStack getEvaluationStack() {
        return evaluationStack;
    }

    public OperationFactory getOperationFactory() {
        return operationFactory;
    }

    @Override
    public TransitionMatrix<State> getTransitionMatrix() {
        return matrix;
    }

    @Override
    public StateAcceptor<State, EvaluationContext, EvaluationException> getStateAcceptor() {
        return evaluationService;
    }
}
