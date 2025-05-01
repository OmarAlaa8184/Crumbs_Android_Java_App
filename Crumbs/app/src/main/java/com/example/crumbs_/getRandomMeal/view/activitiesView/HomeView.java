package com.example.crumbs_.getRandomMeal.view.activitiesView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crumbs_.R;
import com.example.crumbs_.getRandomMeal.model.db.MealLocalDataSourceImpl;
import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealRepositoryImp;
import com.example.crumbs_.getRandomMeal.model.network.MealRemoteDataSourceImpl;
import com.example.crumbs_.getRandomMeal.presenter.activitiesPresenter.HomePresenter;
import com.example.crumbs_.getRandomMeal.view.adaptersView.HomeAdapter;
import com.example.crumbs_.getRandomMeal.view.interfacesView.HomeViewInterface;
import com.example.crumbs_.getRandomMeal.view.listenersView.MealOnClickListener;
import com.example.crumbs_.loginFeature.view.activitiesView.LoginView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeView extends AppCompatActivity implements HomeViewInterface, MealOnClickListener
{

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;

    private static final String KEY_MEAL = "key_meal";
    private List<Meal> meals;

    private HomePresenter homePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen);
        meals = new ArrayList<>();
        homeAdapter = new HomeAdapter(meals, this, this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_home);

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


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home)
            {
                // Already on home
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            else if (id == R.id.nav_favorites)
            {
                startActivity(new Intent(HomeView.this, FavoriteView.class));
            }
            else if (id == R.id.nav_meal_planner)
            {
                startActivity(new Intent(HomeView.this, FavoriteView.class));
            }
            else if (id == R.id.nav_profile)
            {
                startActivity(new Intent(HomeView.this, FavoriteView.class));
            }
            else if (id == R.id.nav_logout)
            {
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
}
