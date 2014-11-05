package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndOfExpressionParser implements MathExpressionParser {

    private final Logger logger = LoggerFactory.getLogger(EndOfExpressionParser.class);

    @Override
    public EvaluationCommand parse(final EvaluationContext context) throws EvaluationException {
        final MathExpressionReader mathExpressionReader = context.getMathExpressionReader();
        if (!mathExpressionReader.isEndOfMathExpression()) {
            return null;
        }
        return new EvaluationCommand() {
            @Override
            public void evaluate(EvaluationStack stack) throws EvaluationException {

                if (stack.isOperationStackHaveBrackets() && !stack.isFunctionsAvailable()) {

                    final String errorMessage = "Closing bracket is missing for opening bracket, perhaps, at position: "
                            + context.getLastOpeningBracketIndex();

                    logger.error(errorMessage);
                    throw new EvaluationException(errorMessage, context.getLastOpeningBracketIndex());
                }
                if (stack.isFunctionsAvailable()) {
                    final String errorMessage = "Function's closing bracket is missing for function, perhaps, at position: "
                            + context.getLastFunctionIndex();

                    logger.error(errorMessage);
                    throw new EvaluationException(errorMessage, context.getLastFunctionIndex());
                }

                stack.popAllOperations();
            }
        };
    }
}

