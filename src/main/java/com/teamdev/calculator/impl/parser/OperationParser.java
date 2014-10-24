package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.impl.EvaluationCommand;
import com.teamdev.calculator.impl.EvaluationContext;
import com.teamdev.calculator.impl.EvaluationStack;
import com.teamdev.calculator.impl.MathExpressionParser;
import com.teamdev.calculator.impl.binary_operations.AddOperation;
import com.teamdev.calculator.impl.binary_operations.SubtractionOperation;

public class OperationParser implements MathExpressionParser {
    @Override
    public EvaluationCommand parse(EvaluationContext context) {
        if (context.getMathExpression().length() == context.getExpressionParsingIndex()) return null;
        char currentEvaluatingChar = context.getMathExpression().charAt(context.getExpressionParsingIndex());
        switch (currentEvaluatingChar) {

            case '+': {
                context.setExpressionParsingIndex(context.getExpressionParsingIndex() + 1);
                return new EvaluationCommand() {
                    @Override
                    public void evaluate(EvaluationStack stack) {
                        stack.getOperationStack().push(new AddOperation());
                    }
                };
            }

            case '-': {
                context.setExpressionParsingIndex(context.getExpressionParsingIndex() + 1);
                return new EvaluationCommand() {
                    @Override
                    public void evaluate(EvaluationStack stack) {
                        stack.getOperationStack().push(new SubtractionOperation());
                    }
                };
            }

            default:
                return null;
        }
    }
}
