package com.teamdev.calculator.impl.operations;

public final class AddBinaryOperation extends BinaryOperation {

    @Override
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand + rightOperand;
    }

    @Override
    public boolean isRightAssociatedOperation() {
        return false;
    }

    @Override
    protected Priority getPriority() {
        return Priority.LOW;
    }
}
