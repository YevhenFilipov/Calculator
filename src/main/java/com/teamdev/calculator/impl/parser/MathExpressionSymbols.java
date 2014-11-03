package com.teamdev.calculator.impl.parser;

public enum MathExpressionSymbols {
    OPENING_BRACKET("("),
    CLOSING_BRACKET(")"),
    FUNCTION_CLOSING_BRACKET("]"),
    FUNCTION_COMMA(";");

    private final String symbolPresentation;

    MathExpressionSymbols(String symbol) {
        this.symbolPresentation = symbol;
    }

    public String getSymbolPresentation() {
        return symbolPresentation;
    }
}
