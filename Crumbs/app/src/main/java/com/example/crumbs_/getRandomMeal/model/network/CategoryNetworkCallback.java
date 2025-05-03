package com.example.crumbs_.getRandomMeal.model.network;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Category;
import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;

import java.util.List;

public interface CategoryNetworkCallback
{
    void onSuccessResult(List<Category> categories);

    void onFailureResult(String errorMsg);
}
