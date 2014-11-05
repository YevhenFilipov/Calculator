package com.teamdev;

import com.teamdev.view.CalculatorView;
import com.teamdev.view.ConsoleView;

public class MainClass {
    public static void main(String[] args) {

        if (args.length > 0 && "console".equals(args[0])) {
            ConsoleView consoleView = new ConsoleView();
            consoleView.consoleCalculate();

        } else new CalculatorView();
    }
}
