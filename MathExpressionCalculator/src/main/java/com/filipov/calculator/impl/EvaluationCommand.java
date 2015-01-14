package com.filipov.calculator.impl;

import com.filipov.calculator.EvaluationException;

public interface EvaluationCommand {
    void evaluate(EvaluationStack stack) throws EvaluationException;
}
