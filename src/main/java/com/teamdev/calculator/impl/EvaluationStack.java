package com.teamdev.calculator.impl;

import com.teamdev.calculator.impl.operations.BinaryOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluationStack {

    private final Logger logger = LoggerFactory.getLogger(EvaluationStack.class);
    private final Deque<Deque<Double>> operandStack = new ArrayDeque<Deque<Double>>();
    private final Deque<Deque<BinaryOperation>> operationStack = new ArrayDeque<Deque<BinaryOperation>>();

    public EvaluationStack() {
        operandStack.push(new ArrayDeque<Double>());
        operationStack.push(new ArrayDeque<BinaryOperation>());
    }

    public void pushNumber(Double number) {
        operandStack.peek().push(number);
    }

    public Double popNumber() {
        return operandStack.pop().pop();
    }

    public void executeTopOperator() {

        final Double rightOperand = operandStack.peek().pop();
        final Double leftOperand = operandStack.peek().pop();
        final BinaryOperation currentOperation = operationStack.peek().pop();

        final Double result = currentOperation.execute(leftOperand, rightOperand);
        operandStack.peek().push(result);
        logger.info("Current executing operation is: " + currentOperation.getClass().getSimpleName());
    }

    public void popAllOperations() {
        while (!operationStack.peek().isEmpty()) {
            executeTopOperator();
        }
    }

    public void pushOperation(BinaryOperation operation) {
        while (!operationStack.peek().isEmpty() && operationStack.peek().peek().compareTo(operation) > -1) {
            executeTopOperator();
        }
        operationStack.peek().push(operation);
    }

    public void pushOpeningBracket() {
        operandStack.push(new ArrayDeque<Double>());
        operationStack.push(new ArrayDeque<BinaryOperation>());
    }

    public void pushClosingBracket() {
        popAllOperations();
        operationStack.pop();
        Double result = operandStack.pop().pop();
        operandStack.peek().push(result);
    }

    public boolean isOperationStackHaveBrackets() {
        return operationStack.size() > 1;
    }
}
