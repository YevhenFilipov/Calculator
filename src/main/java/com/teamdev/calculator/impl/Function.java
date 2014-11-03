package com.teamdev.calculator.impl;

public interface Function<Argument> {
    boolean isSingleArgumentFunction();

    double execute(Argument argument);
}
