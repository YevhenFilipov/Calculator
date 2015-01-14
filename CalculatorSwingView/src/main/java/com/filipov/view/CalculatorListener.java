package com.filipov.view;

import com.filipov.calculator.EvaluationException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class CalculatorListener implements ActionListener {

    CalculatorView parent;

    public CalculatorListener(CalculatorView parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        final String labelOfClickedButton = e.getActionCommand();
        final ActionFactory actionFactory = new ActionFactory();
        final Action currentAction = actionFactory.createAction(labelOfClickedButton);
        String evaluationExpression = parent.getEvaluationExpressionText();

        if (evaluationExpression == null || evaluationExpression.length() == 0) evaluationExpression = "0";

        String result = "0";
        try {
            result = currentAction.execute(evaluationExpression);
        } catch (EvaluationException error) {
            parent.showErrorMessage(error.getMessage(), error.getErrorIndex());
        }

        parent.setResultText(result);

    }
}
