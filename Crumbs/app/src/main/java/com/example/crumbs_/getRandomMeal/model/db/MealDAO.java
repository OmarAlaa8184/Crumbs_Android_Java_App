package com.example.crumbs_.getRandomMeal.model.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;

import java.util.List;

@Dao
public interface MealDAO
{
    @Query("Select * from meal_table")
    public LiveData<List<Meal>> getAllProducts();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertProduct(Meal product);
    @Delete
    public void removeProduct(Meal product);

   // to retrieve only favorite meals.
    @Query("UPDATE meal_table SET isFavorite = :isFavorite WHERE idMeal = :mealId")
    void updateFavoriteStatus(String mealId, boolean isFavorite);

    //to toggle the favorite state without replacing the entire meal.
    @Query("SELECT * FROM meal_table WHERE isFavorite = 1")
    LiveData<List<Meal>> getFavoriteMeals();

}
