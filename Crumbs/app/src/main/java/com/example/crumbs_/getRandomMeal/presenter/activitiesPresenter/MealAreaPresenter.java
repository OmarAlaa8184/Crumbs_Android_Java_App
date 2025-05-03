package com.example.crumbs_.getRandomMeal.presenter.activitiesPresenter;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Area;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealRepository;
import com.example.crumbs_.getRandomMeal.model.network.AreaNetworkCallback;
import com.example.crumbs_.getRandomMeal.presenter.interfacesPresenter.MealAreaPresenterInterface;
import com.example.crumbs_.getRandomMeal.view.interfacesView.MealAreaViewInterface;

import java.util.List;

public class MealAreaPresenter implements MealAreaPresenterInterface, AreaNetworkCallback
{
    MealRepository mealRepository;
    MealAreaViewInterface mealAreaViewInterface;

    public MealAreaPresenter(MealRepository mealRepository, MealAreaViewInterface mealAreaViewInterface) {
        this.mealRepository = mealRepository;
        this.mealAreaViewInterface = mealAreaViewInterface;
    }


    @Override
    public void getAllAreas()
    {
        mealRepository.getAllAreas(this);

    }

    @Override
    public void onSuccessResult(List<Area> areas)
    {
        mealAreaViewInterface.showArea(areas);
    }

    @Override
    public void onFailureResult(String errorMsg)
    {
        mealAreaViewInterface.showAreError(errorMsg);
    }
}
