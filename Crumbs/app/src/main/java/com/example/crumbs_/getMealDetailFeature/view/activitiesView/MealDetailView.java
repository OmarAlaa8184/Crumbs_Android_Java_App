package com.example.crumbs_.getMealDetailFeature.view.activitiesView;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.crumbs_.R;
import com.example.crumbs_.getMealDetailFeature.presenter.activitiesPresenter.MealDetailPresenter;
import com.example.crumbs_.getMealDetailFeature.view.adaptersView.MealDetailAdapter;
import com.example.crumbs_.getMealDetailFeature.view.interfacesView.MealDetailViewInterface;
import com.example.crumbs_.getRandomMeal.model.db.MealLocalDataSourceImpl;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealRepositoryImp;
import com.example.crumbs_.getRandomMeal.model.network.MealRemoteDataSourceImpl;

import java.util.List;

public class MealDetailView extends AppCompatActivity implements MealDetailViewInterface
{

    TextView mealName, mealCategory, mealArea, mealInstructions;
    ImageView mealThumb;
    RecyclerView ingredientsRecyclerView;
    WebView youtubeWebView;

    MealDetailPresenter mealDetailPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_meal);

        mealName = findViewById(R.id.detailMealName);
        mealCategory = findViewById(R.id.detailMealCategory);
        mealArea = findViewById(R.id.detailMealArea);
        mealInstructions = findViewById(R.id.detailMealInstructions);
        mealThumb = findViewById(R.id.detailMealThumb);
        youtubeWebView = findViewById(R.id.youtubeWebView);
        ingredientsRecyclerView = findViewById(R.id.ingredientsRecyclerView);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        ingredientsRecyclerView.setLayoutManager(linearLayoutManager);
        ingredientsRecyclerView.setHasFixedSize(true);


        mealDetailPresenter=new MealDetailPresenter(MealRepositoryImp.getInstance(MealRemoteDataSourceImpl.getInstance(),
                MealLocalDataSourceImpl.getInstance(this)),this);



        Intent intent = getIntent();
        String name = intent.getStringExtra("MEAL_NAME");
        String category = intent.getStringExtra("MEAL_CATEGORY");
        String area = intent.getStringExtra("MEAL_AREA");
        String instructions = intent.getStringExtra("MEAL_INSTRUCTIONS");
        String thumb = intent.getStringExtra("MEAL_THUMB");
        String youtube = intent.getStringExtra("MEAL_YOUTUBE");
        List<String> ingredients = intent.getStringArrayListExtra("MEAL_INGREDIENTS");

        // Pass to presenter
        mealDetailPresenter.getMealDetails(name, category, area, instructions, thumb, youtube, ingredients);

    }

    @Override
    public void showMealDetails(String name, String category, String area, String instructions, String thumb, String youtube, List<String> ingredients)
    {
        mealName.setText(name);
        mealCategory.setText(category);
        mealArea.setText(area);
        mealInstructions.setText(instructions);

        Glide.with(this).load(thumb).into(mealThumb);

        // Load embedded YouTube video
        if (youtube != null && youtube.contains("watch?v=")) {
            String embedded = youtube.replace("watch?v=", "embed/");
            String html = "<iframe width=\"100%\" height=\"100%\" src=\"" + embedded + "\" frameborder=\"0\" allowfullscreen></iframe>";

            youtubeWebView.getSettings().setJavaScriptEnabled(true);
            youtubeWebView.getSettings().setLoadWithOverviewMode(true);
            youtubeWebView.getSettings().setUseWideViewPort(true);
            youtubeWebView.loadData(html, "text/html", "utf-8");
        }

        // Set ingredients adapter
        MealDetailAdapter adapter = new MealDetailAdapter(ingredients,this);
        ingredientsRecyclerView.setAdapter(adapter);


    }
}
