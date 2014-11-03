package com.teamdev.calculator.impl.operations;

import com.teamdev.calculator.impl.Operation;

public abstract class BinaryOperation implements Comparable<BinaryOperation>, Operation {
    static enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    protected abstract Priority getPriority();

    @Override
    public int compareTo(BinaryOperation binaryOperation) {
        final BinaryOperation otherBinaryOperation = binaryOperation;
        return getPriority().compareTo(otherBinaryOperation.getPriority());
    }

}
