package com.example.crumbs_.getRandomMeal.model.mealPojo;

import androidx.lifecycle.LiveData;
import com.example.crumbs_.getRandomMeal.model.db.MealPlannerLocalDataSource;
import com.example.crumbs_.getRandomMeal.model.network.MealNetworkCallback;
import com.example.crumbs_.getRandomMeal.model.network.MealPlannerNetworkCallback;
import com.example.crumbs_.getRandomMeal.model.network.MealRemoteDataSource;

import java.util.List;

public class MealPlannerRepositoryImp implements MealPlannerRepository {


    public MealRemoteDataSource mealRemoteDataSource;

    public MealPlannerLocalDataSource mealPlannerLocalDataSource;

    public static MealPlannerRepositoryImp mealPlannerRepositoryImp=null;

    public MealPlannerRepositoryImp(MealRemoteDataSource mealRemoteDataSource, MealPlannerLocalDataSource mealPlannerLocalDataSource) {
        this.mealRemoteDataSource = mealRemoteDataSource;
        this.mealPlannerLocalDataSource = mealPlannerLocalDataSource;
    }


    public static MealPlannerRepositoryImp getInstance(MealRemoteDataSource mealRemoteDataSource, MealPlannerLocalDataSource mealPlannerLocalDataSource)
    {
        if (mealPlannerRepositoryImp==null)
        {
            mealPlannerRepositoryImp=new MealPlannerRepositoryImp(mealRemoteDataSource,mealPlannerLocalDataSource);
        }
        return mealPlannerRepositoryImp;
    }


    @Override
    public void getAllMeals(MealPlannerNetworkCallback mealPlannerNetworkCallback)
    {
        mealRemoteDataSource.makeMealPlannerNetworkCallback(mealPlannerNetworkCallback);
    }
    @Override
    public void insert(MealPlanner mealPlan)
    {
        mealPlannerLocalDataSource.insert(mealPlan);
    }
    @Override
    public LiveData<List<MealPlanner>> getAllMealPlans()
    {
        return mealPlannerLocalDataSource.getAllMealPlans();
    }
    @Override
    public void deleteByDate(long date)
    {
      mealPlannerLocalDataSource.deleteByDate(date);
    }
    @Override
    public void deleteAll()
    {
        mealPlannerLocalDataSource.deleteAll();
    }

    @Override
    public void deletePlannedMeal(MealPlanner mealPlanner)
    {
        mealPlannerLocalDataSource.deleteMeal(mealPlanner);

    }
}
