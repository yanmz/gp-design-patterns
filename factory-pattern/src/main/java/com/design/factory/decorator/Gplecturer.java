package com.design.factory.decorator;

import java.util.List;

public class Gplecturer extends GPDecorate {
    public Gplecturer(GPCommunity gpCommunity) {
        super(gpCommunity);
    }

    @Override
    public List<String> add() {
        List<String> add = super.add();
        add.add("作业");
        add.add("题库");
        add.add("成长墙");
        return add;
    }
}
