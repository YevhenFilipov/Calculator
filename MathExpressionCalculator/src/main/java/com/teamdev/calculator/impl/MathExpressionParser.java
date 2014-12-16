package com.teamdev.calculator.impl;

import com.teamdev.calculator.EvaluationException;

public interface MathExpressionParser {

    EvaluationCommand parse(EvaluationContext context) throws EvaluationException;
}
