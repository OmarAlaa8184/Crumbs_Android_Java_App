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
import com.example.crumbs_.getRandomMeal.model.mealPojo.Area;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealAreaAdaptor extends RecyclerView.Adapter<MealAreaAdaptor.ViewHolder>
{

    private List<Area> areas;
    private Context context;

    public MealAreaAdaptor(List<Area> areas, Context context) {
        this.areas = areas;
        this.context = context;
    }

    @NonNull
    @Override
    public MealAreaAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View view = inflater.inflate(R.layout.ingredient_item, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealAreaAdaptor.ViewHolder holder, int position)
    {
        Area area=areas.get(position);
        holder.name.setText(area.getArea());

        String areaImageUrl = getAreaFlagUrl(area.getArea());
        Glide.with(context)
                .load(areaImageUrl)
                .placeholder(R.drawable.ic_launcher_background) // Optional: add a placeholder drawable
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return areas.size();
    }

    private String getAreaFlagUrl(String areaName) {
        Map<String, String> areaCountryCodeMap = new HashMap<>();
        areaCountryCodeMap.put("American", "US");
        areaCountryCodeMap.put("British", "GB");
        areaCountryCodeMap.put("Canadian", "CA");
        areaCountryCodeMap.put("Chinese", "CN");
        areaCountryCodeMap.put("Croatian", "HR");
        areaCountryCodeMap.put("Dutch", "NL");
        areaCountryCodeMap.put("Egyptian", "EG");
        areaCountryCodeMap.put("Filipino", "PH");
        areaCountryCodeMap.put("French", "FR");
        areaCountryCodeMap.put("Greek", "GR");
        areaCountryCodeMap.put("Indian", "IN");
        areaCountryCodeMap.put("Irish", "IE");
        areaCountryCodeMap.put("Italian", "IT");
        areaCountryCodeMap.put("Jamaican", "JM");
        areaCountryCodeMap.put("Japanese", "JP");
        areaCountryCodeMap.put("Kenyan", "KE");
        areaCountryCodeMap.put("Malaysian", "MY");
        areaCountryCodeMap.put("Mexican", "MX");
        areaCountryCodeMap.put("Moroccan", "MA");
        areaCountryCodeMap.put("Polish", "PL");
        areaCountryCodeMap.put("Portuguese", "PT");
        areaCountryCodeMap.put("Russian", "RU");
        areaCountryCodeMap.put("Spanish", "ES");
        areaCountryCodeMap.put("Thai", "TH");
        areaCountryCodeMap.put("Tunisian", "TN");
        areaCountryCodeMap.put("Turkish", "TR");
        areaCountryCodeMap.put("Ukrainian", "UA");
        areaCountryCodeMap.put("Uruguayan", "UY");
        areaCountryCodeMap.put("Vietnamese", "VN");

        String countryCode = areaCountryCodeMap.get(areaName);
        if (countryCode != null)
        {
            return "https://flagcdn.com/256x192/" + countryCode.toLowerCase() + ".png";  // 80px width
        }
        else
        {
           return "https://via.placeholder.com/80x50.png?text=" + areaName; // fallback placeholder
        }
    }


    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
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
