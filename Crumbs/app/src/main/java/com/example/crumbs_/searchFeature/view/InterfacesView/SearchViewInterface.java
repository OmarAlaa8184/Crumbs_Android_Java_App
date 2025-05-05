package com.example.crumbs_.searchFeature.view.InterfacesView;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;

import java.util.List;

public interface SearchViewInterface
{
    void showMeals(List<Meal> meals);
    void showError(String message);

}
