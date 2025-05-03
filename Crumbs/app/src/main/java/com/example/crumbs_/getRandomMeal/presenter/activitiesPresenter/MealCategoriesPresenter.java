package com.example.crumbs_.getRandomMeal.presenter.activitiesPresenter;

import com.example.crumbs_.getRandomMeal.presenter.interfacesPresenter.MealCategoriesPresenterInterface;
import com.example.crumbs_.getRandomMeal.view.interfacesView.MealCategoriesViewInterface;
import com.example.crumbs_.getRandomMeal.model.mealPojo.Category;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealRepository;
import com.example.crumbs_.getRandomMeal.model.network.CategoryNetworkCallback;

import java.util.List;

public class MealCategoriesPresenter implements MealCategoriesPresenterInterface , CategoryNetworkCallback
{

    MealRepository mealRepository;

    MealCategoriesViewInterface mealCategoriesViewInterface;

    public MealCategoriesPresenter(MealRepository mealRepository, MealCategoriesViewInterface mealCategoriesViewInterface) {
        this.mealRepository = mealRepository;
        this.mealCategoriesViewInterface = mealCategoriesViewInterface;
    }


    @Override
    public void getAllCategories()
    {
        mealRepository.getAllCategories(this);
    }

    @Override
    public void onSuccessResult(List<Category> categories)
    {
        mealCategoriesViewInterface.showCategories(categories);
    }

    @Override
    public void onFailureResult(String errorMsg)
    {
        mealCategoriesViewInterface.showCategoriesError(errorMsg);
    }


}
