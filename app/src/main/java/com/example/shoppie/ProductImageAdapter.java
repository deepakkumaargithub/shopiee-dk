package com.example.shoppie;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ProductImageAdapter extends PagerAdapter {

    private List<Integer> productImages;

    public ProductImageAdapter(List<Integer> productImages) {
        this.productImages = productImages;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView productImage = new ImageView(container.getContext()); // Create ImageView
        productImage.setImageResource(productImages.get(position)); // Set image resource

        // Add content description for accessibility
        productImage.setContentDescription("Product image " + (position + 1));

        // Set layout parameters to match parent (optional, if not set through XML)
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        productImage.setLayoutParams(layoutParams);

        container.addView(productImage); // Add ImageView to container
        return productImage;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object); // Remove ImageView from container
    }

    @Override
    public int getCount() {
        return productImages.size(); // Return size of the images list
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object; // Return if view equals object
    }
}