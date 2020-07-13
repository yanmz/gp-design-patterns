package com.design.factory.decorator;

import java.util.ArrayList;
import java.util.List;

public class GPOrdinaryLogin implements GPCommunity {

    @Override
    public List<String> column() {
        List<String> list = new ArrayList<>();
        list.add("问答");
        list.add("文章");
        list.add("精品课");
        list.add("冒泡");
        list.add("商城");
        return list;
    }
}
