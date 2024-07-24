package com.example.shoppie;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager productImagesViewPager;
    private TabLayout viewpagerIndicator;

    private View sellerInfo;
    private View product_price_title;
    private View product_description_details;
    private View product_specification_details;
    private View product_other_details;

    private LinearLayout expandableLayoutDescription;
    private LinearLayout expandableLayoutSpecification;
    private LinearLayout expandableLayoutOtherDetails;
    private ImageButton toggleButtonDescription;
    private ImageButton toggleButtonSpecification;
    private ImageButton toggleButtonOtherDetails;

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

        // Initialize other views
        sellerInfo = findViewById(R.id.product_details_seller_info_layout);
        product_price_title = findViewById(R.id.product_details_image_layout);
        product_description_details = findViewById(R.id.description_section);
        product_specification_details = findViewById(R.id.specification_section);
        product_other_details = findViewById(R.id.other_details_section);

        // Ensure that the included layout is properly inflated and accessed
        //for description button up and down.
        View descriptionSection = findViewById(R.id.description_section);

        if (descriptionSection != null) {
            expandableLayoutDescription = descriptionSection.findViewById(R.id.expandable_layout_description);
            toggleButtonDescription = descriptionSection.findViewById(R.id.toggle_button_description);

            if (toggleButtonDescription != null) {
                toggleButtonDescription.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (expandableLayoutDescription.getVisibility() == View.GONE) {
                            expandableLayoutDescription.setVisibility(View.VISIBLE);
                            toggleButtonDescription.setImageResource(R.drawable.up_arrow);
                        } else {
                            expandableLayoutDescription.setVisibility(View.GONE);
                            toggleButtonDescription.setImageResource(R.drawable.arrow_down);
                        }
                    }
                });
            } 
        }

        // for specification button down and up.
        View specificationSection = findViewById(R.id.specification_section);

        if (specificationSection != null) {
            expandableLayoutSpecification = specificationSection.findViewById(R.id.expandable_layout_specification);
            toggleButtonSpecification = specificationSection.findViewById(R.id.toggle_button_specification);

            if (toggleButtonSpecification != null) {
                toggleButtonSpecification.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (expandableLayoutSpecification.getVisibility() == View.GONE) {
                            expandableLayoutSpecification.setVisibility(View.VISIBLE);
                            toggleButtonSpecification.setImageResource(R.drawable.up_arrow);
                        } else {
                            expandableLayoutSpecification.setVisibility(View.GONE);
                            toggleButtonSpecification.setImageResource(R.drawable.arrow_down);
                        }
                    }
                });
            }
        }




        // for specification button down and up.
        View otherDetailsSection = findViewById(R.id.other_details_section);

        if (otherDetailsSection != null) {
            expandableLayoutOtherDetails = otherDetailsSection.findViewById(R.id.expandable_layout_other_details);
            toggleButtonOtherDetails = otherDetailsSection.findViewById(R.id.toggle_button_other_details);

            if (toggleButtonOtherDetails != null) {
                toggleButtonOtherDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (expandableLayoutOtherDetails.getVisibility() == View.GONE) {
                            expandableLayoutOtherDetails.setVisibility(View.VISIBLE);
                            toggleButtonOtherDetails.setImageResource(R.drawable.up_arrow);
                        } else {
                            expandableLayoutOtherDetails.setVisibility(View.GONE);
                            toggleButtonOtherDetails.setImageResource(R.drawable.arrow_down);
                        }
                    }
                });
            }
        }




        // Update seller info
        TextView sellerDetails = sellerInfo.findViewById(R.id.seller_info_details);
        sellerDetails.setText(productObject.getSellerInfo());

        // Update price and title
        TextView price = product_price_title.findViewById(R.id.product_price);
        price.setText(productObject.getPrice());

        TextView ProductTitle = product_price_title.findViewById(R.id.product_title);
        ProductTitle.setText(productObject.getTitle());

        // Update description details
        TextView description_txt = product_description_details.findViewById(R.id.descriptionTEXT);
        description_txt.setText(Html.fromHtml(productObject.getDescription(), Html.FROM_HTML_MODE_COMPACT));

        // Update specification details
        TextView specification_txt = product_specification_details.findViewById(R.id.specificationTEXT);
        specification_txt.setText(Html.fromHtml(productObject.getSpecification(), Html.FROM_HTML_MODE_COMPACT));

        // Update other details
        TextView other_details_txt = product_other_details.findViewById(R.id.other_detailsTEXT);
        other_details_txt.setText(Html.fromHtml(productObject.getOtherDetails(), Html.FROM_HTML_MODE_COMPACT));




        // Get the list of image URLs from the product object
        List<String> productImageUrls = productObject.getImagesUrls();

        // Create and set up the ProductImageAdapter with dynamic image URLs
        ProductImageAdapter productImageAdapter = new ProductImageAdapter(this, productImageUrls);
        productImagesViewPager.setAdapter(productImageAdapter);

        // Set up the TabLayout to synchronize with the ViewPager
        viewpagerIndicator.setupWithViewPager(productImagesViewPager, true);
    }
}
