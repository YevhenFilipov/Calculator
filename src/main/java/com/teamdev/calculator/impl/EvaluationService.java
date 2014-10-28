package com.teamdev.calculator.impl;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.impl.parser.*;
import com.teamdev.fsm.StateAcceptor;

import java.util.HashMap;
import java.util.Map;

import static com.teamdev.calculator.impl.State.*;

public class EvaluationService implements StateAcceptor<State, EvaluationContext> {

    private final Map<State, MathExpressionParser> parsers = new HashMap<State, MathExpressionParser>() {{
        put(NUMBER, new NumberParser());
        put(BINARY_OPERATION, new OperationParser());
        put(FINISH, new EndOfExpressionParser());
        put(OPENING_BRACKET, new OpeningBracketParser());
        put(CLOSING_BRACKET, new ClosingBracketParser());
    }};


    @Override
    public boolean acceptState(EvaluationContext context, State possibleState) throws EvaluationException {

        final MathExpressionParser parser = parsers.get(possibleState);

        if (parser == null) {
            throw new IllegalStateException("Parser not found for state: " + possibleState);
        }

        final EvaluationCommand evaluationCommand = parser.parse(context);
        if (evaluationCommand == null) {
            return false;
        }

        evaluationCommand.evaluate(context.getEvaluationStack());

        return true;
    }
}
