package com.example.crumbs_.mealPlannerFeature.presenter.interfacesPresenter;

import com.example.crumbs_.getRandomMeal.model.mealPojo.MealPlanner;

import java.util.Date;
import java.util.List;

public interface PlannerPresenterInterface
{
    public void onSuccessResult(List<MealPlanner> meal);
    void getPlannedMeal();
    void deleteFromPlannedByDate(long date);
    public void insertMeal(MealPlanner mealPlanner);
    void deletePlannedMeal(MealPlanner mealPlanner);
    void deleteAll();
}
