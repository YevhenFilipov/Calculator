package com.teamdev.calculator.impl.functions;

import com.teamdev.calculator.impl.SingleArgumentFunction;

public class SqrtFunction implements SingleArgumentFunction {

    @Override
    public boolean isSingleArgumentFunction() {
        return true;
    }

    @Override
    public double execute(Double argument) {
        return Math.sqrt(argument);
    }
}
