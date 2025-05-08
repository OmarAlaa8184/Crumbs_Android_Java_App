package com.example.crumbs_.getRandomMeal.model.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealPlanner;

@Database(entities = {Meal.class , MealPlanner.class},version = 1)
public abstract class MealDatabase extends RoomDatabase
{
    private static MealDatabase mealDatabase=null;

    public abstract MealDAO getMealDAO();
    public abstract MealPlannerDao getMealPlannerDAO();

    public static MealDatabase getInstance(Context context)
    {
        if (mealDatabase==null)
        {
            mealDatabase= Room.databaseBuilder(context,MealDatabase.class,"db").build();
        }
        return mealDatabase;
    }
}
