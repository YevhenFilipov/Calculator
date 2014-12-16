package com.teamdev.calculator.impl.functions;

import com.google.common.collect.Sets;
import com.teamdev.calculator.impl.Function;

import java.util.NavigableSet;
import java.util.TreeSet;

public class MaxFunction implements Function {

    @Override
    public Double execute(Double... arguments) {
        NavigableSet<Double> resultsSet = new TreeSet<Double>(Sets.newHashSet(arguments));
        return resultsSet.last();
    }
}
