package com.teamdev.calculator.impl.operations;

public class AddBinaryOperation extends BinaryOperation {

    @Override
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand + rightOperand;
    }

    @Override
    protected Priority getPriority() {
        return Priority.LOW;
    }
}
