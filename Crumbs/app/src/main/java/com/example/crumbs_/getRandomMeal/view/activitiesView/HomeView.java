package com.example.crumbs_.getRandomMeal.view.activitiesView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.crumbs_.R;
import com.example.crumbs_.addToFavoriteFeature.view.activitiesView.FavoriteView;
import com.example.crumbs_.getRandomMeal.model.mealPojo.Area;
import com.example.crumbs_.getRandomMeal.model.mealPojo.Ingredient;
import com.example.crumbs_.getRandomMeal.presenter.activitiesPresenter.MealAreaPresenter;
import com.example.crumbs_.getRandomMeal.presenter.activitiesPresenter.MealCategoriesPresenter;
import com.example.crumbs_.getRandomMeal.presenter.activitiesPresenter.MealIngredientPresenter;
import com.example.crumbs_.getRandomMeal.view.adaptersView.MealAreaAdaptor;
import com.example.crumbs_.getRandomMeal.view.adaptersView.MealCategoriesAdapter;
import com.example.crumbs_.getRandomMeal.view.adaptersView.MealIngredientAdapter;
import com.example.crumbs_.getRandomMeal.view.interfacesView.MealAreaViewInterface;
import com.example.crumbs_.getRandomMeal.view.interfacesView.MealCategoriesViewInterface;
import com.example.crumbs_.getMealDetailFeature.view.activitiesView.MealDetailView;
import com.example.crumbs_.getRandomMeal.model.db.MealLocalDataSourceImpl;
import com.example.crumbs_.getRandomMeal.model.mealPojo.Category;
import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealRepositoryImp;
import com.example.crumbs_.getRandomMeal.model.network.MealRemoteDataSourceImpl;
import com.example.crumbs_.getRandomMeal.presenter.activitiesPresenter.HomePresenter;
import com.example.crumbs_.getRandomMeal.view.adaptersView.HomeAdapter;
import com.example.crumbs_.getRandomMeal.view.interfacesView.HomeViewInterface;
import com.example.crumbs_.getRandomMeal.view.interfacesView.MealIngredientViewInterface;
import com.example.crumbs_.getRandomMeal.view.listenersView.MealOnClickListener;
import com.example.crumbs_.loginFeature.view.activitiesView.LoginView;
import com.example.crumbs_.searchFeature.view.activitiesView.SearchViewActivity;
import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class HomeView extends AppCompatActivity implements
                                                           HomeViewInterface,
        MealCategoriesViewInterface, MealOnClickListener,
        MealIngredientViewInterface, MealAreaViewInterface
{

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private static final String KEY_MEAL = "key_meal";
    private List<Meal> meals;
    private HomePresenter homePresenter;
    Context context;

     /*Category*/

    private RecyclerView categoryRecyclerView;
    private MealCategoriesAdapter mealCategoriesAdapter;
    private MealCategoriesPresenter mealCategoriesPresenter;
    private ArrayList<Category> categories;

    /*Ingredient*/

    private RecyclerView ingredientRecyclerView;
    private MealIngredientAdapter mealIngredientAdapter;
    private MealIngredientPresenter mealIngredientPresenter;
    private List<Ingredient> ingredients;

    /*Areas*/
    private RecyclerView areaRecyclerView;
    private MealAreaAdaptor mealAreaAdaptor;
    private MealAreaPresenter mealAreaPresenter;
    private List<Area> areas;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen);

        meals = new ArrayList<>();
        homeAdapter = new HomeAdapter(meals, this, this);

        categories = new ArrayList<>();
        mealCategoriesAdapter = new MealCategoriesAdapter(categories, this);

        ingredients = new ArrayList<>();
        mealIngredientAdapter = new MealIngredientAdapter(ingredients, this);

        areas = new ArrayList<>();
        mealAreaAdaptor = new MealAreaAdaptor(areas, this);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_home);

        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String userEmail = prefs.getString("EMAIL", "guest@example.com");

        View headerView = navigationView.getHeaderView(0);
        TextView userEmailTextView = headerView.findViewById(R.id.userEmailTextView);
        userEmailTextView.setText(userEmail);

        /* Random MEAL  */

        recyclerView = findViewById(R.id.mealsRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        homePresenter = new HomePresenter(MealRepositoryImp.getInstance(
                MealRemoteDataSourceImpl.getInstance(),
                MealLocalDataSourceImpl.getInstance(this)),
                this);

        homePresenter.getAllMeals();

        /* Categories*/

        categoryRecyclerView = findViewById(R.id.categoryRecyclerView);
        LinearLayoutManager linearLayoutManagerCategories = new LinearLayoutManager(this);
        linearLayoutManagerCategories.setOrientation(RecyclerView.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(linearLayoutManagerCategories);
        categoryRecyclerView.setHasFixedSize(true);

        mealCategoriesPresenter = new MealCategoriesPresenter(MealRepositoryImp.getInstance(
                MealRemoteDataSourceImpl.getInstance(),
                MealLocalDataSourceImpl.getInstance(this)),
                this);
        mealCategoriesPresenter.getAllCategories();

        /*Ingredient*/

        ingredientRecyclerView = findViewById(R.id.ingredientRecyclerView);
        LinearLayoutManager linearLayoutManagerIngredients = new LinearLayoutManager(this);
        linearLayoutManagerIngredients.setOrientation(RecyclerView.HORIZONTAL);
        ingredientRecyclerView.setLayoutManager(linearLayoutManagerIngredients);
        ingredientRecyclerView.setHasFixedSize(true);

        mealIngredientPresenter = new MealIngredientPresenter(MealRepositoryImp.getInstance(
                MealRemoteDataSourceImpl.getInstance(),
                MealLocalDataSourceImpl.getInstance(this)), this);
        mealIngredientPresenter.getAllIngredients();

        /*Area*/


        areaRecyclerView = findViewById(R.id.areaRecyclerView);
        LinearLayoutManager linearLayoutManagerAreas = new LinearLayoutManager(this);
        linearLayoutManagerAreas.setOrientation(RecyclerView.HORIZONTAL);
        areaRecyclerView.setLayoutManager(linearLayoutManagerAreas);
        areaRecyclerView.setHasFixedSize(true);

        mealAreaPresenter = new MealAreaPresenter(MealRepositoryImp.getInstance(
                MealRemoteDataSourceImpl.getInstance(),
                MealLocalDataSourceImpl.getInstance(this)), this);
        mealAreaPresenter.getAllAreas();


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                // Already on home
                drawerLayout.closeDrawer(GravityCompat.START);
            } else if (id == R.id.nav_favorites) {
                startActivity(new Intent(HomeView.this, FavoriteView.class));
            } else if (id == R.id.nav_meal_planner) {
                startActivity(new Intent(HomeView.this, FavoriteView.class));
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(HomeView.this, FavoriteView.class));
            } else if (id == R.id.nav_search) {
                startActivity(new Intent(HomeView.this, SearchViewActivity.class));
            } else if (id == R.id.nav_logout) {
                // Clear session and return to login
                SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                preferences.edit().clear().apply();

                Intent intent = new Intent(HomeView.this, LoginView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

    }


    @Override
    protected void onStart()
    {
        super.onStart();
        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public void showData(List<Meal> meals)
    {
        homeAdapter.setMeals(meals);
        recyclerView.setAdapter(homeAdapter);
    }
    @Override
    public void showError(String message)
    {
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
            homePresenter.insertMeal(meal);
            Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // Remove from favorites
            homePresenter.deleteFromFav(meal);
            Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void showCategories(List<Category> categories)
    {
        mealCategoriesAdapter.setCategories(categories);
        categoryRecyclerView.setAdapter(mealCategoriesAdapter);

    }

    @Override
    public void showCategoriesError(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showIngredient(List<Ingredient> ingredients)
    {
        mealIngredientAdapter.setIngredients(ingredients);
        ingredientRecyclerView.setAdapter(mealIngredientAdapter);

    }

    @Override
    public void showIngredientError(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showArea(List<Area> areas)
    {
        mealAreaAdaptor.setAreas(areas);
        areaRecyclerView.setAdapter(mealAreaAdaptor);
    }

    @Override
    public void showAreError(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}
