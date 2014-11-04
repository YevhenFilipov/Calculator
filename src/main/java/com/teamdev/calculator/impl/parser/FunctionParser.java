package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.impl.*;

public class FunctionParser implements MathExpressionParser {
    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        final MathExpressionReader mathExpressionReader = context.getMathExpressionReader();

        if (mathExpressionReader.isEndOfMathExpression()) return null;

        final FunctionFactory functionFactory = context.getFunctionFactory();
        final String remainingMathExpression = mathExpressionReader.getRemainingMathExpression();

        for (String functionPresentation : functionFactory.getAvailableFunctionPresentation()) {
            if (remainingMathExpression.startsWith(functionPresentation)) {
                context.setLastFunctionIndex(mathExpressionReader.getIndex());
                mathExpressionReader.incrementMathExpressionIndex(functionPresentation.length());
                final Function newFunction = functionFactory.createFunction(functionPresentation);
                //if (!newFunction.isSingleArgumentFunction()) context.setFunctionCommasParsingAvailable(true);
                //else context.setFunctionCommasParsingAvailable(false);
                return new EvaluationCommand() {
                    @Override
                    public void evaluate(EvaluationStack stack) {
                        stack.pushFunction(newFunction);
                    }
                };
            }
        }
        return null;
    }
}
