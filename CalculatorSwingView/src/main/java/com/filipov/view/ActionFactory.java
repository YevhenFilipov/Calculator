package com.filipov.view;

import java.util.HashMap;
import java.util.Map;

public final class ActionFactory {

    private final Map<String, Action> actionMap = new HashMap<String, Action>() {{
        put(ButtonLabel.CALCULATE.getLabelName(), new CalculateAction());
    }};

    public Action createAction(String labelName) {
        return actionMap.get(labelName);
    }
}
