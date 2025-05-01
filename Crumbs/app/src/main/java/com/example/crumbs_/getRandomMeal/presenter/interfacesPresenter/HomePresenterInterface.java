package com.example.crumbs_.getRandomMeal.presenter.interfacesPresenter;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;

public interface HomePresenterInterface
{
    public void getAllMeals();
    public void insertMeal(Meal meal);
    public void deleteFromFav(Meal meal);

}
