package com.example.crumbs_.getRandomMeal.model.mealPojo;

import androidx.lifecycle.LiveData;

import com.example.crumbs_.getRandomMeal.model.network.AreaNetworkCallback;
import com.example.crumbs_.getRandomMeal.model.network.CategoryNetworkCallback;
import com.example.crumbs_.getRandomMeal.model.network.IngredientNetworkCallback;
import com.example.crumbs_.getRandomMeal.model.network.MealNetworkCallback;
import com.example.crumbs_.getRandomMeal.model.network.SearchNetworkCallback;

import java.util.List;

public interface MealRepository
{

    public void getAllMeals(MealNetworkCallback mealNetworkCallback);

    public void getAllCategories(CategoryNetworkCallback categoryNetworkCallback);

    public  void getAllIngredients(IngredientNetworkCallback ingredientNetworkCallback);

    public  void getAllAreas(AreaNetworkCallback areaNetworkCallback);

    void searchMeals(String query, SearchNetworkCallback searchNetworkCallback);
    void filterByIngredient(String ingredient, SearchNetworkCallback searchNetworkCallback);
    void filterByArea(String area, SearchNetworkCallback searchNetworkCallback);
    void filterByCategory(String category, SearchNetworkCallback searchNetworkCallback);

    public void insertMeal(Meal meal);

    public void deleteMeal(Meal meal);

    public LiveData<List<Meal>> getStoredMeals();
}
