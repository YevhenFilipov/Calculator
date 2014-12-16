package com.teamdev.calculator.impl;

public enum State {
    START,
    NUMBER,
    BINARY_OPERATION,
    OPENING_BRACKET,
    CLOSING_BRACKET,
    FUNCTION,
    FUNCTION_COMMA,
    FINISH
}