package com.example.crumbs_.getRandomMeal.view.interfacesView;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;

import java.util.List;

public interface HomeViewInterface
{
    public void showData(List<Meal> meal);
    public void showError(String message);
}
