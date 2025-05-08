package com.example.crumbs_.mealPlannerFeature.presenter.activitiesPresenter;

import androidx.lifecycle.LiveData;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealPlanner;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealPlannerRepository;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealPlannerRepositoryImp;
import com.example.crumbs_.getRandomMeal.model.network.MealPlannerNetworkCallback;
import com.example.crumbs_.mealPlannerFeature.presenter.interfacesPresenter.PlannerPresenterInterface;
import com.example.crumbs_.mealPlannerFeature.view.interfacesView.PlannerViewInterface;

import java.util.Date;
import java.util.List;

public class PlannerPresenter implements PlannerPresenterInterface,MealPlannerNetworkCallback{

    private PlannerViewInterface plannerViewInterface;
    private MealPlannerRepository mealPlannerRepository;
    private Date pendingDate; // Store date for async network callback

    public PlannerPresenter(PlannerViewInterface plannerViewInterface, MealPlannerRepository mealPlannerRepository) {
        this.plannerViewInterface = plannerViewInterface;
        this.mealPlannerRepository = mealPlannerRepository;
    }

    @Override
    public void getPlannedMeal() {
        LiveData<List<MealPlanner>> mealPlan = mealPlannerRepository.getAllMealPlans();
        plannerViewInterface.showMealPlan(mealPlan);
    }

    @Override
    public void deleteFromPlannedByDate(long date) {
        mealPlannerRepository.deleteByDate(date);
    }

    @Override
    public void insertMeal(MealPlanner mealPlanner) {
        mealPlannerRepository.insert(mealPlanner);

    }

    @Override
    public void deletePlannedMeal(MealPlanner mealPlanner) {

        mealPlannerRepository.deletePlannedMeal(mealPlanner);
    }

    @Override
    public void deleteAll() {

        mealPlannerRepository.deleteAll();

    }

    @Override
    public void onSuccessResult(List<MealPlanner> meal)
    {
        if (meal != null && !meal.isEmpty() && pendingDate != null)
        {
            MealPlanner mealPlanner = meal.get(0);
            mealPlanner.date = pendingDate.getTime(); // Set the date from assignRandomMealToDate
            mealPlannerRepository.insert(mealPlanner);
            getPlannedMeal(); // Refresh UI
            pendingDate = null; // Clear pending date
        }
        else
        {
            plannerViewInterface.showError("No meals found");
        }

    }

    @Override
    public void onFailureResult(String errorMsg)
    {
        plannerViewInterface.showError(errorMsg);
        pendingDate = null; // Clear pending date on failure
    }

    public void assignRandomMealToDate(Date date)
    {
        pendingDate = date; // Store date for callback
        mealPlannerRepository.getAllMeals(this); // Use this as the callback

    }
}
