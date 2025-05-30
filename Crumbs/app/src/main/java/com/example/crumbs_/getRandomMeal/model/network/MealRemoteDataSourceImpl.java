package com.example.crumbs_.getRandomMeal.model.network;

import com.example.crumbs_.getRandomMeal.model.mealPojo.AreaResponse;
import com.example.crumbs_.getRandomMeal.model.mealPojo.CategoryResponse;
import com.example.crumbs_.getRandomMeal.model.mealPojo.IngredientResponse;
import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealPlanner;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealResponse;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void makeAreaNetworkCallback(AreaNetworkCallback areaNetworkCallback)
    {

        mealService.getAllAreas().enqueue(new Callback<AreaResponse>() {
            @Override
            public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response)
            {
                if (response.isSuccessful())
                {
                    areaNetworkCallback.onSuccessResult(response.body().getAreas());
                }
                else
                {
                    areaNetworkCallback.onFailureResult(response.message());
                }
            }

            @Override
            public void onFailure(Call<AreaResponse> call, Throwable t)
            {
                areaNetworkCallback.onFailureResult(t.getMessage());

            }
        });
    }

    @Override
    public void makeSearchMealsNetworkCallback(String query, SearchNetworkCallback searchNetworkCallback)
    {
        mealService.searchMeals(query).enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful())
                {
                    searchNetworkCallback.onSuccessSearchResult(response.body().getMeals());
                }
                else
                {
                    searchNetworkCallback.onFailureSearchResult(response.message());
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t)
            {
                searchNetworkCallback.onFailureSearchResult(t.getMessage());

            }
        });
    }

    @Override
    public void makeFilterByIngredientNetworkCallback(String ingredient, SearchNetworkCallback searchNetworkCallback)
    {
        mealService.filterByIngredient(ingredient).enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful())
                {
                    searchNetworkCallback.onSuccessSearchResult(response.body().getMeals());
                }
                else
                {
                    searchNetworkCallback.onFailureSearchResult(response.message());
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t)
            {
                searchNetworkCallback.onFailureSearchResult(t.getMessage());

            }
        });
    }

    @Override
    public void makeFilterByAreaNetworkCallback(String area, SearchNetworkCallback searchNetworkCallback)
    {
        mealService.filterByArea(area).enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful())
                {
                    searchNetworkCallback.onSuccessSearchResult(response.body().getMeals());
                }
                else
                {
                    searchNetworkCallback.onFailureSearchResult(response.message());
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t)
            {
                searchNetworkCallback.onFailureSearchResult(t.getMessage());

            }
        });
    }

    @Override
    public void makeFilterByCategoryNetworkCallback(String category, SearchNetworkCallback searchNetworkCallback)
    {
        mealService.filterByCategory(category).enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful())
                {
                    searchNetworkCallback.onSuccessSearchResult(response.body().getMeals());
                }
                else
                {
                    searchNetworkCallback.onFailureSearchResult(response.message());
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t)
            {
                searchNetworkCallback.onFailureSearchResult(t.getMessage());

            }
        });
    }

    @Override
    public void makeMealPlannerNetworkCallback(MealPlannerNetworkCallback mealPlannerNetworkCallback)
    {
        mealService.getRandomMeal().enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response)
            {
                if (response.isSuccessful())
                {
                    mealPlannerNetworkCallback.onSuccessResult(convertMealsToMealPlanners(response.body().getMeals()));
                }
                else
                {
                    mealPlannerNetworkCallback.onFailureResult(response.message());
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t)
            {
                mealPlannerNetworkCallback.onFailureResult(t.getMessage());
            }
        });

    }
    private List<MealPlanner> convertMealsToMealPlanners(List<Meal> meals) {
        List<MealPlanner> mealPlanners = new ArrayList<>();
        if (meals != null)
        {
            for (Meal meal : meals)
            {
                mealPlanners.add(meal.toMealPlanner(System.currentTimeMillis()));
            }
        }
        return mealPlanners;
    }
}
