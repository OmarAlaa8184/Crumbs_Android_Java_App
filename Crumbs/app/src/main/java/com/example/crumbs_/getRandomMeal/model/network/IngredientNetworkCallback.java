package com.example.crumbs_.getRandomMeal.model.network;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Category;
import com.example.crumbs_.getRandomMeal.model.mealPojo.Ingredient;

import java.util.List;

public interface IngredientNetworkCallback
{
    void onSuccessResult(List<Ingredient> ingredients);

    void onFailureResult(String errorMsg);
}
