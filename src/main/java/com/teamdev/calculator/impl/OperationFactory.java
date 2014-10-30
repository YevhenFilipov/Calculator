package com.teamdev.calculator.impl;

import com.teamdev.calculator.impl.operations.AddBinaryOperation;
import com.teamdev.calculator.impl.operations.BinaryOperation;
import com.teamdev.calculator.impl.operations.SubtractionBinaryOperation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OperationFactory {

    private final Map<String, BinaryOperation> operationCreator = new HashMap<String, BinaryOperation>() {{
        put("+", new AddBinaryOperation());
        put("-", new SubtractionBinaryOperation());
    }};

    public BinaryOperation createOperation(String operationPresentation) {
        return operationCreator.get(operationPresentation);
    }

    public Set<String> getAvailableOperationPresentation() {
        return operationCreator.keySet();
    }

}
