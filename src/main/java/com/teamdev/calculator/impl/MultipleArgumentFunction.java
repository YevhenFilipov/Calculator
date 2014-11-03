package com.teamdev.calculator.impl;

import java.util.Collection;

public interface MultipleArgumentFunction extends Function<Collection<Double>> {
    double execute(Collection<Double> arguments);
}
