package com.example.crumbs_.addToFavoriteFeature.view.activitiesView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crumbs_.R;
import com.example.crumbs_.changeOldPasswordFeature.view.activitiesView.ChangeOldPasswordView;
import com.example.crumbs_.getMealDetailFeature.view.activitiesView.MealDetailView;
import com.example.crumbs_.getRandomMeal.model.db.MealLocalDataSourceImpl;
import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealRepositoryImp;
import com.example.crumbs_.getRandomMeal.model.network.MealRemoteDataSourceImpl;
import com.example.crumbs_.addToFavoriteFeature.presenter.activitiesPresenter.FavoritePresenter;
import com.example.crumbs_.getRandomMeal.view.activitiesView.HomeView;
import com.example.crumbs_.addToFavoriteFeature.view.adaptersView.FavoriteAdapter;
import com.example.crumbs_.addToFavoriteFeature.view.interfacesView.FavoriteViewInterface;
import com.example.crumbs_.getRandomMeal.view.listenersView.MealOnClickListener;
import com.example.crumbs_.loginFeature.view.activitiesView.LoginView;
import com.example.crumbs_.logoutFeature.view.LogoutView;
import com.example.crumbs_.mealPlannerFeature.view.activitiesView.PlannerView;
import com.example.crumbs_.searchFeature.view.activitiesView.SearchViewActivity;
import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.Field;
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

        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String userEmail = prefs.getString("EMAIL", "guest@example.com");

        View headerView = navigationView.getHeaderView(0);
        TextView userEmailTextView = headerView.findViewById(R.id.userEmailTextView);
        userEmailTextView.setText(userEmail);


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
                // Already on fav
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            else if (id == R.id.nav_home)
            {
                startActivity(new Intent(FavoriteView.this, HomeView.class));
            }
            else if (id == R.id.nav_meal_planner)
            {
                startActivity(new Intent(FavoriteView.this, PlannerView.class));
            }
            else if (id == R.id.nav_profile)
            {
                startActivity(new Intent(FavoriteView.this, ChangeOldPasswordView.class));
            }
            else if (id == R.id.nav_search)
            {
                startActivity(new Intent(FavoriteView.this, SearchViewActivity.class));
            }
            else if (id == R.id.nav_logout)
            {
                startActivity(new Intent(FavoriteView.this, LogoutView.class));
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
    protected void onStart() {
        super.onStart();
        navigationView.setCheckedItem(R.id.nav_favorites);
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

    @Override
    public void onFavoriteClick1(Meal meal)
    {

        if (meal.isFavorite())
        {
            favoritePresenter.deleteFromFav(meal);
            Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
        }
        else
        {
            meal.setFavorite(!meal.isFavorite());
            favoritePresenter.insertMeal(meal);
            Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
        }
        favoritePresenter.toggleFavorite(meal);

    }

    @Override
    public void onPlannerClick(Meal meal, boolean isPlanned) {

    }
}
