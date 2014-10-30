package com.teamdev.calculator.impl;

import com.teamdev.calculator.EvaluationException;

public interface EvaluationCommand {
    void evaluate(EvaluationStack stack) throws EvaluationException;
}
