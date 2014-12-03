package com.teamdev.calculator.impl;

import com.teamdev.calculator.impl.operations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class OperationFactory {

    private final Map<String, BinaryOperation> operationCreator = new HashMap<String, BinaryOperation>() {{
        put("+", new AddBinaryOperation());
        put("-", new SubtractionBinaryOperation());
        put("*", new MultiplyBinaryOperation());
        put("/", new DivisionBinaryOperation());
        put("^", new PowerBinaryOperation());
    }};

    public BinaryOperation createOperation(String operationPresentation) {
        return operationCreator.get(operationPresentation);
    }

    public Set<String> getAvailableOperationPresentation() {
        return operationCreator.keySet();
    }

}
