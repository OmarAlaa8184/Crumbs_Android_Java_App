package com.example.crumbs_.getRandomMeal.model.network;

import com.example.crumbs_.getRandomMeal.model.mealPojo.AreaResponse;
import com.example.crumbs_.getRandomMeal.model.mealPojo.CategoryResponse;
import com.example.crumbs_.getRandomMeal.model.mealPojo.IngredientResponse;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;

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

}
