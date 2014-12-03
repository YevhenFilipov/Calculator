package com.teamdev.calculator.impl;

public final class MathExpressionReader {
    private final String mathExpression;
    private int index = 0;

    public MathExpressionReader(String mathExpression) {
        this.mathExpression = mathExpression;
    }

    public void incrementMathExpressionIndex(int incrementValue) {
        this.index += incrementValue;
    }

    public boolean isEndOfMathExpression() {
        return index >= mathExpression.length();
    }

    public String getRemainingMathExpression() {
        return mathExpression.substring(index);
    }

    public void skipWhiteSpaces() {
        while (!isEndOfMathExpression() && Character.isWhitespace(mathExpression.charAt(index))) {
            incrementMathExpressionIndex(1);
        }
    }

    public void skipEnterCharacters() {
        while (!isEndOfMathExpression() && (mathExpression.charAt(index)) == '\n') {
            incrementMathExpressionIndex(1);
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getMathExpression() {
        return mathExpression;
    }
}
