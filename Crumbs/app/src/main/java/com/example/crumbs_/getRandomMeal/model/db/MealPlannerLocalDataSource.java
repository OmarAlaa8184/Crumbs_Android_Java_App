package com.example.crumbs_.getRandomMeal.model.db;

import androidx.lifecycle.LiveData;

import com.example.crumbs_.getRandomMeal.model.mealPojo.MealPlanner;

import java.util.List;

public interface MealPlannerLocalDataSource
{
    void insert(MealPlanner mealPlan);

    LiveData<List<MealPlanner>> getAllMealPlans();

    void deleteByDate(long date);

    void deleteAll();

    void deleteMeal(MealPlanner mealPlanner);

}
