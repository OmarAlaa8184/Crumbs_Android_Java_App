package com.example.crumbs_.getRandomMeal.model.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.crumbs_.getRandomMeal.model.mealPojo.MealPlanner;

import java.util.List;

@Dao
public interface MealPlannerDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MealPlanner mealPlan);

    @Query("SELECT * FROM meal_plan_table")
    LiveData<List<MealPlanner>> getAllMealPlans();

    @Query("DELETE FROM meal_plan_table WHERE date = :date")
    void deleteByDate(long date);

    @Query("DELETE FROM meal_plan_table")
    void deleteAll();

    @Delete
    void deletePlannedMeal(MealPlanner mealPlanner);
}
