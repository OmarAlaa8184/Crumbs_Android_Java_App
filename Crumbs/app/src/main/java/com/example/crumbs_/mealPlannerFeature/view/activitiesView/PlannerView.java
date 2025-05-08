package com.example.crumbs_.mealPlannerFeature.view.activitiesView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crumbs_.R;
import com.example.crumbs_.getRandomMeal.model.db.MealPlannerLocalDataSourceImpl;
import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealPlanner;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealPlannerRepository;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealPlannerRepositoryImp;
import com.example.crumbs_.getRandomMeal.model.network.MealRemoteDataSourceImpl;
import com.example.crumbs_.getRandomMeal.view.adaptersView.HomeAdapter;
import com.example.crumbs_.getRandomMeal.view.listenersView.MealOnClickListener;
import com.example.crumbs_.mealPlannerFeature.presenter.activitiesPresenter.PlannerPresenter;
import com.example.crumbs_.mealPlannerFeature.view.adaptersView.PlannerAdapter;
import com.example.crumbs_.mealPlannerFeature.view.interfacesView.PlannerViewInterface;
import com.example.crumbs_.mealPlannerFeature.view.listenersView.MealPlannerOnClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PlannerView extends AppCompatActivity implements PlannerViewInterface, MealPlannerOnClickListener
{
    private PlannerPresenter plannerPresenter;
    private CalendarView calendarView;
    private RecyclerView rvMealPlan;
    private PlannerAdapter  plannerAdapter;
    private Date selectedDate;
    private List<MealPlanner> meals;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealplanneractivity);

        meals=new ArrayList<>();
        plannerAdapter = new PlannerAdapter(meals, this, this);
        Intent intent = getIntent();
        Meal meal= (Meal) intent.getSerializableExtra("MEAL");
        if (meal==null)
        {
            Toast.makeText(this, "No meal data received", Toast.LENGTH_SHORT).show();
            Log.e("PlannerView", "Meal is null from Intent");

        }
        calendarView = findViewById(R.id.calendarView);
        rvMealPlan = findViewById(R.id.rvMealPlan);
        Button btnRandomMeal = findViewById(R.id.btnRandomMeal);
        Button btnFilterByCategory = findViewById(R.id.btnFilterByCategory);
        Button btnClearMealPlan = findViewById(R.id.btnClearMealPlan);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvMealPlan.setLayoutManager(linearLayoutManager);
        rvMealPlan.setHasFixedSize(true);

        plannerPresenter=new PlannerPresenter(this,
                             MealPlannerRepositoryImp.getInstance(MealRemoteDataSourceImpl.getInstance(),
                             MealPlannerLocalDataSourceImpl.getInstance(this)));

        plannerPresenter.getPlannedMeal();

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = new Date(year - 1900, month, dayOfMonth);
            plannerPresenter.getPlannedMeal();
        });

        calendarView.setMinDate(System.currentTimeMillis() - 1000);
        calendarView.setMaxDate(new Date(2025 - 1900, 11, 31).getTime());

        btnRandomMeal.setOnClickListener(v -> {
            if (selectedDate != null)
            {
                plannerPresenter.assignRandomMealToDate(selectedDate);
            }
            else
            {
                Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show();
            }
        });

        btnFilterByCategory.setOnClickListener(v -> {
            if (selectedDate != null)
            {
              if(meal==null)
               {
                Toast.makeText(this, "No Meal Found", Toast.LENGTH_SHORT).show();

                }
              else
              {
                MealPlanner mealPlanner=new MealPlanner( selectedDate.getTime(),meal.getIdMeal(),meal.getStrMeal(),meal.getStrInstructions(),meal.getStrMealThumb(),meal.getStrYoutube(),meal.getStrCategory(),meal.getStrArea());
                plannerPresenter.insertMeal(mealPlanner);

              }
            }
            else
            {
                Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show();
            }
        });

        btnClearMealPlan.setOnClickListener(v -> plannerPresenter.deleteAll());


    }



    @Override
    public void showMealPlan(LiveData<List<MealPlanner>> mealPlan)
    {

        mealPlan.observe(this, mealPlanners -> {
            List<MealPlanner> filteredPlanners = new ArrayList<>();
            if (mealPlanners != null) {
                for (MealPlanner mp : mealPlanners) {
                    if (selectedDate != null && isSameDay(mp.getDate(), selectedDate.getTime())) {
                        filteredPlanners.add(mp);
                    }
                }
            }
            plannerAdapter.updatePlan(filteredPlanners);
            rvMealPlan.setAdapter(plannerAdapter);
            plannerAdapter.notifyDataSetChanged();
        });


    }

    @Override
    public void showError(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private List<MealPlanner> filterMealsBySelectedDate(List<MealPlanner> allMeals, Date selectedDate) {
        List<MealPlanner> filtered = new ArrayList<>();
        if (selectedDate == null) return filtered;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String selectedDateStr = sdf.format(selectedDate);

        for (MealPlanner meal : allMeals) {
            String mealDateStr = sdf.format(new Date(meal.getDate()));
            if (mealDateStr.equals(selectedDateStr)) {
                filtered.add(meal);
            }
        }
        return filtered;
    }
    private boolean isSameDay(long date1, long date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
        return sdf.format(new Date(date1)).equals(sdf.format(new Date(date2)));
    }

    @Override
    public void onMealClick(MealPlanner mealPlanner) {

    }

    @Override
    public void onYoutubeClick(String youtubeUrl) {

    }

    @Override
    public void onFavoriteClick(MealPlanner mealPlanner, boolean isFavorite)
    {


    }

    @Override
    public void onPlannerClick(MealPlanner mealPlanner, boolean isPlanned)
    {

        if (isPlanned)
        {
            // Remove from favorites
            plannerPresenter.deleteFromPlannedByDate(mealPlanner.getDate());
            Toast.makeText(this, "Removed from this day", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // Add to favorites
            plannerPresenter.insertMeal(mealPlanner);
            Toast.makeText(this, "Added to this day", Toast.LENGTH_SHORT).show();

        }

    }
}
