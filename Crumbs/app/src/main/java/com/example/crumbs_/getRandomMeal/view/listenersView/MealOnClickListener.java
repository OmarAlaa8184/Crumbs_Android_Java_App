package com.example.crumbs_.getRandomMeal.view.listenersView;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealPlanner;

public interface MealOnClickListener
{
    void onMealClick(Meal meal);
    void onYoutubeClick(String youtubeUrl);
    void onFavoriteClick(Meal meal, boolean isFavorite);
    void onFavoriteClick1(Meal meal);
    void onPlannerClick(Meal meal, boolean isPlanned);


}
