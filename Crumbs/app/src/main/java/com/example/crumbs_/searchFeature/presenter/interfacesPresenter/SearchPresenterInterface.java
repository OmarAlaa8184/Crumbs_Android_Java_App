package com.example.crumbs_.searchFeature.presenter.interfacesPresenter;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;

public interface SearchPresenterInterface
{
    void search(String type, String query);

    public void insertMeal(Meal meal);
    public void deleteFromFav(Meal meal);
    public void toggleFavorite(Meal meal);

}
