package com.teamdev.calculator.impl.operations;

public final class MultiplyBinaryOperation extends BinaryOperation {

    @Override
    public boolean isRightAssociatedOperation() {
        return false;
    }

    @Override
    protected Priority getPriority() {
        return Priority.MEDIUM;
    }

    @Override
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand * rightOperand;
    }
}
