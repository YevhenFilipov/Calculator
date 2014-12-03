package com.teamdev.calculator.impl.operations;

public final class DivisionBinaryOperation extends BinaryOperation {

    @Override
    protected Priority getPriority() {
        return Priority.MEDIUM;
    }

    @Override
    public boolean isRightAssociatedOperation() {
        return false;
    }

    @Override
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand / rightOperand;

    }
}
