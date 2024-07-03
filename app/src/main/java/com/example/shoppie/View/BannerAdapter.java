package com.example.shoppie.View;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.shoppie.R;
import com.example.shoppie.ModelClass.Banner;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BannerAdapter extends PagerAdapter {

    private Context context;
    private List<Banner> bannerImages;

    public BannerAdapter(HomePageActivity context, List<Banner> bannerImages) {
        this.context = context;
        this.bannerImages = bannerImages;
    }

    @Override
    public int getCount() {
        return bannerImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item, container, false);
        ImageView imageView = view.findViewById(R.id.bannerImageView);
        Picasso.get().load(bannerImages.get(position).getImageUrl()).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

