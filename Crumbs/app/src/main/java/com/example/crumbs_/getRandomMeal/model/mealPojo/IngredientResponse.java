package com.example.crumbs_.getRandomMeal.model.mealPojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientResponse
{
        @SerializedName("meals")
        private List<Ingredient> ingredients;

        public List<Ingredient> getIngredients()
        {
            return ingredients;
        }


    public void setIngredients(List<Ingredient> ingredients)
    {
        this.ingredients = ingredients;
    }
}
