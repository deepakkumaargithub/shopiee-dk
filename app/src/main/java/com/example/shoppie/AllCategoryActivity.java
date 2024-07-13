package com.example.shoppie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppie.View.HomePageActivity;

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

        backArrow = findViewById(R.id.back_arrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_HomePage = new Intent( AllCategoryActivity.this,HomePageActivity.class);
                startActivity(go_to_HomePage);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recycler_view_allCategory);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        productList = new ArrayList<>();
        // Add sample products to the list
        productList.add(new Product("Bosch 8 kg 5 Star Fully-Automatic Front Loading Washing Machine ", "2474", "https://m.media-amazon.com/images/I/51B6SQ1CRML._SY445_SX342_QL70_FMwebp_.jpg"));
        productList.add(new Product("Samsung 32L, Slim Fry, Convection Microwave Oven with Tandoor and Curd making", "16,999", "https://m.media-amazon.com/images/I/41utNMTgOAL._SY445_SX342_QL70_FMwebp_.jpg"));
        productList.add(new Product("Godrej 1 Ton 3 Star, 5-In-1 Convertible Cooling, Inverter Split AC ", "28,999", "https://m.media-amazon.com/images/I/514j-RPeWyL._SX679_.jpg"));
        productList.add(new Product("Whirlpool 184 L 2 Star Direct-Cool Single Door Refrigerator ", "13,999", "https://m.media-amazon.com/images/I/5134E0Z7iwL._SY879_.jpg"));
        productList.add(new Product("Bosch 8 kg 5 Star Fully-Automatic Front Loading Washing Machine ", "2474", "https://m.media-amazon.com/images/I/51B6SQ1CRML._SY445_SX342_QL70_FMwebp_.jpg"));
        productList.add(new Product("Samsung 32L, Slim Fry, Convection Microwave Oven with Tandoor and Curd making", "16,999", "https://m.media-amazon.com/images/I/41utNMTgOAL._SY445_SX342_QL70_FMwebp_.jpg"));
        productList.add(new Product("Godrej 1 Ton 3 Star, 5-In-1 Convertible Cooling, Inverter Split AC ", "28,999", "https://m.media-amazon.com/images/I/514j-RPeWyL._SX679_.jpg"));

        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);
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

