package com.teamdev.view;

public enum ButtonLabel {
    CALCULATE("Calculate");

    private final String labelName;

    ButtonLabel(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelName() {
        return this.labelName;
    }
}
