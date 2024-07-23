package com.example.shoppie;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager productImagesViewPager;
    private TabLayout viewpagerIndicator;

    private ViewPager productDetailsViewPager;
    private TabLayout productDetailsTabLayout;

    private View sellerInfo;
    private View product_price_title;

    private Product productObject;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // Retrieve the product object passed from another activity
        productObject = getIntent().getParcelableExtra("object", Product.class);

        // Initialize views
        productImagesViewPager = findViewById(R.id.product_images_viewPager);
        viewpagerIndicator = findViewById(R.id.view_pager_indicator);
        productDetailsViewPager = findViewById(R.id.product_details_view_pager);
        productDetailsTabLayout = findViewById(R.id.product_details_tab_layout);
        sellerInfo = findViewById(R.id.product_details_seller_info_layout);
        product_price_title = findViewById(R.id.product_details_image_layout);





        // Update seller info
        TextView sellerDetails = sellerInfo.findViewById(R.id.seller_info_details);
        sellerDetails.setText(productObject.getSellerInfo());

        //Update price and title
        TextView price = product_price_title.findViewById(R.id.product_price);
        price.setText(productObject.getPrice());
        TextView ProductTitle = product_price_title.findViewById(R.id.product_title);
        ProductTitle.setText(productObject.getTitle());

//        //update rating
//        RatingBar ratingBar  = ratingInfo.findViewById(R.id.ratingBar);
//        ratingBar.setRating(productObject.getRating());










        // Get the list of image URLs from the product object
        List<String> productImageUrls = productObject.getImagesUrls();

        // Create and set up the ProductImageAdapter with dynamic image URLs
        ProductImageAdapter productImageAdapter = new ProductImageAdapter(this, productImageUrls);
        productImagesViewPager.setAdapter(productImageAdapter);

        // Set up the TabLayout to synchronize with the ViewPager
        viewpagerIndicator.setupWithViewPager(productImagesViewPager, true);

        // Set up the ProductDetailsViewPager and TabLayout for product details
        productDetailsViewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(), productDetailsTabLayout.getTabCount()));
        productDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));
        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Handle tab unselected if needed
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Handle tab reselected if needed
            }
        });
    }
}
