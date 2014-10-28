package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.impl.*;

public class ClosingBracketParser implements MathExpressionParser {
    @Override
    public EvaluationCommand parse(EvaluationContext context) throws EvaluationException{
        if (context.getMathExpression().length() == context.getExpressionParsingIndex())
            return null;
        char currentEvaluatingChar = context.getMathExpression().charAt(context.getExpressionParsingIndex());

        switch (currentEvaluatingChar) {

            case ')':{
                if(context.getEvaluationStack().getOperandStack().size() < 2)
                    throw new EvaluationException("Opening bracket is missing for bracket at position: "
                            + context.getExpressionParsingIndex(),
                            context.getExpressionParsingIndex());
                context.setExpressionParsingIndex(context.getExpressionParsingIndex() + 1);
                return new EvaluationCommand() {
                    @Override
                    public void evaluate(EvaluationStack stack){

                        while (!stack.getOperationStack().peek().isEmpty()){
                            Operation currentOperation = stack.getOperationStack().peek().removeLast();
                            currentOperation.execute(stack);
                        }
                        Double result = stack.getOperandStack().peek().pop();
                        stack.getOperationStack().pop();
                        stack.getOperandStack().pop();
                        stack.getOperandStack().peek().push(result);

                    }
                };
            }
        }

        return null;
    }
}
