package com.filipov.calculator.impl.operations;

public class PowerBinaryOperation extends BinaryOperation {

    @Override
    protected Priority getPriority() {
        return Priority.HIGH;
    }

    @Override
    public boolean isRightAssociatedOperation() {
        return true;
    }

    @Override
    public double execute(double leftOperand, double rightOperand) {
        return Math.pow(leftOperand, rightOperand);

    }
}
