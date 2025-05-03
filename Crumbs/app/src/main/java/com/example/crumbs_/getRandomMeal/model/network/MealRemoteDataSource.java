package com.example.crumbs_.getRandomMeal.model.network;

public interface MealRemoteDataSource
{
    void makeNetworkCall(MealNetworkCallback mealNetworkCallback);

    void makeCategoryNetworkCall(CategoryNetworkCallback categoryNetworkCallback);

    void makeIngredientNetworkCall(IngredientNetworkCallback ingredientNetworkCallback);


}
