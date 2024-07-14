package com.example.shoppie;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppie.View.HomePageActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
//for fetching category image
import com.bumptech.glide.Glide;


public class AllCategoryActivity extends AppCompatActivity {


    private RecyclerView recyclerView; // RecyclerView to display the products
    private ProductAdapter productAdapter; // Adapter for the RecyclerView
    private List<Product> productList; // List to hold the products
    private TextView resultText; // TextView to show the total product count

    ImageView backArrow; // ImageView for the back arrow


    //for Category
    private List<ImageView> categoryImageView;
    private List<TextView> titleTexts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_category);


        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        backArrow = findViewById(R.id.back_arrow);

        // Set click listener on the back arrow to navigate to the home page
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_HomePage = new Intent(AllCategoryActivity.this, HomePageActivity.class);
                startActivity(go_to_HomePage);
                finish();
            }
        });

        //for category
        categoryImageView = new ArrayList<>();
        titleTexts = new ArrayList<>();

        categoryImageView.add(findViewById(R.id.category_image1));
        categoryImageView.add(findViewById(R.id.category_image2));
        categoryImageView.add(findViewById(R.id.category_image3));
        categoryImageView.add(findViewById(R.id.category_image4));
        categoryImageView.add(findViewById(R.id.category_image5));
        categoryImageView.add(findViewById(R.id.category_image6));
        categoryImageView.add(findViewById(R.id.category_image7));
        categoryImageView.add(findViewById(R.id.category_image8));

        titleTexts.add(findViewById(R.id.category_title1));
        titleTexts.add(findViewById(R.id.category_title2));
        titleTexts.add(findViewById(R.id.category_title3));
        titleTexts.add(findViewById(R.id.category_title4));
        titleTexts.add(findViewById(R.id.category_title5));
        titleTexts.add(findViewById(R.id.category_title6));
        titleTexts.add(findViewById(R.id.category_title7));
        titleTexts.add(findViewById(R.id.category_title8));


        fetchCategoryData();


        recyclerView = findViewById(R.id.recycler_view_allCategory);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // Set layout manager for the RecyclerView

        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);
        // Fetch data from Firebase
        database.getReference("categories")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        productList.clear();
                      //  int totalProducts = 0; // Counter for total products

                        // Iterate through all categories
                        for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                            // Iterate through all subcategories
                            for (DataSnapshot subCategorySnapshot : categorySnapshot.getChildren()) {
                                // Iterate through all products in each subcategory
                                for (DataSnapshot productSnapshot : subCategorySnapshot.child("products").getChildren()) {
                                    // Fetch product details
                                    String name = productSnapshot.child("name").getValue(String.class);
                                    String price = productSnapshot.child("price").getValue(String.class);
                                    String imageUrl = productSnapshot.child("imagesUrls").child("image1").getValue(String.class);

                                    // Add product to the list
                                    productList.add(new Product(name, price, imageUrl));
                                    //totalProducts++; // Increment the product counter
                                }
                            }
                        }

                        // Update the resultText TextView with the total product count
                        //Log.d(TAG, "onCreate: category all pages started ");
                        //resultText.setText("Showing " + totalProducts + " results for 'all categories'");

                        // Notify adapter about data changes
                        productAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Show error message if data loading fails
                        Toast.makeText(AllCategoryActivity.this, "Failed to load data.", Toast.LENGTH_SHORT).show();
                    }
                });




    }
    //for category
    private void fetchCategoryData() {
        FirebaseDatabase.getInstance("https://shoppie-a1b51-default-rtdb.firebaseio.com/")
                .getReference("subcategories")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int index = 0;
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            if (index >= categoryImageView.size()) break;
                            String title = dataSnapshot.child("name").getValue(String.class);
                            String imageUrl = dataSnapshot.child("url").getValue(String.class);

                            Glide.with(AllCategoryActivity.this).load(imageUrl).into(categoryImageView.get(index));
                            titleTexts.get(index).setText(title);
                            index++;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AllCategoryActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                    }
                });
    }




    // Override the onBackPressed method to navigate to the home screen
    @Override
    public void onBackPressed() {
        // Intent to start the home screen activity
        super.onBackPressed();
        Intent intent = new Intent(AllCategoryActivity.this, HomePageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

        // Finish the current activity to remove it from the back stack
        finish();
    }
}
