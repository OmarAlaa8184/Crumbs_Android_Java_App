package com.example.crumbs_.getRandomMeal.model.network;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;

import java.util.List;

public interface MealNetworkCallback
{
     void onSuccessResult(List<Meal> meal);

     void onFailureResult(String errorMsg);
}
