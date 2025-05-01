package com.example.crumbs_.getRandomMeal.model.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;

import java.util.List;

public class MealLocalDataSourceImpl implements MealLocalDataSource
{
    private MealDAO mealDAO;
    private LiveData<List<Meal>> meals;
    private static MealLocalDataSourceImpl mealLocalDataSourceImpl=null;

    private MealLocalDataSourceImpl(Context context)
    {
        MealDatabase mealDatabase=MealDatabase.getInstance(context.getApplicationContext());
        mealDAO=mealDatabase.getMealDAO();
        meals=mealDAO.getAllProducts();
    }
    public static MealLocalDataSourceImpl getInstance(Context context)
    {
        if (mealLocalDataSourceImpl==null)
        {
            mealLocalDataSourceImpl=new MealLocalDataSourceImpl(context);
        }
        return mealLocalDataSourceImpl;
    }

    @Override
    public void insertMeal(Meal meal)
    {
         new Thread(new Runnable() {
             @Override
             public void run()
             {
                mealDAO.insertProduct(meal);
             }
         }).start();
    }

    @Override
    public void deleteMeal(Meal meal)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                mealDAO.removeProduct(meal);
            }
        }).start();

    }
    @Override
    public LiveData<List<Meal>> getStoredMeals()
    {
        return meals;
    }
}
