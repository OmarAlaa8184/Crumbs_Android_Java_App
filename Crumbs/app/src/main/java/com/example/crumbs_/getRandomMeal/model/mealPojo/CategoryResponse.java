package com.example.crumbs_.getRandomMeal.model.mealPojo;

import java.util.List;

public class CategoryResponse
{
  private List<Category> categories;

  public List<Category> getCategories()
  {
      return categories;
  }
  public void setCategories(List<Category> categories)
  {
        this.categories = categories;
    }
}
