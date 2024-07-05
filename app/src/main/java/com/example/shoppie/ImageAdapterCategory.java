package com.example.shoppie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapterCategory extends BaseAdapter {
    private Context mContext;
    private int[] imageArray;

    public ImageAdapterCategory(Context mContext, int[] imageArray) {
        this.mContext = mContext;
        this.imageArray = imageArray;
    }

    @Override
    public int getCount() {
        return imageArray.length;
    }

    @Override
    public Object getItem(int position) {
        return imageArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.gridcategory_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.image_view);
        imageView.setImageResource(imageArray[position]);

        return convertView;
    }
}

