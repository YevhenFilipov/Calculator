package com.teamdev.calculator.impl;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluationStack {

    private final Deque<Deque<Double>> operandStack = new ArrayDeque<Deque<Double>>();
    private final Deque<Deque<Operation>> operationStack = new ArrayDeque<Deque<Operation>>();

    public EvaluationStack() {
        operandStack.push(new ArrayDeque<Double>());
        operationStack.push(new ArrayDeque<Operation>());
    }

    public Deque<Deque<Operation>> getOperationStack() {
        return operationStack;
    }

    public Deque<Deque<Double>> getOperandStack() {
        return operandStack;
    }
}
