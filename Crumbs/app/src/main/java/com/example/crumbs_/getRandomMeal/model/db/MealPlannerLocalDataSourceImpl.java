package com.example.crumbs_.getRandomMeal.model.db;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealPlanner;
import java.util.List;

public class MealPlannerLocalDataSourceImpl implements MealPlannerLocalDataSource
{
    private MealPlannerDao mealPlannerDao;
    private LiveData<List<MealPlanner>> meals;
    private static MealPlannerLocalDataSourceImpl mealPlannerLocalDataSource=null;

    private MealPlannerLocalDataSourceImpl(Context context)
    {
        MealDatabase mealDatabase=MealDatabase.getInstance(context.getApplicationContext());
        mealPlannerDao=mealDatabase.getMealPlannerDAO();
        meals=mealPlannerDao.getAllMealPlans();
    }
    public static MealPlannerLocalDataSourceImpl getInstance(Context context)
    {
        if (mealPlannerLocalDataSource==null)
        {
            mealPlannerLocalDataSource=new MealPlannerLocalDataSourceImpl(context);
        }
        return mealPlannerLocalDataSource;
    }


    @Override
    public void insert(MealPlanner mealPlan)
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                mealPlannerDao.insert(mealPlan);
            }
        }).start();

    }

    @Override
    public LiveData<List<MealPlanner>> getAllMealPlans()
    {
          return meals;
    }

    @Override
    public void deleteByDate(long date)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealPlannerDao.deleteByDate(date);
            }
        }).start();
    }

    @Override
    public void deleteAll()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealPlannerDao.deleteAll();
            }
        }).start();

    }

    @Override
    public void deleteMeal(MealPlanner mealPlanner)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {

                mealPlannerDao.deletePlannedMeal(mealPlanner);
            }
        });
    }


}
