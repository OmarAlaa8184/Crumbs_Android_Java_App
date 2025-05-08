package com.example.crumbs_.addToFavoriteFeature.presenter.activitiesPresenter;

import androidx.lifecycle.LiveData;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealRepository;
import com.example.crumbs_.addToFavoriteFeature.presenter.interfacesPresenter.FavoritePresenterInterface;
import com.example.crumbs_.addToFavoriteFeature.view.interfacesView.FavoriteViewInterface;

import java.util.List;

public class FavoritePresenter implements FavoritePresenterInterface
{
    MealRepository mealRepository;

    FavoriteViewInterface favoriteViewInterface;

    public FavoritePresenter(MealRepository mealRepository, FavoriteViewInterface favoriteViewInterface)
    {
        this.mealRepository = mealRepository;
        this.favoriteViewInterface = favoriteViewInterface;
    }

    @Override
    public void getFavMeal()
    {
      LiveData<List<Meal>> meals= mealRepository.getStoredMeals();
      favoriteViewInterface.showData(meals);
    }
    @Override
    public void deleteFromFav(Meal meal)
    {
        mealRepository.deleteMeal(meal);
    }

    @Override
    public void insertMeal(Meal meal)
    {
        mealRepository.insertMeal(meal);
    }

    @Override
    public void toggleFavorite(Meal meal) {

        boolean newFavoriteState = meal.isFavorite();
        mealRepository.updateFavoriteStatus(meal.getIdMeal(), newFavoriteState);
    }
}
