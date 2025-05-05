package com.example.crumbs_.getRandomMeal.model.network;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;

import java.util.List;

public interface SearchNetworkCallback
{
    void onSuccessSearchResult(List<Meal> meal);

    void onFailureSearchResult(String errorMsg);
}
