package com.filipov.calculator.impl.parser;

import com.filipov.calculator.impl.*;

public class OpeningBracketParser implements MathExpressionParser {
    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        MathExpressionReader mathExpressionReader = context.getMathExpressionReader();

        if (mathExpressionReader.isEndOfMathExpression())
            return null;

        final String symbolPresentation = MathExpressionSymbols.OPENING_BRACKET.getSymbolPresentation();
        if (mathExpressionReader.getRemainingMathExpression().startsWith(symbolPresentation)) {
            context.setLastOpeningBracketIndex(mathExpressionReader.getIndex());
            mathExpressionReader.incrementMathExpressionIndex(symbolPresentation.length());
            return new EvaluationCommand() {
                @Override
                public void evaluate(EvaluationStack stack) {
                    stack.pushOpeningBracket();
                }
            };
        }
        return null;
    }
}
