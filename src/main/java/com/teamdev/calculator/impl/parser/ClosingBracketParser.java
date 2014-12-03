package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ClosingBracketParser implements MathExpressionParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClosingBracketParser.class);

    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        final MathExpressionReader mathExpressionReader = context.getMathExpressionReader();

        if (mathExpressionReader.isEndOfMathExpression())
            return null;

        final String symbolPresentation = MathExpressionSymbols.CLOSING_BRACKET.getSymbolPresentation();
        if (mathExpressionReader.getRemainingMathExpression().startsWith(symbolPresentation)) {
            mathExpressionReader.incrementMathExpressionIndex(symbolPresentation.length());
            return new EvaluationCommand() {
                @Override
                public void evaluate(EvaluationStack stack) throws EvaluationException {

                    if (!stack.isOperationStackHaveBrackets()) {

                        final String message = "Error during evaluating: "
                                + symbolPresentation
                                + ". Opening bracket is missing for bracket at position: "
                                + mathExpressionReader.getIndex();

                        if (LOGGER.isErrorEnabled())
                            LOGGER.error(message);
                        throw new EvaluationException(message, mathExpressionReader.getIndex());
                    }

                    stack.pushClosingBracket();
                }
            };
        }
        return null;
    }
}
