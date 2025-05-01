package com.example.crumbs_.getRandomMeal.model.mealPojo;

import androidx.lifecycle.LiveData;

import com.example.crumbs_.getRandomMeal.model.network.MealNetworkCallback;

import java.util.List;

public interface MealRepository
{

    public void getAllMeals(MealNetworkCallback mealNetworkCallback);
    public void insertMeal(Meal meal);
    public void deleteMeal(Meal meal);
    public LiveData<List<Meal>> getStoredMeals();
}
