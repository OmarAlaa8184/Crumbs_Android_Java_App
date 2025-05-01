package com.example.crumbs_.getRandomMeal.model.db;

import androidx.lifecycle.LiveData;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;

import java.util.List;

public interface MealLocalDataSource
{
    void insertMeal(Meal meal);
    void deleteMeal(Meal meal);
    LiveData<List<Meal>> getStoredMeals();
}
