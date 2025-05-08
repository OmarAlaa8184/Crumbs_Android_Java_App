package com.example.crumbs_.getRandomMeal.model.network;

public interface MealRemoteDataSource
{
    void makeNetworkCall(MealNetworkCallback mealNetworkCallback);

    void makeCategoryNetworkCall(CategoryNetworkCallback categoryNetworkCallback);

    void makeIngredientNetworkCall(IngredientNetworkCallback ingredientNetworkCallback);

    void makeAreaNetworkCallback(AreaNetworkCallback areaNetworkCallback);

    void makeSearchMealsNetworkCallback(String query, SearchNetworkCallback searchNetworkCallback);
    void makeFilterByIngredientNetworkCallback(String ingredient, SearchNetworkCallback searchNetworkCallback);

    void makeFilterByAreaNetworkCallback(String area, SearchNetworkCallback searchNetworkCallback);

    void makeFilterByCategoryNetworkCallback(String category, SearchNetworkCallback searchNetworkCallback);

   void makeMealPlannerNetworkCallback(MealPlannerNetworkCallback mealPlannerNetworkCallback);


}
