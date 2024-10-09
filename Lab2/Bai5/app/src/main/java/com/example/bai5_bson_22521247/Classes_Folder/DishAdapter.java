package com.example.bai5_bson_22521247.Classes_Folder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bai5_bson_22521247.R;

import java.util.List;

public class DishAdapter extends BaseAdapter {
    private Context context;
    private List<Dish> dishList;
    private LayoutInflater inflater;

    public DishAdapter(Context context, List<Dish> dishList) {
        this.context = context;
        this.dishList = dishList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dishList.size();
    }

    @Override
    public Object getItem(int position) {
        return dishList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item_dish, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageViewThumbnail);
        TextView textViewName = convertView.findViewById(R.id.textViewDishName);
        ImageView imageViewPromotion = convertView.findViewById(R.id.imageViewPromotion);

        Dish dish = dishList.get(position);
        imageView.setImageResource(dish.getThumbnail());
        textViewName.setText(dish.getName());

        if (dish.isPromotion()) {
            imageViewPromotion.setVisibility(View.VISIBLE);
        } else {
            imageViewPromotion.setVisibility(View.GONE);
        }

        return convertView;
    }
}
