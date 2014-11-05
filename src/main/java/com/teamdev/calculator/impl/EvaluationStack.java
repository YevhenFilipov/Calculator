package com.teamdev.calculator.impl;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.impl.operations.BinaryOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluationStack {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Deque<Deque<Double>> operandStack = new ArrayDeque<Deque<Double>>();
    private final Deque<Deque<BinaryOperation>> operationStack = new ArrayDeque<Deque<BinaryOperation>>();
    private final Deque<Function> functionStack = new ArrayDeque<Function>();
    private final Deque<Integer> sizeOfOperationStack = new ArrayDeque<Integer>();

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

    public Double popNumber() {
        return operandStack.pop().pop();
    }

    public void executeTopOperator() {

        final Double rightOperand = operandStack.peek().pop();
        final Double leftOperand = operandStack.peek().pop();
        final BinaryOperation currentOperation = operationStack.peek().pop();

        final Double result = currentOperation.execute(leftOperand, rightOperand);
        operandStack.peek().push(result);
        logger.info("Current executing operation is: " + currentOperation.getClass().getSimpleName());
    }

    public void popAllOperations() {
        while (!operationStack.peek().isEmpty()) {
            executeTopOperator();
        }
    }

//    public void pushOperation(BinaryOperation operation) {
//        while (!operationStack.peek().isEmpty() &&
//               operationStack.peek().peek().compareTo(operation) > -1 &&
//               !operationStack.peek().peek().isRightAssociatedOperation()) {
//            executeTopOperator();
//        }
//        operationStack.peek().push(operation);
//    }

    public void pushOperation(BinaryOperation operation) {
        while (!operationStack.peek().isEmpty() &&
                operationStack.peek().peek().compareTo(operation) > -1 &&
                !operation.isRightAssociatedOperation()) {
            executeTopOperator();
        }
        operationStack.peek().push(operation);
    }

    public void pushOpeningBracket() {
        pushNewEvaluationStackLevel();
    }

    public void pushClosingBracket() throws EvaluationException {
        popAllOperations();
        operationStack.pop();
        Double result = operandStack.pop().pop();
        operandStack.peek().push(result);
    }

    public boolean isOperationStackHaveBrackets() {
        return operationStack.size() > 1;
    }

    public boolean isFunctionCommasParsingAvailable() {

        return !functionStack.isEmpty() && !functionStack.peek().isSingleArgumentFunction();
    }

    public void executeFunction() {
        evaluateFunctionArgument();
        final Function function = functionStack.peek();
        if (function.isSingleArgumentFunction()) {
            executeSingleArgumentFunction();
        } else {
            executeMultipleArgumentFunction();
        }
        logger.info("Current executing function is: " + function.getClass().getSimpleName());
    }

    private void executeSingleArgumentFunction() {

        final Function currentFunction = functionStack.pop();
        final double result = currentFunction.execute(operandStack.pop().pop());
        operationStack.pop();
        operandStack.peek().push(result);
    }

    private void executeMultipleArgumentFunction() {
        final Function currentFunction = functionStack.pop();
        final double result = currentFunction.execute(operandStack.pop());
        operationStack.pop();
        operandStack.peek().push(result);

    }

    public void pushFunction(Function function) {
        pushNewEvaluationStackLevel();
        pushSizeOfOperationStack();
        functionStack.push(function);
    }

    public boolean isFunctionsAvailable() {
        return functionStack.size() > 0;
    }

    public void putFunctionComma() {
        evaluateFunctionArgument();
        pushSizeOfOperationStack();
    }

    private void evaluateFunctionArgument() {
        int lastSizeOfOperationStack = popSizeOfOperationStack();
        while (!(operationStack.peek().size() == lastSizeOfOperationStack)) {
            executeTopOperator();
        }
    }

    private void pushNewEvaluationStackLevel() {
        operandStack.push(new ArrayDeque<Double>());
        operationStack.push(new ArrayDeque<BinaryOperation>());
    }
}
