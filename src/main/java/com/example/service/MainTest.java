package com.example.service;

public class MainTest {
    public static void main(String[] args) {

        Formula fa = new Formula() {
            @Override
            public double calculate(int a) {
                return 0;
            }
        };
        double sqrt = fa.sqrt(16);
        System.out.println("sqrt = " + sqrt);
        Formula.feedFood();

    }
}
