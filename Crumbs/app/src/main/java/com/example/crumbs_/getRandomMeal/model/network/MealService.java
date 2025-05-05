package com.example.crumbs_.getRandomMeal.model.network;

import com.example.crumbs_.getRandomMeal.model.mealPojo.AreaResponse;
import com.example.crumbs_.getRandomMeal.model.mealPojo.CategoryResponse;
import com.example.crumbs_.getRandomMeal.model.mealPojo.IngredientResponse;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService
{
    @GET("random.php")
    Call<MealResponse> getRandomMeal();

    @GET("categories.php")
    Call<CategoryResponse> getCategories();

    @GET("list.php?i=list")
    Call<IngredientResponse> getAllIngredients();

    @GET("list.php?a=list")
    Call<AreaResponse> getAllAreas();

    @GET("search.php")
    Call<MealResponse> searchMeals(@Query("s") String query);

    @GET("filter.php")
    Call<MealResponse> filterByIngredient(@Query("i") String ingredient);

    @GET("filter.php")
    Call<MealResponse> filterByArea(@Query("a") String area);

    @GET("filter.php")
    Call<MealResponse> filterByCategory(@Query("c") String category);

    @GET("lookup.php")
    Call<MealResponse> getMealById(@Query("i") String id);



}
