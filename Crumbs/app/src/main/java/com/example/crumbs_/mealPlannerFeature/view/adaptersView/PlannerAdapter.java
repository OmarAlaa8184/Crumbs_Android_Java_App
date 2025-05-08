package com.example.crumbs_.mealPlannerFeature.view.adaptersView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.crumbs_.R;
import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;
import com.example.crumbs_.getRandomMeal.model.mealPojo.MealPlanner;
import com.example.crumbs_.getRandomMeal.view.adaptersView.HomeAdapter;
import com.example.crumbs_.getRandomMeal.view.listenersView.MealOnClickListener;
import com.example.crumbs_.mealPlannerFeature.view.listenersView.MealPlannerOnClickListener;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class PlannerAdapter extends RecyclerView.Adapter<PlannerAdapter.ViewHolder> {

    public List<MealPlanner> meals;

    private Context context;

    private MealPlannerOnClickListener mealPlannerOnClickListener;

    private Set<String> favoriteMealIds = new HashSet<>();
    private Set<String> plannedMealIds = new HashSet<>();

    public PlannerAdapter(List<MealPlanner> meals, MealPlannerOnClickListener mealPlannerOnClickListener, Context context) {
        this.meals = meals;
        this.mealPlannerOnClickListener = mealPlannerOnClickListener;
        this.context = context;
    }


    @NonNull
    @Override
    public PlannerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int i) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View view = inflater.inflate(R.layout.planner_item, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlannerAdapter.ViewHolder holder, int position)
    {

        MealPlanner mealPlanner = meals.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        holder.tvDate.setText(sdf.format(new java.util.Date(mealPlanner.getDate())));
        holder.tvMealName.setText(mealPlanner.getMealName());

       /* holder.btnYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mealPlannerOnClickListener.onYoutubeClick(mealPlanner.getMealYoutube());
            }
        });*/

        holder.ivMealImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealPlannerOnClickListener.onMealClick(mealPlanner);
            }
        });

        boolean isFavorite = favoriteMealIds.contains(mealPlanner.getMealId());

      /* holder.btnFavorite.setImageResource(isFavorite ? R.drawable.ic_favorite_filled : R.drawable.ic_favorite_border);

        holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                boolean newFavoriteState = !favoriteMealIds.contains(mealPlanner.getMealId());

                if (newFavoriteState)
                {
                    favoriteMealIds.add(mealPlanner.getMealId());
                    holder.btnFavorite.setImageResource(R.drawable.ic_favorite_filled);
                }
                else
                {
                    favoriteMealIds.remove(mealPlanner.getMealId());
                    holder.btnFavorite.setImageResource(R.drawable.ic_favorite_border);
                }

                // Notify your database/presenter about the change
                mealPlannerOnClickListener.onFavoriteClick(mealPlanner, newFavoriteState);
            }

        });*/

        boolean isPlanned = plannedMealIds.contains(mealPlanner.getMealId());

        holder.plannerButton.setImageResource(isPlanned ?  R.drawable.ic_calendar_border :R.drawable.ic_clander_filled );


        holder.plannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean newPlannedState = !plannedMealIds.contains(mealPlanner.getMealId());

                if (newPlannedState)
                {
                    plannedMealIds.remove(mealPlanner.getMealId());
                    holder.plannerButton.setImageResource(R.drawable.ic_calendar_border);
                }
                else
                {
                    plannedMealIds.add(mealPlanner.getMealId());
                    holder.plannerButton.setImageResource(R.drawable.ic_clander_filled);

                }

                // Notify your database/presenter about the change
                mealPlannerOnClickListener.onPlannerClick(mealPlanner, newPlannedState);
            }
        });



        Glide.with(context)
                .load(mealPlanner.getMealImageUrl())
                .placeholder(R.drawable.logo)
                .error(R.drawable.ic_launcher_background)
                .into(holder.ivMealImage);

    }


    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void updatePlan(List<MealPlanner> mealPlan) {
        this.meals = mealPlan;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivMealImage;
        TextView tvMealName,tvDate;
        ImageButton plannerButton;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvMealName = itemView.findViewById(R.id.mealName);
            ivMealImage = itemView.findViewById(R.id.mealImage);
          //  btnYoutube = itemView.findViewById(R.id.youtubeButton);
          //  btnFavorite = itemView.findViewById(R.id.favoriteButton);
            plannerButton=itemView.findViewById(R.id.plannerButton);
        }
    }
}
