package com.example.crumbs_.getRandomMeal.model.mealPojo;

import androidx.lifecycle.LiveData;

import com.example.crumbs_.getRandomMeal.model.db.MealLocalDataSource;
import com.example.crumbs_.getRandomMeal.model.network.AreaNetworkCallback;
import com.example.crumbs_.getRandomMeal.model.network.CategoryNetworkCallback;
import com.example.crumbs_.getRandomMeal.model.network.IngredientNetworkCallback;
import com.example.crumbs_.getRandomMeal.model.network.MealNetworkCallback;
import com.example.crumbs_.getRandomMeal.model.network.MealRemoteDataSource;
import com.example.crumbs_.getRandomMeal.model.network.SearchNetworkCallback;

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
    public void getAllCategories(CategoryNetworkCallback categoryNetworkCallback)
    {
            mealRemoteDataSource.makeCategoryNetworkCall(categoryNetworkCallback);
    }

    @Override
    public void getAllIngredients(IngredientNetworkCallback ingredientNetworkCallback)
    {
          mealRemoteDataSource.makeIngredientNetworkCall(ingredientNetworkCallback);
    }

    @Override
    public void getAllAreas(AreaNetworkCallback areaNetworkCallback)
    {
        mealRemoteDataSource.makeAreaNetworkCallback(areaNetworkCallback);

    }

    @Override
    public void searchMeals(String query, SearchNetworkCallback searchNetworkCallback)
    {
        mealRemoteDataSource.makeSearchMealsNetworkCallback(query,searchNetworkCallback);

    }

    @Override
    public void filterByIngredient(String ingredient, SearchNetworkCallback searchNetworkCallback)
    {
       mealRemoteDataSource.makeFilterByIngredientNetworkCallback(ingredient,searchNetworkCallback);
    }

    @Override
    public void filterByArea(String area, SearchNetworkCallback searchNetworkCallback)
    {
        mealRemoteDataSource.makeFilterByAreaNetworkCallback(area,searchNetworkCallback);
    }

    @Override
    public void filterByCategory(String category, SearchNetworkCallback searchNetworkCallback)
    {
        mealRemoteDataSource.makeFilterByCategoryNetworkCallback(category,searchNetworkCallback);

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
