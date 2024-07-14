package com.example.shoppie;

import android.content.Intent;
import android.os.Bundle;
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

public class AllCategoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView; // RecyclerView to display the products
    private ProductAdapter productAdapter; // Adapter for the RecyclerView
    private List<Product> productList; // List to hold the products
    private TextView resultText; // TextView to show the total product count

    ImageView backArrow; // ImageView for the back arrow

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

        recyclerView = findViewById(R.id.recycler_view_allCategory);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // Set layout manager for the RecyclerView

        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);

        resultText = findViewById(R.id.result_text); // Initialize the resultText TextView

        // Fetch data from Firebase
        database.getReference("categories")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        productList.clear();
                        int totalProducts = 0; // Counter for total products

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
                                    totalProducts++; // Increment the product counter
                                }
                            }
                        }

                        // Update the resultText TextView with the total product count
                        resultText.setText("Showing " + totalProducts + " results for 'all categories'");

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