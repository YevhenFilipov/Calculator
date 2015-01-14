package com.filipov.calculator.impl.operations;

public abstract class BinaryOperation implements Comparable<BinaryOperation> {
    static enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    public abstract double execute(double leftOperand, double rightOperand);

    protected abstract Priority getPriority();

    public abstract boolean isRightAssociatedOperation();

    @Override
    public int compareTo(BinaryOperation binaryOperation) {
        return getPriority().compareTo(binaryOperation.getPriority());
    }

}
