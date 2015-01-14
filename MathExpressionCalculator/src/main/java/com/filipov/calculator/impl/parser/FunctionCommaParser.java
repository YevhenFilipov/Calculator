package com.filipov.calculator.impl.parser;

import com.filipov.calculator.EvaluationException;
import com.filipov.calculator.impl.*;

public class FunctionCommaParser implements MathExpressionParser {
    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        final MathExpressionReader mathExpressionReader = context.getMathExpressionReader();

        if (mathExpressionReader.isEndOfMathExpression()) {
            return null;
        }

        final String commaSymbolPresentation = MathExpressionSymbols.FUNCTION_COMMA.getSymbolPresentation();
        final String semicolonSymbolPresentation = MathExpressionSymbols.FUNCTION_SEMICOLON.getSymbolPresentation();

        if (mathExpressionReader.getRemainingMathExpression().startsWith(commaSymbolPresentation) ||
                mathExpressionReader.getRemainingMathExpression().startsWith(semicolonSymbolPresentation)) {

            mathExpressionReader.incrementMathExpressionIndex(commaSymbolPresentation.length());
            return new EvaluationCommand() {
                @Override
                public void evaluate(EvaluationStack stack) throws EvaluationException {

                    stack.pushFunctionComma();
                }
            };
        }
        return null;
    }
}
