package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.impl.EvaluationCommand;
import com.teamdev.calculator.impl.EvaluationContext;
import com.teamdev.calculator.impl.EvaluationStack;
import com.teamdev.calculator.impl.MathExpressionParser;

public class EndOfExpressionParser implements MathExpressionParser {

    @Override
    public EvaluationCommand parse(EvaluationContext context) throws EvaluationException {
        if (context.getExpressionParsingIndex() != context.getMathExpression().length()) {
            return null;
        }
        if (context.getEvaluationStack().getOperandStack().size() > 1)
            throw new EvaluationException("Closing bracket is missing for bracket at position: "
                    + context.getLastOpeningBricketIndex(),
                    context.getLastOpeningBricketIndex());

        return new EvaluationCommand() {
            @Override
            public void evaluate(EvaluationStack stack) {
            }
        };
    }
}
