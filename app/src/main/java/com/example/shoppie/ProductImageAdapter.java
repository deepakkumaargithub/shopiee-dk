package com.example.shoppie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductImageAdapter extends PagerAdapter {

    private List<String> imageUrls; // List of image URLs
    private Context context; // Context for loading images

    // Constructor to initialize image URLs and context
    public ProductImageAdapter(Context context, List<String> imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // Create a new ImageView
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER); // Optional: Scale type for images

        // Use Glide to load the image from the URL
        Glide.with(context)
                .load(imageUrls.get(position)) // Load image from URL
                .error(R.drawable.loc_access_logo) // Optional: error image if loading fails
                .into(imageView);

        // Add content description for accessibility
        imageView.setContentDescription("Product image " + (position + 1));

        // Set layout parameters to match parent (optional, if not set through XML)
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        imageView.setLayoutParams(layoutParams);

        // Add ImageView to the container
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // Remove ImageView from container
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return imageUrls.size(); // Return the size of the image URLs list
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object); // Return if view equals object
    }
}
