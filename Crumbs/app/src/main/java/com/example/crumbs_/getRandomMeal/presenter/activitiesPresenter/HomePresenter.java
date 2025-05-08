package com.example.crumbs_.getRandomMeal.presenter.activitiesPresenter;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealRepository;
import com.example.crumbs_.getRandomMeal.model.network.MealNetworkCallback;
import com.example.crumbs_.getRandomMeal.presenter.interfacesPresenter.HomePresenterInterface;
import com.example.crumbs_.getRandomMeal.view.interfacesView.HomeViewInterface;

import java.util.List;

public class HomePresenter implements HomePresenterInterface, MealNetworkCallback
{
    MealRepository mealRepository;
    HomeViewInterface homeViewInterface;

    public HomePresenter(MealRepository mealRepository, HomeViewInterface homeViewInterface)
    {
        this.mealRepository = mealRepository;
        this.homeViewInterface = homeViewInterface;
    }

    @Override
    public void getAllMeals()
    {
        mealRepository.getAllMeals(this);
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
    public void toggleFavorite(Meal meal) {
        boolean newFavoriteState = meal.isFavorite();
        mealRepository.updateFavoriteStatus(meal.getIdMeal(), newFavoriteState);
    }


    @Override
    public void onSuccessResult(List<Meal> meals)
    {
        homeViewInterface.showData(meals);
    }

    @Override
    public void onFailureResult(String errorMsg)
    {
        homeViewInterface.showError(errorMsg);

    }
}
