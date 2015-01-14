package com.filipov.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public final class CalculatorView {

    private JTextArea evaluationExpressionArea;
    private final JButton buttonCalculate;
    private final JTextField resultField;

    public CalculatorView() {

        JPanel calculatorContent = new JPanel();
        GridBagLayout borderLayout = new GridBagLayout();
        calculatorContent.setLayout(borderLayout);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        evaluationExpressionArea = new JTextArea(3, 15);
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
        mainFrame.setLocationRelativeTo(null);

        enableGlobalHotKeys();

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

    private HashMap<KeyStroke, String> actionMap = new HashMap<KeyStroke, String>();

    private void enableGlobalHotKeys() {
        KeyStroke key1 = KeyStroke.getKeyStroke('\n');
        actionMap.put(key1, ButtonLabel.CALCULATE.getLabelName());

        final ActionListener actionListener = new CalculatorListener(this);

        KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        kfm.addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                final KeyStroke keyStroke = KeyStroke.getKeyStrokeForEvent(e);
                if (actionMap.containsKey(keyStroke)) {
                    final ActionEvent actionEvent = new ActionEvent(e.getSource(), e.getID(), actionMap.get(keyStroke));
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            actionListener.actionPerformed(actionEvent);
                        }
                    });
                    return true;
                }
                return false;
            }
        });
    }
}
