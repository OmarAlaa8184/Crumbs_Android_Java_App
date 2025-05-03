package com.example.crumbs_.getRandomMeal.model.network;

import com.example.crumbs_.getRandomMeal.model.mealPojo.Area;
import com.example.crumbs_.getRandomMeal.model.mealPojo.Category;

import java.util.List;

public interface AreaNetworkCallback
{

    void onSuccessResult(List<Area> areas);

    void onFailureResult(String errorMsg);
}
