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
import com.example.crumbs_.getRandomMeal.model.mealPojo.Category;

import java.util.List;

public class MealCategoriesAdapter extends RecyclerView.Adapter<MealCategoriesAdapter.ViewHolder>
{
    private List<Category> categories;

    private Context context;

    public MealCategoriesAdapter(List<Category> categories, Context context)
    {
        this.categories = categories;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View view = inflater.inflate(R.layout.ingredient_item, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealCategoriesAdapter.ViewHolder viewHolder, int position)
    {
        Category category = categories.get(position);
        viewHolder.name.setText(category.getStrCategory());

        Glide.with(context)
                .load(category.getStrCategoryThumb())
                .placeholder(R.drawable.logo) // Optional: add a placeholder drawable
                .error(R.drawable.ic_launcher_background)
                .into(viewHolder.image);

    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();

    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView name;
        ImageView image;

        ViewHolder(View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.ingredientText);
            image = itemView.findViewById(R.id.ingredientImage);
        }
    }
}
