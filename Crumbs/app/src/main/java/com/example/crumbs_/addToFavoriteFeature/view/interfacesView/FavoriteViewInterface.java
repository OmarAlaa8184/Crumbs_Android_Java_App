package com.example.crumbs_.addToFavoriteFeature.view.interfacesView;

import androidx.lifecycle.LiveData;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;

import java.util.List;

public interface FavoriteViewInterface
{

    public void showData(LiveData<List<Meal>> meals);
    public void showError(String message);


}
