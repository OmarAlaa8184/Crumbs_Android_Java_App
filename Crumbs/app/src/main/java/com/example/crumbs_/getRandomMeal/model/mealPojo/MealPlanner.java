package com.example.crumbs_.getRandomMeal.model.mealPojo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meal_plan_table", primaryKeys = {"date", "mealId"})
public class MealPlanner
{
    public long date;
    @NonNull
    public String mealId;
    public String mealName;
    public String mealInstructions;
    public String mealImageUrl;
    public String mealYoutube;
    private String mealCategory;

    private String mealArea;

    public MealPlanner(long date,@NonNull String mealId, String mealName, String mealInstructions, String mealImageUrl, String mealYoutube, String mealCategory, String mealArea) {
        this.date = date;
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealInstructions = mealInstructions;
        this.mealImageUrl = mealImageUrl;
        this.mealYoutube = mealYoutube;
        this.mealCategory = mealCategory;
        this.mealArea = mealArea;
    }


    public void setMealArea(String mealArea) {
        this.mealArea = mealArea;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }
    public String getMealArea()
    {
    return mealArea;
   }

    public String getMealCategory() {
        return mealCategory;
    }

    public long getDate() {
        return date;
    }

    public String getMealId() {
        return mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public String getMealInstructions() {
        return mealInstructions;
    }

    public String getMealImageUrl() {
        return mealImageUrl;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setMealInstructions(String mealInstructions) {
        this.mealInstructions = mealInstructions;
    }
    public String getMealYoutube() {
        return mealYoutube;
    }

    public void setMealYoutube(String mealYoutube) {
        this.mealYoutube = mealYoutube;
    }

    public void setMealImageUrl(String mealImageUrl) {
        this.mealImageUrl = mealImageUrl;
    }



}

