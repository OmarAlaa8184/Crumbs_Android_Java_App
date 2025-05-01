package com.example.crumbs_.getRandomMeal.model.mealPojo;

import androidx.lifecycle.LiveData;

import com.example.crumbs_.getRandomMeal.model.db.MealLocalDataSource;
import com.example.crumbs_.getRandomMeal.model.network.MealNetworkCallback;
import com.example.crumbs_.getRandomMeal.model.network.MealRemoteDataSource;

import java.util.List;

public class MealRepositoryImp implements MealRepository
{
    public MealRemoteDataSource mealRemoteDataSource;
    public MealLocalDataSource mealLocalDataSource;

    private static MealRepositoryImp mealRepositoryImp=null;

    public MealRepositoryImp(MealRemoteDataSource mealRemoteDataSource, MealLocalDataSource mealLocalDataSource) {
        this.mealRemoteDataSource = mealRemoteDataSource;
        this.mealLocalDataSource = mealLocalDataSource;
    }

    public static MealRepositoryImp getInstance(MealRemoteDataSource mealRemoteDataSource,MealLocalDataSource mealLocalDataSource)
    {
        if (mealRepositoryImp==null)
        {
            mealRepositoryImp=new MealRepositoryImp(mealRemoteDataSource,mealLocalDataSource);
        }
        return mealRepositoryImp;
    }



    @Override
    public void getAllMeals(MealNetworkCallback mealNetworkCallback)
    {
        mealRemoteDataSource.makeNetworkCall(mealNetworkCallback);

    }

    @Override
    public void insertMeal(Meal meal)
    {
        mealLocalDataSource.insertMeal(meal);
    }

    @Override
    public void deleteMeal(Meal meal)
    {
          mealLocalDataSource.deleteMeal(meal);
    }

    @Override
    public LiveData<List<Meal>> getStoredMeals()
    {
            return mealLocalDataSource.getStoredMeals();
    }
}
