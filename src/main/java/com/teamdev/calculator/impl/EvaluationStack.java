package com.teamdev.calculator.impl;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluationStack {

    private final Deque<Double> operandStack = new ArrayDeque<Double>();
    private final Deque<Operation> OperationStack = new ArrayDeque<Operation>();

    public Deque<Operation> getOperationStack() {
        return OperationStack;
    }

    public Deque<Double> getOperandStack() {
        return operandStack;
    }
}
