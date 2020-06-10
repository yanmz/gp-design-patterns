package com.design.factory.stat;

import jdk.nashorn.internal.runtime.Context;

public abstract class State {

    protected Context context;

    public State(Context context) {
        this.context = context;
    }

    public abstract  void handle();
}
