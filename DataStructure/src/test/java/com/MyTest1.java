package com;


public class MyTest1 {
    public static void main(String[] args) {
        String str = "1dsd大红灯笼圣诞节十多年@#￥%&（*&%￥%&￥%&（*&%￥%&￥%&（*&%￥%&";
        Character[] test = test(str);
        Character[] characters = test1(str);
        String s = "";
        for (int i = 0; i < test.length; i++) {
            s += test[i].toString();
        }
        for (int i = 0; i < characters.length; i++) {
            s += characters[i].toString();
        }
        System.out.println(s);
    }

    public static Character[] test(String str) {
        Character[] characters = new Character[10];
        for (int i = 0; i < characters.length; i++) {
            char c = str.charAt(i);
            characters[i] = c;
        }
        return characters;
    }

    public static Character[] test1(String str) {
        Character[] characters = new Character[20];
        for (int i = 0; i < characters.length; i++) {
            char c = str.charAt(str.length() - 1 - i);
            characters[i] = c;
        }
        return characters;
    }
}
