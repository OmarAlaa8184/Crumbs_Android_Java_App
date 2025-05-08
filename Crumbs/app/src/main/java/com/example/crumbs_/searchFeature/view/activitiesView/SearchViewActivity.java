package com.example.crumbs_.searchFeature.view.activitiesView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crumbs_.R;
import com.example.crumbs_.getMealDetailFeature.view.activitiesView.MealDetailView;
import com.example.crumbs_.getRandomMeal.model.db.MealLocalDataSourceImpl;
import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealRepositoryImp;
import com.example.crumbs_.getRandomMeal.model.network.MealRemoteDataSourceImpl;
import com.example.crumbs_.getRandomMeal.view.activitiesView.HomeView;
import com.example.crumbs_.getRandomMeal.view.listenersView.MealOnClickListener;
import com.example.crumbs_.loginFeature.view.activitiesView.LoginView;
import com.example.crumbs_.mealPlannerFeature.view.activitiesView.PlannerView;
import com.example.crumbs_.searchFeature.presenter.activitiesPresenter.SearchPresenter;
import com.example.crumbs_.searchFeature.view.InterfacesView.SearchViewInterface;
import com.example.crumbs_.searchFeature.view.adaptersView.SearchAdapter;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.search.SearchBar;
import com.google.android.material.search.SearchView;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SearchViewActivity extends AppCompatActivity implements SearchViewInterface, MealOnClickListener
{
    private SearchBar searchBar;
    private SearchView searchView;
    private ChipGroup filterChipGroup;
    private RecyclerView searchRecyclerView;
    private LinearLayout noResultsView;
    private SearchPresenter searchPresenter;
    private SearchAdapter searchAdapter;
    private List<Meal> meals;
    private enum SearchType { All, Ingredient, Area, Category }
    private SearchType currentSearchType = SearchType.All;

    private boolean isGuest;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        meals = new ArrayList<>();
        searchAdapter=new SearchAdapter(meals,this,this);

        searchBar = findViewById(R.id.searchBar);
        searchView = findViewById(R.id.searchView);
        filterChipGroup = findViewById(R.id.filterChipGroup);
        searchRecyclerView = findViewById(R.id.searchRecyclerView);
        noResultsView = findViewById(R.id.noResultsView);

        SharedPreferences prefs = getSharedPreferences("user_prefs1", MODE_PRIVATE);
        isGuest = prefs.getBoolean("isGuest", false);

        searchView.setupWithSearchBar(searchBar);
        searchView.setHint(getString(R.string.search_hint));

        searchBar.setOnClickListener(v -> {
            searchView.show();
            searchView.post(() -> {
                EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
                if (searchEditText != null) {
                    searchEditText.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT);
                }
            });

        });

        // Handle search submission
        searchView.getEditText().setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH)
            {
                String query = v.getText().toString().trim();
                if (!query.isEmpty()) {
                    handleSearch(query);
                    //hide keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
                    searchView.hide();
                    return true;
                }
                else
                {
                    Toast.makeText(this, "Please enter a search query", Toast.LENGTH_SHORT).show();
                }

            }
            return false;
        });

        // Ensure keyboard shows when SearchView appears
        searchView.addTransitionListener(new SearchView.TransitionListener() {
            @Override
            public void onStateChanged(SearchView searchView, SearchView.TransitionState previousState,
                                       SearchView.TransitionState newState) {
                if (newState == SearchView.TransitionState.SHOWING)
                {
                    //show keyboard
                    searchView.post(() -> {
                        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
                        if (searchEditText != null) {
                            searchEditText.requestFocus();
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT);
                        }
                    });
                }
            }
        });


        searchRecyclerView = findViewById(R.id.searchRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        searchRecyclerView.setLayoutManager(linearLayoutManager);
        searchRecyclerView.setHasFixedSize(true);

        searchPresenter=new SearchPresenter(MealRepositoryImp.getInstance(MealRemoteDataSourceImpl.getInstance(),
                        MealLocalDataSourceImpl.getInstance(this)),this);


        filterChipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.ingredientChip)
            {
                currentSearchType = SearchType.Ingredient;
            }
            else if (checkedId == R.id.areaChip)
            {
                currentSearchType = SearchType.Area;
            }
            else if (checkedId == R.id.categoryChip)
            {
                currentSearchType = SearchType.Category;
            }
           else
            {
                currentSearchType = SearchType.All;
            }
        });
    }
    private void handleSearch(String query)
    {
        switch (currentSearchType)
        {
            case Ingredient:
                searchPresenter.search("Ingredient", query);
                break;
            case Area:
                searchPresenter.search("Area", query);
                break;
            case Category:
                searchPresenter.search("Category", query);
                break;
            default:
                searchPresenter.search("All", query);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("user_prefs1", MODE_PRIVATE);
        isGuest = prefs.getBoolean("isGuest", false);
    }

    @Override
    public void showMeals(List<Meal> meals)
    {
        if (meals == null || meals.isEmpty()) {
            searchRecyclerView.setVisibility(View.GONE);
            noResultsView.setVisibility(View.VISIBLE);
            Toast.makeText(this, "No meals found.", Toast.LENGTH_SHORT).show();
            return;
        }

        this.meals.clear();           // clear current list
        this.meals.addAll(meals);     // avoid replacing the reference directly
        searchRecyclerView.setAdapter(searchAdapter);
        searchRecyclerView.setVisibility(View.VISIBLE);
        noResultsView.setVisibility(View.GONE);

    }

    @Override
    public void showError(String message)
    {
        searchRecyclerView.setVisibility(View.GONE);
        noResultsView.setVisibility(View.VISIBLE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMealClick(Meal meal)
    {
        Intent intent = new Intent(this, MealDetailView.class);
        intent.putExtra("MEAL_NAME", meal.getStrMeal());
        intent.putExtra("MEAL_CATEGORY", meal.getStrCategory());
        intent.putExtra("MEAL_AREA", meal.getStrArea());
        intent.putExtra("MEAL_INSTRUCTIONS", meal.getStrInstructions());
        intent.putExtra("MEAL_THUMB", meal.getStrMealThumb());
        intent.putExtra("MEAL_YOUTUBE", meal.getStrYoutube());

        ArrayList<String> ingredients = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            String ingredient = getValue(meal, "strIngredient" + i);
            if (ingredient != null && !ingredient.trim().isEmpty()) {
                String formatted =ingredient;
                ingredients.add(formatted.trim());
            }
        }
        intent.putStringArrayListExtra("MEAL_INGREDIENTS", ingredients);

        startActivity(intent);

    }
    private String getValue(Meal meal, String fieldName) {
        try {
            Field field = meal.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (String) field.get(meal);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void onYoutubeClick(String youtubeUrl)
    {
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
        startActivity(intent);
    }

    @Override
    public void onFavoriteClick(Meal meal, boolean isFavorite)
    {
        if (isFavorite)
        {
            // Add to favorites
            searchPresenter.insertMeal(meal);
            Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // Remove from favorites
            searchPresenter.deleteFromFav(meal);
            Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFavoriteClick1(Meal meal) {
        if (isGuest)
        {
            Toast.makeText(this, "Please login to manage favorites", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SearchViewActivity.this, LoginView.class));

        }
        else
        {
            if (meal.isFavorite())
            {
                searchPresenter.deleteFromFav(meal);
                Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
            }
            else
            {
                meal.setFavorite(!meal.isFavorite());
                searchPresenter.insertMeal(meal);
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
            }
            searchPresenter.toggleFavorite(meal);
        }

    }


    @Override
    public void onPlannerClick(Meal meal, boolean isPlanned)
    {
        if (isGuest) {
            Toast.makeText(this, "Please login to access the planner", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SearchViewActivity.this, LoginView.class));
        }
        else
        {
            Intent intent= new Intent(SearchViewActivity.this, PlannerView.class);

            if (meal != null) {
                intent.putExtra("MEAL", meal);
                startActivity(intent);
            } else {
                Log.e("HomeView", "Meal is null");
            }
        }

    }
}
