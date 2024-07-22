package com.example.shoppie;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
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

    private View sellerInfo;

    private Product productObject;
// api supressor added remove if doesnt work.

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productObject = getIntent().getParcelableExtra("object",Product.class);

        productImagesViewPager = findViewById(R.id.product_images_viewPager);
        viewpagerIndicator = findViewById(R.id.view_pager_indicator);

        productDetailsViewPager = findViewById(R.id.product_details_view_pager);
        productDetailsTabLayout = findViewById(R.id.product_details_tab_layout);


        //update information and object according to json data.
        sellerInfo = findViewById(R.id.product_details_seller_info_layout);
        TextView sellerDetails =  sellerInfo.findViewById(R.id.seller_info_details);
        sellerDetails.setText(productObject.getTitle());


        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.ac1);
        productImages.add(R.drawable.laptop2);
        productImages.add(R.drawable.fridge1);
        productImages.add(R.drawable.speaker1);

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