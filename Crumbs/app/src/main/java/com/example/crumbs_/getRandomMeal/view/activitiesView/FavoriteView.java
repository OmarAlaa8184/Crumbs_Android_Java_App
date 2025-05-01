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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crumbs_.R;
import com.example.crumbs_.getRandomMeal.model.db.MealLocalDataSourceImpl;
import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealRepositoryImp;
import com.example.crumbs_.getRandomMeal.model.network.MealRemoteDataSourceImpl;
import com.example.crumbs_.getRandomMeal.presenter.activitiesPresenter.FavoritePresenter;
import com.example.crumbs_.getRandomMeal.presenter.activitiesPresenter.HomePresenter;
import com.example.crumbs_.getRandomMeal.view.adaptersView.FavoriteAdapter;
import com.example.crumbs_.getRandomMeal.view.adaptersView.HomeAdapter;
import com.example.crumbs_.getRandomMeal.view.interfacesView.FavoriteViewInterface;
import com.example.crumbs_.getRandomMeal.view.listenersView.MealOnClickListener;
import com.example.crumbs_.loginFeature.view.activitiesView.LoginView;
import com.example.crumbs_.splashFeature.view.activtiesView.SplashView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class FavoriteView extends AppCompatActivity implements FavoriteViewInterface, MealOnClickListener
{
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private FavoriteAdapter favoriteAdapter;

    private static final String KEY_MEAL = "key_meal";
    private List<Meal> meals;

    private FavoritePresenter favoritePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favscreen);
        meals = new ArrayList<>();
        favoriteAdapter = new FavoriteAdapter(meals, this, this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_favorites);

        recyclerView = findViewById(R.id.mealsRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(favoriteAdapter);


        favoritePresenter = new FavoritePresenter(MealRepositoryImp.getInstance(
                MealRemoteDataSourceImpl.getInstance(),
                MealLocalDataSourceImpl.getInstance(this)),
                this);

        favoritePresenter.getFavMeal();


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_favorites)
            {
                // Already on home
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            else if (id == R.id.nav_home)
            {
                startActivity(new Intent(FavoriteView.this, HomeView.class));
            }
            else if (id == R.id.nav_meal_planner)
            {
                startActivity(new Intent(FavoriteView.this, FavoriteView.class));
            }
            else if (id == R.id.nav_profile)
            {
                startActivity(new Intent(FavoriteView.this, FavoriteView.class));
            }
            else if (id == R.id.nav_logout)
            {
                // Clear session and return to login
                SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                preferences.edit().clear().apply();

                Intent intent = new Intent(FavoriteView.this, LoginView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }


            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

    }

    @Override
    public void showData(LiveData<List<Meal>> meals)
    {
        meals.observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                favoriteAdapter.setMeals(meals);
                favoriteAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void showError(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMealClick(Meal meal) {

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
            // Remove from favorites
            favoritePresenter.deleteFromFav(meal);
            Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // Add to favorites
            favoritePresenter.insertMeal(meal);
            Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
        }
    }
}
