package com.design.factory.decorator;

import java.util.ArrayList;
import java.util.List;

public abstract class GPDecorate implements GPCommunity {
    List<String> list = null;
    protected GPCommunity gpCommunity;

    public GPDecorate(GPCommunity gpCommunity) {
        this.gpCommunity = gpCommunity;
    }

    @Override
    public List<String> column() {
        return this.gpCommunity.column();
    }

    @Override
    public List<String> add() {
        list = this.column();
        return this.list;
    }
}
