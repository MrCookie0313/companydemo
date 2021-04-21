package com.example.service;

public interface IFruit {
 
    void   dessert();
 
    default void  feedPet(){
        System.out.println("小泰迪不喜欢吃水果");
    }
 
    static   void  feedFood(){
        System.out.println("小泰迪晚晚餐不需要甜点");
    }
 
}