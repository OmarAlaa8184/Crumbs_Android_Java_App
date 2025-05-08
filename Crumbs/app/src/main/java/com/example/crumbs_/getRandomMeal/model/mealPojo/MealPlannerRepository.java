package com.example.crumbs_.getRandomMeal.model.mealPojo;

import androidx.lifecycle.LiveData;

import com.example.crumbs_.getRandomMeal.model.network.MealNetworkCallback;
import com.example.crumbs_.getRandomMeal.model.network.MealPlannerNetworkCallback;

import java.util.List;

public interface MealPlannerRepository
{
    public void getAllMeals(MealPlannerNetworkCallback mealPlannerNetworkCallback);
    void insert(MealPlanner mealPlan);

    LiveData<List<MealPlanner>> getAllMealPlans();

    void deleteByDate(long date);

    void deleteAll();

    void deletePlannedMeal(MealPlanner mealPlanner);
}
