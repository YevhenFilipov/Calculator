package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.impl.*;

public class FunctionClosingBracketParser implements MathExpressionParser {
    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        final MathExpressionReader mathExpressionReader = context.getMathExpressionReader();

        if (mathExpressionReader.isEndOfMathExpression())
            return null;

        final String symbolPresentation = MathExpressionSymbols.FUNCTION_CLOSING_BRACKET.getSymbolPresentation();
        if (mathExpressionReader.getRemainingMathExpression().startsWith(symbolPresentation)) {
            mathExpressionReader.incrementMathExpressionIndex(symbolPresentation.length());
            context.popFromFunctionCommasParsingAvailableQuene();
            return new EvaluationCommand() {
                @Override
                public void evaluate(EvaluationStack stack) throws EvaluationException {

                    if (!stack.isFunctionsAvailable()) throw new EvaluationException(
                            "Error during evaluating: "
                                    + symbolPresentation
                                    + ". Function is missing for function's closing bracket at position: "
                                    + mathExpressionReader.getIndex(),
                            mathExpressionReader.getIndex());

                    stack.executeFunction();
                }
            };
        }
        return null;
    }
}
