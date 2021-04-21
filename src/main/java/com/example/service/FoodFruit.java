package com.example.service;

public  class FoodFruit implements IFood,IFruit {
 
    @Override
    public void eatDinner(IFoodTime  foodTime) {
        System.out.println("晚餐"+foodTime.getFoodTime()+"点吃土豆炖牛肉");
    }
 
    @Override
    public void dessert() {
        System.out.println("饭后甜点吃点啥呢？葡萄还是草莓");
    }
 
    @Override
    public void feedPet() {
        IFood.feedFood();
    }
}