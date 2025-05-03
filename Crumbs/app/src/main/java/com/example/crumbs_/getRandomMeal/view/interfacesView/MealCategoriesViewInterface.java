package com.example.crumbs_.getRandomMeal.view.interfacesView;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Category;

import java.util.List;

public interface MealCategoriesViewInterface
{
    void showCategories(List<Category> categories);
    void showCategoriesError(String message);
}
