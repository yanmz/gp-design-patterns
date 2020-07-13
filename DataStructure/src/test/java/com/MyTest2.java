package com;

public class MyTest2 {
    public static void main(String[] args) {
        int[] str = new int[]{1, 29, 10, 11, 298, 120, 12, 9, 5};
        for (int i = 0; i < str.length - 1; i++) {
            for (int j = 0; j < str.length - i - 1; j++) {
                if (str[j] > str[j + 1]) {
                    int temp = str[j + 1];
                    str[j + 1] = str[j];
                    str[j] = temp;
                }
            }
        }
        for (int k = 0; k < str.length; k++) {
            System.out.print(str[k] + " ");
        }
    }
}
