package com.example.service;

public interface IFood {
 
     void  eatDinner(IFoodTime  foodTime);
 
     default  void   feedPet(){
         System.out.println("记得给小泰迪吃晚餐");
     }
 
     static   void   feedFood(){
         System.out.println("小泰迪晚上要吃肉");
     }
 
}
 
 
 

 
