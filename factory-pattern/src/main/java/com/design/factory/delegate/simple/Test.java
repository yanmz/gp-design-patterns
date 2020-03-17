package com.design.factory.delegate.simple;

public class Test {
    public static void main(String[] args) {
        new Boss().command("爬虫",new Leader());

        new Boss().command("设计",new Leader());
    }
}
