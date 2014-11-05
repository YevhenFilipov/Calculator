package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.impl.*;

public class EndOfExpressionParser implements MathExpressionParser {

    @Override
    public EvaluationCommand parse(final EvaluationContext context) throws EvaluationException {
        final MathExpressionReader mathExpressionReader = context.getMathExpressionReader();
        if (!mathExpressionReader.isEndOfMathExpression()) {
            return null;
        }
        return new EvaluationCommand() {
            @Override
            public void evaluate(EvaluationStack stack) throws EvaluationException {

                if (stack.isOperationStackHaveBrackets() &&
                    !stack.isFunctionsAvailable()) throw new EvaluationException(
                        "Closing bracket is missing for opening bracket, perhaps, at position: "
                                + context.getLastOpeningBracketIndex(),
                        context.getLastOpeningBracketIndex());
                if (stack.isFunctionsAvailable()) throw new EvaluationException(
                        "Function's closing bracket is missing for function, perhaps, at position: "
                                + context.getLastFunctionIndex(),
                        context.getLastFunctionIndex());

                stack.popAllOperations();
            }
        };
    }
}

