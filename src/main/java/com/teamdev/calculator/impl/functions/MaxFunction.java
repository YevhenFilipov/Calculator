package com.teamdev.calculator.impl.functions;

import com.teamdev.calculator.impl.MultipleArgumentFunction;

import java.util.Collection;
import java.util.NavigableSet;
import java.util.TreeSet;

public final class MaxFunction implements MultipleArgumentFunction {

    @Override
    public double execute(Collection<Double> arguments) {
        NavigableSet<Double> resultsSet = new TreeSet<Double>(arguments);
        return resultsSet.last();
    }

    @Override
    public boolean isSingleArgumentFunction() {
        return false;
    }
}
