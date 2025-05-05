package com.example.crumbs_.getMealDetailFeature.view.adaptersView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.crumbs_.R;
import com.example.crumbs_.getRandomMeal.model.mealPojo.Meal;

import java.util.List;

public class MealDetailAdapter extends RecyclerView.Adapter<MealDetailAdapter.ViewHolder>
{

    private final List<String> ingredients;
    private final Context context;

    public MealDetailAdapter(List<String> ingredients, Context context) {
        this.ingredients = ingredients;
        this.context = context;
    }


    @NonNull
    @Override
    public MealDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(recyclerView.getContext());
        View view=layoutInflater.inflate(R.layout.ingredient_item,recyclerView,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MealDetailAdapter.ViewHolder viewHolder, int position) {

        String ingredient = ingredients.get(position);
        viewHolder.ingredientText.setText(ingredient);

        // Image URL format from TheMealDB
        String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredient + ".png";

        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background) // Optional: add a placeholder drawable
                .error(R.drawable.ic_launcher_foreground)
                .into(viewHolder.ingredientImage);

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView ingredientImage;

        public TextView ingredientText;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            ingredientImage=itemView.findViewById(R.id.ingredientImage);
            ingredientText=itemView.findViewById(R.id.ingredientText);
        }
    }
}
