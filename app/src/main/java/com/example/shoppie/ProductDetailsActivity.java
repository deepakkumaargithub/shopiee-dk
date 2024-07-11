package com.example.shoppie;

import android.os.Bundle;
import android.widget.TableLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager productImagesViewPager;
    private TabLayout viewpagerIndicator;

    private ViewPager productDetailsViewPager;
    private TabLayout productDetailsTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productImagesViewPager = findViewById(R.id.product_images_viewPager);
        viewpagerIndicator = findViewById(R.id.view_pager_indicator);

        productDetailsViewPager = findViewById(R.id.product_details_view_pager);
        productDetailsTabLayout = findViewById(R.id.product_details_tab_layout);

        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.loc_logo);
        productImages.add(R.drawable.loc_logo);
        productImages.add(R.drawable.loc_logo);
        productImages.add(R.drawable.loc_logo);

        ProductImageAdapter productImageAdapter = new ProductImageAdapter(productImages);
        productImagesViewPager.setAdapter(productImageAdapter);

        viewpagerIndicator.setupWithViewPager(productImagesViewPager,true);


        productDetailsViewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(), productDetailsTabLayout.getTabCount()));
        productDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));
        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}