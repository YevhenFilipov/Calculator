package com.teamdev.calculator.impl.functions;

import com.teamdev.calculator.impl.MultipleArgumentFunction;

import java.util.Collection;
import java.util.Iterator;

public final class SumFunction implements MultipleArgumentFunction {

    @Override
    public boolean isSingleArgumentFunction() {
        return false;
    }

    @Override
    public double execute(Collection<Double> arguments) {
        Double result = 0d;
        for (Iterator<Double> currentArgument = arguments.iterator(); currentArgument.hasNext(); ) {
            result += currentArgument.next();
        }
        return result;
    }
}
