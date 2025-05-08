package com.example.crumbs_.mealPlannerFeature.view.interfacesView;

import androidx.lifecycle.LiveData;

import com.example.crumbs_.getRandomMeal.model.mealPojo.MealPlanner;

import java.util.List;

public interface PlannerViewInterface
{
    void showMealPlan(LiveData<List<MealPlanner>> mealPlan);
    void showError(String message);
}
