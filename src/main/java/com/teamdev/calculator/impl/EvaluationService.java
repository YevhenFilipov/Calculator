package com.teamdev.calculator.impl;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.impl.parser.*;
import com.teamdev.fsm.StateAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static com.teamdev.calculator.impl.State.*;

public class EvaluationService implements StateAcceptor<State, EvaluationContext, EvaluationException> {
    static int f = 10;
    Logger logger = LoggerFactory.getLogger(EvaluationService.class);
    private final Map<State, MathExpressionParser> parsers = new HashMap<State, MathExpressionParser>() {{
        put(NUMBER, new NumberParser());
        put(BINARY_OPERATION, new OperationParser());
        put(FINISH, new EndOfExpressionParser());
        put(OPENING_BRACKET, new OpeningBracketParser());
        put(CLOSING_BRACKET, new ClosingBracketParser());
        put(FUNCTION, new FunctionParser());
        put(FUNCTION_CLOSING_BRACKET, new FunctionClosingBracketParser());
        put(FUNCTION_COMMA, new FunctionCommaParser());
    }};


    @Override
    public boolean acceptState(EvaluationContext context, State possibleState) throws EvaluationException {

        final MathExpressionParser parser = parsers.get(possibleState);

        if (parser == null) {
            throw new IllegalStateException("Parser not found for state: " + possibleState);
        }

        context.getMathExpressionReader().skipWhiteSpaces();

        final EvaluationCommand evaluationCommand = parser.parse(context);
        if (evaluationCommand == null) {
            logger.trace("Current state is " + possibleState.toString() + "\n"
                    + "Parsing with " + parser.getClass().getSimpleName()
                    + ": Unsuccessful");
            return false;
        }
        evaluationCommand.evaluate(context.getEvaluationStack());
        logger.info("Current state is " + possibleState.toString());
        return true;
    }
}
