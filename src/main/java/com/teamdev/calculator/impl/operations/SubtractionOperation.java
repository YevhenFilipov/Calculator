package com.teamdev.calculator.impl.operations;

import com.teamdev.calculator.impl.EvaluationStack;
import com.teamdev.calculator.impl.Operation;

public class SubtractionOperation implements Operation {
    @Override
    public void execute(EvaluationStack stack) {

        final double firstNumber = stack.getOperandStack().removeLast();
        final double secondNumber = stack.getOperandStack().removeLast();
        stack.getOperandStack().addLast(firstNumber - secondNumber);

    }
}
