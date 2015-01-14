package com.filipov.calculator.impl.functions;

import com.filipov.calculator.impl.Function;

import java.util.Arrays;

public class SqrtFunction implements Function {

    @Override
    public Double execute(Double... arguments) {
        if (arguments.length > 1) {
            return null;
        }
        final Double argument = Arrays.asList(arguments).get(0);
        return Math.sqrt(argument);
    }
}
