package com.teamdev.calculator.impl.operations;

public class MultiplyBinaryOperation extends BinaryOperation {

    @Override
    protected Priority getPriority() {
        return Priority.MEDIUM;
    }

    @Override
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand * rightOperand;
    }
}
