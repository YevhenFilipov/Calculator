package com.teamdev.calculator.impl;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.impl.operations.BinaryOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluationStack {

    private static final Logger LOGGER = LoggerFactory.getLogger(EvaluationStack.class);
    private final Deque<Deque<Double>> operandStack = new ArrayDeque<>();
    private final Deque<Deque<BinaryOperation>> operationStack = new ArrayDeque<>();
    private final Deque<Function> functionStack = new ArrayDeque<>();
    private final Deque<Integer> sizeOfOperationStack = new ArrayDeque<>();
    private final Deque<Integer> bracketNumberOfTopFunction = new ArrayDeque<>();
    private int bracketsCount;
    private boolean errorFlag = false;

    public EvaluationStack() {
        operandStack.push(new ArrayDeque<Double>());
        operationStack.push(new ArrayDeque<BinaryOperation>());
    }

    private void pushSizeOfOperationStack() {
        this.sizeOfOperationStack.push(operationStack.peek().size());
    }

    private int popSizeOfOperationStack() {
        return this.sizeOfOperationStack.pop();
    }

    public void pushNumber(Double number) {
        operandStack.peek().push(number);
    }

    public Double popResult() {
        return operandStack.pop().pop();
    }

    public boolean isError() {
        return errorFlag;
    }

    public void executeTopOperator() {

        final Double rightOperand = operandStack.peek().pop();
        final Double leftOperand = operandStack.peek().pop();
        final BinaryOperation currentOperation = operationStack.peek().pop();

        final Double result = currentOperation.execute(leftOperand, rightOperand);
        operandStack.peek().push(result);
        if (LOGGER.isInfoEnabled())
            LOGGER.info("Current executing operation is: " + currentOperation.getClass().getSimpleName());
    }

    public void popAllOperations() {
        while (!operationStack.peek().isEmpty()) {
            executeTopOperator();
        }
    }

    public void pushOperation(BinaryOperation operation) {
        while (!operationStack.peek().isEmpty() &&
                operationStack.peek().peek().compareTo(operation) > -1 &&
                !operation.isRightAssociatedOperation()) {
            executeTopOperator();
        }
        operationStack.peek().push(operation);
    }

    public void pushOpeningBracket() {
        bracketsCount++;
        pushNewEvaluationStackLevel();
    }

    public void pushClosingBracket() throws EvaluationException {

        Double result;
        if (bracketNumberOfTopFunction.isEmpty() ||
                bracketNumberOfTopFunction.peek() != bracketsCount) {
            popAllOperations();
            result = operandStack.pop().pop();

        } else {
            result = executeTopFunction();
        }

        operationStack.pop();
        if (result == null) {
            errorFlag = true;
        } else {
            operandStack.peek().push(result);
        }
        bracketsCount--;
    }

    public boolean isOperationStackHaveBrackets() {
        return operationStack.size() > 1;
    }

    private Double executeTopFunction() {
        evaluateFunctionArgument();
        final Function currentFunction = functionStack.pop();
        final Deque<Double> operands = operandStack.pop();
        final Double[] arguments = operands.toArray(new Double[operands.size()]);

        final Double result = currentFunction.execute(arguments);
        bracketNumberOfTopFunction.pop();
        if (LOGGER.isInfoEnabled())
            LOGGER.info("Current executing function is: " + currentFunction.getClass().getSimpleName());
        return result;
    }


    public void pushFunction(Function function) {
        bracketNumberOfTopFunction.push(bracketsCount + 1);
        pushSizeOfOperationStack();
        functionStack.push(function);
    }

    public boolean isFunctionsAvailable() {
        return functionStack.size() > 0;
    }

    public void pushFunctionComma() {
        evaluateFunctionArgument();
        pushSizeOfOperationStack();
    }

    private void evaluateFunctionArgument() {
        int lastSizeOfOperationStack = popSizeOfOperationStack();
        while (operationStack.peek().size() > lastSizeOfOperationStack) {
            executeTopOperator();
        }
    }

    private void pushNewEvaluationStackLevel() {
        operandStack.push(new ArrayDeque<Double>());
        operationStack.push(new ArrayDeque<BinaryOperation>());
    }
}
