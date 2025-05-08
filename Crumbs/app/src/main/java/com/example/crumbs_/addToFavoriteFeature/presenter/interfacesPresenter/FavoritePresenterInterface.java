package com.example.crumbs_.addToFavoriteFeature.presenter.interfacesPresenter;

import androidx.lifecycle.LiveData;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;

import java.util.List;

public interface FavoritePresenterInterface
{
    void getFavMeal();
    void deleteFromFav(Meal meal);
    public void insertMeal(Meal meal);
    public void toggleFavorite(Meal meal);


}
