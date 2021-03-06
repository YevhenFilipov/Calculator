package com.filipov.calculator.impl.parser;

import com.filipov.calculator.impl.*;
import com.filipov.calculator.impl.operations.BinaryOperation;

public class OperationParser implements MathExpressionParser {
    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        final MathExpressionReader mathExpressionReader = context.getMathExpressionReader();

        if (mathExpressionReader.isEndOfMathExpression()) return null;

        final OperationFactory operationFactory = context.getOperationFactory();
        final String remainingMathExpression = mathExpressionReader.getRemainingMathExpression();

        for (String operationPresentation : operationFactory.getAvailableOperationPresentation()) {
            if (remainingMathExpression.startsWith(operationPresentation)) {
                mathExpressionReader.incrementMathExpressionIndex(operationPresentation.length());
                final BinaryOperation newOperation = operationFactory.createOperation(operationPresentation);
                return new EvaluationCommand() {
                    @Override
                    public void evaluate(EvaluationStack stack) {
                        stack.pushOperation(newOperation);
                    }
                };
            }
        }
        return null;
    }
}
