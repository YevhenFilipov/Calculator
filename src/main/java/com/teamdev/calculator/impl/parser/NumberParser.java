package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.impl.EvaluationCommand;
import com.teamdev.calculator.impl.EvaluationContext;
import com.teamdev.calculator.impl.EvaluationStack;
import com.teamdev.calculator.impl.MathExpressionParser;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

public class NumberParser implements MathExpressionParser {

    private final NumberFormat numberFormat = DecimalFormat.getNumberInstance(Locale.US);

    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        final String mathExpression = context.getMathExpression();
        final int index = context.getExpressionParsingIndex();

        final ParsePosition parsePosition = new ParsePosition(index);
        final Number number = numberFormat.parse(mathExpression, parsePosition);
        if (parsePosition.getErrorIndex() != -1) {
            return null;
        }

        context.setExpressionParsingIndex(parsePosition.getIndex());

        return new EvaluationCommand() {
            @Override
            public void evaluate(EvaluationStack stack) {
                stack.getOperandStack().push(number.doubleValue());
            }
        };
    }
}
