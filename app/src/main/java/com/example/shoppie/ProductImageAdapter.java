package com.example.shoppie;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ProductImageAdapter extends PagerAdapter{

    private List<Integer> productImages;

    public ProductImageAdapter(List<Integer> productImages){
        this.productImages = productImages;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView productImage = new ImageView(container.getContext());  //create image view
        productImage.setImageResource(productImages.get(position)); // set resources for image
        container.addView(productImage,0);   // added in container
        return productImage;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);  //remove image view
    }

    @Override
    public int getCount() {
        return productImages.size();  // list size
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
