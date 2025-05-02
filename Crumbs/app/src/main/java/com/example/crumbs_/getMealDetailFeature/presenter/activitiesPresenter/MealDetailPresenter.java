package com.example.crumbs_.getMealDetailFeature.presenter.activitiesPresenter;

import android.content.Intent;

import com.example.crumbs_.getMealDetailFeature.presenter.interfacesPresenter.MealDetailPresenterInterface;
import com.example.crumbs_.getMealDetailFeature.view.interfacesView.MealDetailViewInterface;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealRepository;

import java.util.List;

public class MealDetailPresenter implements MealDetailPresenterInterface
{

    MealRepository mealRepository;
    MealDetailViewInterface mealDetailViewInterface;

    public MealDetailPresenter(MealRepository mealRepository, MealDetailViewInterface mealDetailViewInterface) {
        this.mealRepository = mealRepository;
        this.mealDetailViewInterface = mealDetailViewInterface;
    }


    @Override
    public void getMealDetails(String name, String category, String area, String instructions, String thumb, String youtube, List<String> ingredients)
    {
       mealDetailViewInterface.showMealDetails(name,category,area,instructions,thumb,youtube,ingredients);
    }
}
