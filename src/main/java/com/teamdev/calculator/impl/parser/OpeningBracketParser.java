package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.impl.*;

import java.util.ArrayDeque;

public class OpeningBracketParser implements MathExpressionParser{
    @Override
    public EvaluationCommand parse(EvaluationContext context) {
        if (context.getMathExpression().length() == context.getExpressionParsingIndex())
            return null;
        char currentEvaluatingChar = context.getMathExpression().charAt(context.getExpressionParsingIndex());
        switch (currentEvaluatingChar) {
            case '(':{
                context.setLastOpeningBricketIndex(context.getExpressionParsingIndex());
                context.setExpressionParsingIndex(context.getExpressionParsingIndex() + 1);
                return new EvaluationCommand() {
                    @Override
                    public void evaluate(EvaluationStack stack) {
                        stack.getOperandStack().push(new ArrayDeque<Double>());
                        stack.getOperationStack().push(new ArrayDeque<Operation>());
                    }
                };
            }
        }

        return null;
    }
}
