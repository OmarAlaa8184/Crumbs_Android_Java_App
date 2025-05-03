package com.example.crumbs_.getRandomMeal.presenter.activitiesPresenter;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Ingredient;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealRepository;
import com.example.crumbs_.getRandomMeal.model.network.IngredientNetworkCallback;
import com.example.crumbs_.getRandomMeal.presenter.interfacesPresenter.MealIngredientPresenterInterface;
import com.example.crumbs_.getRandomMeal.view.interfacesView.MealIngredientViewInterface;

import java.util.List;

public class MealIngredientPresenter implements MealIngredientPresenterInterface,IngredientNetworkCallback
{

   MealRepository mealRepository;

   MealIngredientViewInterface mealIngredientViewInterface;

    public MealIngredientPresenter(MealRepository mealRepository, MealIngredientViewInterface mealIngredientViewInterface) {
        this.mealRepository = mealRepository;
        this.mealIngredientViewInterface = mealIngredientViewInterface;
    }


    @Override
    public void getAllIngredients()
    {
         mealRepository.getAllIngredients(this);
    }

    @Override
    public void onSuccessResult(List<Ingredient> ingredients)
    {
         mealIngredientViewInterface.showIngredient(ingredients);
    }

    @Override
    public void onFailureResult(String errorMsg)
    {
         mealIngredientViewInterface.showIngredientError(errorMsg);
    }


}
