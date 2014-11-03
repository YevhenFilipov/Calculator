package com.teamdev.view;

import javax.swing.*;
import java.awt.*;

public class CalculatorView {

    private JTextArea evaluationExpressionArea;
    private final JButton buttonCalculate;
    private final JTextField resultField;

    public CalculatorView() {

        JPanel calculatorContent = new JPanel();
        GridBagLayout borderLayout = new GridBagLayout();
        calculatorContent.setLayout(borderLayout);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        evaluationExpressionArea = new JTextArea("", 2, 15);
        evaluationExpressionArea.setLineWrap(true);

        JScrollPane evaluationExpressionField = new JScrollPane(evaluationExpressionArea);

        resultField = new JTextField("0", 15);
        resultField.setHorizontalAlignment(JTextField.RIGHT);
        resultField.setEditable(false);

        buttonCalculate = new JButton(ButtonLabel.CALCULATE.getLabelName());

        JLabel evaluationLabel = new JLabel("Math expression:");
        evaluationLabel.setHorizontalAlignment(JTextField.RIGHT);

        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setHorizontalAlignment(JTextField.RIGHT);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.insets = new Insets(20, 20, 5, 10);
        borderLayout.setConstraints(evaluationLabel, gridBagConstraints);
        calculatorContent.add(evaluationLabel);

        gridBagConstraints.gridx = GridBagConstraints.RELATIVE;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(20, 10, 5, 10);
        borderLayout.setConstraints(evaluationExpressionField, gridBagConstraints);
        calculatorContent.add(evaluationExpressionField);

        gridBagConstraints.gridx = GridBagConstraints.RELATIVE;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(20, 10, 5, 20);
        borderLayout.setConstraints(buttonCalculate, gridBagConstraints);
        calculatorContent.add(buttonCalculate);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(10, 20, 20, 10);
        borderLayout.setConstraints(resultLabel, gridBagConstraints);
        calculatorContent.add(resultLabel);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(10, 10, 20, 10);
        borderLayout.setConstraints(resultField, gridBagConstraints);
        calculatorContent.add(resultField);

        CalculatorListener calculatorListener = new CalculatorListener(this);
        buttonCalculate.addActionListener(calculatorListener);

        JFrame mainFrame = new JFrame("Math expression calculator");
        mainFrame.setContentPane(calculatorContent);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainFrame.pack();
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

    public String getEvaluationExpressionText() {
        return evaluationExpressionArea.getText();
    }

    public void setResultText(String resultText) {
        resultField.setText(resultText);
    }

    public void showErrorMessage(String textErrorMessage, int positionError) {
        evaluationExpressionArea.setCaretPosition(positionError);
        evaluationExpressionArea.grabFocus();
        JOptionPane.showMessageDialog(buttonCalculate, textErrorMessage, "Warning!", JOptionPane.WARNING_MESSAGE);
    }


}
