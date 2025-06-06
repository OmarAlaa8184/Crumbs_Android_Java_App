package com.example.crumbs_.addToFavoriteFeature.view.adaptersView;

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
import com.example.crumbs_.getRandomMeal.view.listenersView.MealOnClickListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>
{

    public List<Meal> meals;

    private Context context;

    private MealOnClickListener mealOnClickListener;

    private Set<String> favoriteMealIds = new HashSet<>();

    public FavoriteAdapter(List<Meal> meals, Context context, MealOnClickListener mealOnClickListener) {
        this.meals = meals;
        this.context = context;
        this.mealOnClickListener = mealOnClickListener;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View view = inflater.inflate(R.layout.item_meal, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder viewHolder, int position)
    {
        Meal meal=meals.get(position);

        viewHolder.mealArea.setText(meal.getStrArea());
        viewHolder.mealCategory.setText(meal.getStrCategory());
        viewHolder.mealName.setText(meal.getStrMeal());

        Glide.with(context)
                .load(meal.getStrMealThumb())
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                .into(viewHolder.mealImage);

        viewHolder.youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mealOnClickListener.onYoutubeClick(meal.getStrYoutube());
            }
        });
        viewHolder.mealImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealOnClickListener.onMealClick(meal);
            }
        });


        boolean isFavorite = favoriteMealIds.contains(meal.getIdMeal());

        viewHolder.favoriteButton.setImageResource(isFavorite ?  R.drawable.ic_favorite_border :R.drawable.ic_favorite_filled );

        viewHolder.favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                boolean newFavoriteState = !favoriteMealIds.contains(meal.getIdMeal());

                if (newFavoriteState)
                {
                    favoriteMealIds.remove(meal.getIdMeal());
                    viewHolder.favoriteButton.setImageResource(R.drawable.ic_favorite_border);
                }
                else
                {
                    favoriteMealIds.add(meal.getIdMeal());
                    viewHolder.favoriteButton.setImageResource(R.drawable.ic_favorite_filled);
                }

                // Notify your database/presenter about the change
                mealOnClickListener.onFavoriteClick(meal, newFavoriteState);
            }

        });
       /* viewHolder.favoriteButton.setImageResource(meal.isFavorite() ? R.drawable.ic_favorite_filled: R.drawable.ic_favorite_border  );

        viewHolder.favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                if (meal.isFavorite())
                {
                    viewHolder.favoriteButton.setImageResource(R.drawable.ic_favorite_border);
                }
                else
                {
                    viewHolder.favoriteButton.setImageResource(R.drawable.ic_favorite_filled);
                }
                mealOnClickListener.onFavoriteClick1(meal);
            }

        });*/

    }

    @Override
    public int getItemCount()
    {
           return meals.size();
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals != null ? meals : new ArrayList<>();
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView mealImage;
        TextView mealName, mealCategory, mealArea;
        ImageButton youtubeButton,favoriteButton;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mealImage = itemView.findViewById(R.id.mealImage);
            mealName = itemView.findViewById(R.id.mealName);
            mealCategory = itemView.findViewById(R.id.mealCategory);
            mealArea = itemView.findViewById(R.id.mealArea);
            youtubeButton = itemView.findViewById(R.id.youtubeButton);
            favoriteButton=itemView.findViewById(R.id.favoriteButton);
        }
    }
}
