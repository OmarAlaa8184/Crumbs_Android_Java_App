package com.example.crumbs_.searchFeature.presenter.activitiesPresenter;

import androidx.lifecycle.LiveData;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealRepository;
import com.example.crumbs_.getRandomMeal.model.network.SearchNetworkCallback;
import com.example.crumbs_.searchFeature.presenter.interfacesPresenter.SearchPresenterInterface;
import com.example.crumbs_.searchFeature.view.InterfacesView.SearchViewInterface;

import java.util.List;

public class SearchPresenter implements SearchPresenterInterface, SearchNetworkCallback
{
    MealRepository mealRepository;

    SearchViewInterface searchViewInterface;

    public SearchPresenter(MealRepository mealRepository, SearchViewInterface searchViewInterface)
    {
        this.mealRepository = mealRepository;
        this.searchViewInterface = searchViewInterface;
    }


    @Override
    public void search(String type, String query)
    {
        switch (type)
          {
            case "All":
                mealRepository.searchMeals(query, this);
                break;
            case "Ingredient":
                mealRepository.filterByIngredient(query, this);
                break;
            case "Area":
                mealRepository.filterByArea(query, this);
                break;
            case "Category":
                mealRepository.filterByCategory(query, this);
                break;
            default:
                searchViewInterface.showError("Invalid search type.");
         }

    }

    @Override
    public void insertMeal(Meal meal)
    {
        mealRepository.insertMeal(meal);

    }

    @Override
    public void deleteFromFav(Meal meal)
    {
       mealRepository.deleteMeal(meal);
    }

    @Override
    public void toggleFavorite(Meal meal)
    {
        boolean newFavoriteState = meal.isFavorite();
        mealRepository.updateFavoriteStatus(meal.getIdMeal(), newFavoriteState);
    }

    @Override
    public void onSuccessSearchResult(List<Meal> meal)
    {
       //searchViewInterface.showMeals(meal);
        if (meal != null && !meal.isEmpty())
        {
            searchViewInterface.showMeals(meal);
        }
        else
        {
            searchViewInterface.showError("No meals found.");
        }
    }

    @Override
    public void onFailureSearchResult(String errorMsg)
    {

        searchViewInterface.showError(errorMsg);
    }
}
