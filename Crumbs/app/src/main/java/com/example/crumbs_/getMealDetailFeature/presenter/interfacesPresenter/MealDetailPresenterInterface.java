package com.example.crumbs_.getMealDetailFeature.presenter.interfacesPresenter;

import android.content.Intent;

import java.util.List;

public interface MealDetailPresenterInterface
{
    void getMealDetails(String name, String category, String area,
                        String instructions, String thumb, String youtube,
                        List<String> ingredients);
}
