package com.example.crumbs_.getRandomMeal.view.adaptersView;

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
import com.example.crumbs_.getMealDetailFeature.view.adaptersView.MealDetailAdapter;
import com.example.crumbs_.getRandomMeal.model.mealPojo.Ingredient;

import java.util.List;

public class MealIngredientAdapter extends RecyclerView.Adapter<MealIngredientAdapter.ViewHolder>
{

    private List<Ingredient> ingredients;
    private Context context;

    public MealIngredientAdapter(List<Ingredient> ingredients, Context context) {
        this.ingredients = ingredients;
        this.context = context;
    }

    @NonNull
    @Override
    public MealIngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType)
    {
        LayoutInflater layoutInflater=LayoutInflater.from(recyclerView.getContext());
        View view=layoutInflater.inflate(R.layout.ingredient_item,recyclerView,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealIngredientAdapter.ViewHolder viewHolder, int position)
    {
        Ingredient ingredient=ingredients.get(position);
        viewHolder.ingredientText.setText(ingredient.getStrIngredient());

        String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredient.getStrIngredient() + ".png";


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

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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
