package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.impl.*;

public class EndOfExpressionParser implements MathExpressionParser {

    @Override
    public EvaluationCommand parse(EvaluationContext context) throws EvaluationException {
        if (context.getExpressionParsingIndex() != context.getMathExpression().length()) {
            return null;
        }
        if (context.getEvaluationStack().getOperandStack().size() > 1)
            throw new EvaluationException("Error during evaluating: "
                    + context.getMathExpression().charAt(context.getLastOpeningBricketIndex())
                    + ". Closing bracket is missing for bracket at position: "
                    + context.getLastOpeningBricketIndex(),
                    context.getLastOpeningBricketIndex());

        return new EvaluationCommand() {
            @Override
            public void evaluate(EvaluationStack stack) {
                while (!stack.getOperationStack().peek().isEmpty()) {
                    Operation currentOperation = stack.getOperationStack().peek().removeLast();
                    currentOperation.execute(stack);
                }
            }
        };
    }
}
