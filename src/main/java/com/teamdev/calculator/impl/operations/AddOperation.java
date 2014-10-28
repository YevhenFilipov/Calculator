package com.teamdev.calculator.impl.operations;

import com.teamdev.calculator.impl.EvaluationStack;
import com.teamdev.calculator.impl.Operation;

public class AddOperation implements Operation {
    @Override
    public void execute(EvaluationStack stack) {
        final double firstNumber = stack.getOperandStack().peek().removeLast();
        final double secondNumber = stack.getOperandStack().peek().removeLast();
        stack.getOperandStack().peek().addLast(firstNumber + secondNumber);
    }
}
