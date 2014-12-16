package com.teamdev.view;

import com.teamdev.calculator.EvaluationException;

public interface Action {
    String execute(String mathExpression) throws EvaluationException;
}
