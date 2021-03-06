package com.filipov.calculator.impl;

import com.filipov.calculator.impl.functions.MaxFunction;
import com.filipov.calculator.impl.functions.MinFunction;
import com.filipov.calculator.impl.functions.SqrtFunction;
import com.filipov.calculator.impl.functions.SumFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FunctionFactory {

    private final Map<String, Function> functionFactoryMap = new HashMap<String, Function>() {{
        put("max", new MaxFunction());
        put("min", new MinFunction());
        put("sqrt", new SqrtFunction());
        put("sum", new SumFunction());
    }};

    public Function createFunction(String functionPresentation) {
        return functionFactoryMap.get(functionPresentation);
    }

    public Set<String> getAvailableFunctionPresentation() {
        return functionFactoryMap.keySet();
    }
}
