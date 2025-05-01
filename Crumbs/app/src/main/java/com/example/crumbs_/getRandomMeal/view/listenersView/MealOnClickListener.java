package com.example.crumbs_.getRandomMeal.view.listenersView;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;

public interface MealOnClickListener
{
    void onMealClick(Meal meal);
    void onYoutubeClick(String youtubeUrl);
}
