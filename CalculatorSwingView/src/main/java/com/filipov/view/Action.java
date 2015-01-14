package com.filipov.view;

import com.filipov.calculator.EvaluationException;

public interface Action {
    String execute(String mathExpression) throws EvaluationException;
}
