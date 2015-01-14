package com.filipov.calculator.impl.functions;

import com.filipov.calculator.impl.Function;

public class SumFunction implements Function {

    @Override
    public Double execute(Double... arguments) {
        Double result = 0d;
        for (Double argument : arguments) {
            result += argument;
        }
        return result;
    }
}
