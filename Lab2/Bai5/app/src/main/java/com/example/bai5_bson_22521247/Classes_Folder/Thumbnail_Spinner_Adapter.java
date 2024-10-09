package com.example.bai5_bson_22521247.Classes_Folder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bai5_bson_22521247.R;

public class Thumbnail_Spinner_Adapter extends BaseAdapter {
    private Context context;
    private int[] thumbnails;
    private String[] names;

    public Thumbnail_Spinner_Adapter(Context context, int[] thumbnails, String[] names) {
        this.context = context;
        this.thumbnails = thumbnails;
        this.names = names;
    }

    @Override
    public int getCount() {
        return thumbnails.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_item_thumbnail, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageViewThumbnail);
        TextView textViewName = convertView.findViewById(R.id.textViewThumbnailName);

        imageView.setImageResource(thumbnails[position]);
        textViewName.setText(names[position]);

        return convertView;
    }
}
