package com.example.crumbs_.getRandomMeal.presenter.interfacesPresenter;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;

public interface FavoritePresenterInterface
{
    void getFavMeal();
    void deleteFromFav(Meal meal);
    public void insertMeal(Meal meal);


}
