package com.example.service;

interface Formula {

    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }

    static void feedFood() {
        System.out.println("小泰迪晚上要吃肉");
    }


}