package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunctionClosingBracketParser implements MathExpressionParser {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        final MathExpressionReader mathExpressionReader = context.getMathExpressionReader();

        if (mathExpressionReader.isEndOfMathExpression())
            return null;

        final String symbolPresentation = MathExpressionSymbols.FUNCTION_CLOSING_BRACKET.getSymbolPresentation();
        if (mathExpressionReader.getRemainingMathExpression().startsWith(symbolPresentation)) {
            mathExpressionReader.incrementMathExpressionIndex(symbolPresentation.length());
            return new EvaluationCommand() {
                @Override
                public void evaluate(EvaluationStack stack) throws EvaluationException {

                    if (!stack.isFunctionsAvailable()) {
                        final String ErrorMessage = "Error during evaluating: "
                                + symbolPresentation
                                + ". Function is missing for function's closing bracket at position: "
                                + mathExpressionReader.getIndex();

                        logger.error(ErrorMessage);
                        throw new EvaluationException(ErrorMessage, mathExpressionReader.getIndex());
                    }
                    stack.executeFunction();
                }
            };
        }
        return null;
    }
}
