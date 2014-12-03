package com.teamdev.calculator.impl.operations;

public final class SubtractionBinaryOperation extends BinaryOperation {

    @Override
    protected Priority getPriority() {
        return Priority.LOW;
    }

    @Override
    public boolean isRightAssociatedOperation() {
        return false;
    }

    @Override
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand - rightOperand;
    }
}
