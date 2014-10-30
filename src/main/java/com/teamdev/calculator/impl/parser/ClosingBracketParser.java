package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.impl.*;

public class ClosingBracketParser implements MathExpressionParser {
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

                    if (!stack.isOperationStackHaveBrackets()) throw new EvaluationException(
                            "Error during evaluating: "
                                    + symbolPresentation
                                    + ". Opening bracket is missing for bracket at position: "
                                    + mathExpressionReader.getIndex(),
                            mathExpressionReader.getIndex());

                    stack.pushClosingBracket();
                }
            };
        }
        return null;
    }
}
