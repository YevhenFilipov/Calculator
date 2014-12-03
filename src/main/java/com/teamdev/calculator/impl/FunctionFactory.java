package com.teamdev.calculator.impl;

import com.teamdev.calculator.impl.functions.MaxFunction;
import com.teamdev.calculator.impl.functions.MinFunction;
import com.teamdev.calculator.impl.functions.SqrtFunction;
import com.teamdev.calculator.impl.functions.SumFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class FunctionFactory {

    private final Map<String, Function> functionFactoryMap = new HashMap<String, Function>() {{
        put("max[", new MaxFunction());
        put("min[", new MinFunction());
        put("sqrt[", new SqrtFunction());
        put("sum[", new SumFunction());
    }};

    public Function createFunction(String functionPresentation) {
        return functionFactoryMap.get(functionPresentation);
    }

    public Set<String> getAvailableFunctionPresentation() {
        return functionFactoryMap.keySet();
    }
}
