package com.example.crumbs_.getRandomMeal.model.network;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealPlanner;

import java.util.List;

public interface MealPlannerNetworkCallback
{
    void onSuccessResult(List<MealPlanner> meal);

    void onFailureResult(String errorMsg);
}
