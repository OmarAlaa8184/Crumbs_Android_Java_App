package com.example.crumbs_.getRandomMeal.model.network;

import com.example.crumbs_.getRandomMeal.model.mealPojo.CategoryResponse;
import com.example.crumbs_.getRandomMeal.model.mealPojo.IngredientResponse;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSourceImpl implements MealRemoteDataSource
{

    private static final String Base_URL="https://www.themealdb.com/api/json/v1/1/";

    private MealService mealService;

    private static MealRemoteDataSourceImpl mealRemoteDataSourceImpl=null;

    private MealRemoteDataSourceImpl()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mealService=retrofit.create(MealService.class);
    }
    public static MealRemoteDataSourceImpl getInstance()
    {
        if (mealRemoteDataSourceImpl==null)
        {
            mealRemoteDataSourceImpl=new MealRemoteDataSourceImpl();
        }
        return mealRemoteDataSourceImpl;
    }

    @Override
    public void makeNetworkCall(MealNetworkCallback mealNetworkCallback)
    {
        mealService.getRandomMeal().enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response)
            {
                if (response.isSuccessful())
                {
                    mealNetworkCallback.onSuccessResult(response.body().getMeals());
                }
                else
                {
                    mealNetworkCallback.onFailureResult(response.message());
                }

            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t)
            {
               mealNetworkCallback.onFailureResult(t.getMessage());
            }
        });

    }

    @Override
    public void makeCategoryNetworkCall(CategoryNetworkCallback categoryNetworkCallback)
    {
        mealService.getCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response)
            {
                if (response.isSuccessful())
                {
                    categoryNetworkCallback.onSuccessResult(response.body().getCategories());
                }
                else
                {
                    categoryNetworkCallback.onFailureResult(response.message());
                }

            }
            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t)
            {
                categoryNetworkCallback.onFailureResult(t.getMessage());

            }
        });

    }

    @Override
    public void makeIngredientNetworkCall(IngredientNetworkCallback ingredientNetworkCallback)
    {
        mealService.getAllIngredients().enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response)
            {
                if (response.isSuccessful())
                {
                    ingredientNetworkCallback.onSuccessResult(response.body().getIngredients());
                }
                else
                {
                    ingredientNetworkCallback.onFailureResult(response.message());
                }
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable t)
            {
                ingredientNetworkCallback.onFailureResult(t.getMessage());

            }
        });

    }
}
