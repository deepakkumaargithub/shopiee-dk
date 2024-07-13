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

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_category);

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        backArrow = findViewById(R.id.back_arrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_HomePage = new Intent(AllCategoryActivity.this, HomePageActivity.class);
                startActivity(go_to_HomePage);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recycler_view_allCategory);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);

        // Fetch data from Firebase
        database.getReference("categories/electronics/computers/products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productList.clear();
                for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                    String name = productSnapshot.child("name").getValue(String.class);
                    String price = productSnapshot.child("price").getValue(String.class);
                    String imageUrl = productSnapshot.child("imagesUrls").child("image1").getValue(String.class);
                    productList.add(new Product(name, price, imageUrl));
                }
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
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
