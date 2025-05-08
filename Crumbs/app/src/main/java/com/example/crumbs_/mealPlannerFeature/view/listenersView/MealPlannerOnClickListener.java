package com.example.crumbs_.mealPlannerFeature.view.listenersView;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealPlanner;

public interface MealPlannerOnClickListener
{
    void onMealClick(MealPlanner mealPlanner);
    void onYoutubeClick(String youtubeUrl);
    void onFavoriteClick(MealPlanner mealPlanner, boolean isFavorite);
    void onPlannerClick(MealPlanner mealPlanner, boolean isPlanned);

}
