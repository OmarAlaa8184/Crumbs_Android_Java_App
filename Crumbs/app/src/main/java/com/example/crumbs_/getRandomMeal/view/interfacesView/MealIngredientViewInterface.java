package com.example.crumbs_.getRandomMeal.view.interfacesView;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Category;
import com.example.crumbs_.getRandomMeal.model.mealPojo.Ingredient;

import java.util.List;

public interface MealIngredientViewInterface
{
    void showIngredient(List<Ingredient> ingredients);
    void showIngredientError(String message);
}
