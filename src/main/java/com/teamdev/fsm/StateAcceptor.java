package com.teamdev.fsm;

public interface StateAcceptor<State extends Enum,
        Context extends StateMachineContext<State, Context>> {

    boolean acceptState(Context context, State possibleState);
}
