package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.impl.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

public final class NumberParser implements MathExpressionParser {

    private final NumberFormat numberFormat = DecimalFormat.getNumberInstance(Locale.US);

    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        numberFormat.setGroupingUsed(false);
        final MathExpressionReader mathExpressionReader = context.getMathExpressionReader();
        final String mathExpression = mathExpressionReader.getMathExpression();
        final int index = mathExpressionReader.getIndex();

        final ParsePosition parsePosition = new ParsePosition(index);
        final Number number = numberFormat.parse(mathExpression, parsePosition);
        if (parsePosition.getErrorIndex() != -1) {
            return null;
        }
        mathExpressionReader.setIndex(parsePosition.getIndex());

        return new EvaluationCommand() {
            @Override
            public void evaluate(EvaluationStack stack) {
                stack.pushNumber(number.doubleValue());
            }
        };
    }
}
