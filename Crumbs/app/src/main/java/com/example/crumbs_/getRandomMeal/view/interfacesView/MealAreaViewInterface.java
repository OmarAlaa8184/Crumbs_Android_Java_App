package com.example.crumbs_.getRandomMeal.view.interfacesView;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Area;
import com.example.crumbs_.getRandomMeal.model.mealPojo.Ingredient;

import java.util.List;

public interface MealAreaViewInterface
{
    void showArea(List<Area> areas);
    void showAreError(String message);
}
