package com.example.shoppie;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CategoryActivity extends AppCompatActivity {
    private GridView gridView1, gridView2, gridView3, gridView4;

    private int[] imageArray = {
            R.drawable.ac1, R.drawable.ac2, R.drawable.ac3, R.drawable.ac4,
            R.drawable.fridge1, R.drawable.fridge2, R.drawable.fridge3, R.drawable.fridge4,
            R.drawable.laptop1, R.drawable.laptop2, R.drawable.laptop3, R.drawable.laptop4,
            R.drawable.speaker1, R.drawable.speaker2, R.drawable.speaker3, R.drawable.speaker4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        gridView1 = findViewById(R.id.grid_view1);
        gridView2 = findViewById(R.id.grid_view2);
        gridView3 = findViewById(R.id.grid_view3);
        gridView4 = findViewById(R.id.grid_view4);

        ImageAdapterCategory adapter1 = new ImageAdapterCategory(this, new int[]{R.drawable.ac1, R.drawable.ac2, R.drawable.ac3, R.drawable.ac4});
        ImageAdapterCategory adapter2 = new ImageAdapterCategory(this, new int[]{R.drawable.fridge1, R.drawable.fridge2, R.drawable.fridge3, R.drawable.fridge4});
        ImageAdapterCategory adapter3 = new ImageAdapterCategory(this, new int[]{R.drawable.laptop1, R.drawable.laptop2, R.drawable.laptop3, R.drawable.laptop4});
        ImageAdapterCategory adapter4 = new ImageAdapterCategory(this, new int[]{R.drawable.speaker1, R.drawable.speaker2, R.drawable.speaker3, R.drawable.speaker4});

        gridView1.setAdapter(adapter1);
        gridView2.setAdapter(adapter2);
        gridView3.setAdapter(adapter3);
        gridView4.setAdapter(adapter4);
    }
}

