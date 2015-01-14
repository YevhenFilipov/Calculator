package com.filipov;

import com.filipov.view.CalculatorView;
import com.filipov.view.ConsoleView;

public class CalculatorMain {
    public static void main(String[] args) {

        if (args.length > 0 && "console".equals(args[0])) {
            ConsoleView consoleView = new ConsoleView();
            consoleView.consoleCalculate();
        } else new CalculatorView();
    }
}
