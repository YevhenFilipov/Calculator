package com.filipov.calculator.impl.functions;

import com.google.common.collect.Sets;
import com.filipov.calculator.impl.Function;

import java.util.NavigableSet;
import java.util.TreeSet;

public class MinFunction implements Function {

    @Override
    public Double execute(Double... arguments) {
        NavigableSet<Double> resultsSet = new TreeSet<Double>(Sets.newHashSet(arguments));
        return resultsSet.first();
    }
}
