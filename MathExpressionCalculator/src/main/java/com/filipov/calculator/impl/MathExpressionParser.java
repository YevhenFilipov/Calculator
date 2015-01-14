package com.filipov.calculator.impl;

import com.filipov.calculator.EvaluationException;

public interface MathExpressionParser {

    EvaluationCommand parse(EvaluationContext context) throws EvaluationException;
}
