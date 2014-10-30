package com.teamdev.calculator.impl.operations;

public class SubtractionBinaryOperation extends BinaryOperation {

    @Override
    protected Priority getPriority() {
        return Priority.LOW;
    }

    @Override
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand - rightOperand;
    }
}
