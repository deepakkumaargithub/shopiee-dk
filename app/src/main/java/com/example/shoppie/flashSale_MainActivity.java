package com.example.shoppie;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class flashSale_MainActivity extends AppCompatActivity {

    private List<ImageView> imageViews;
    private List<TextView> discountTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashsale_activity_main);

        imageViews = new ArrayList<>();
        discountTexts = new ArrayList<>();

        imageViews.add(findViewById(R.id.flashsale_image1));
        imageViews.add(findViewById(R.id.flashsale_image2));
        imageViews.add(findViewById(R.id.flashsale_image3));
        imageViews.add(findViewById(R.id.flashsale_image4));
        imageViews.add(findViewById(R.id.flashsale_image5));
        imageViews.add(findViewById(R.id.flashsale_image6));

        discountTexts.add(findViewById(R.id.discount_text1));
        discountTexts.add(findViewById(R.id.discount_text2));
        discountTexts.add(findViewById(R.id.discount_text3));
        discountTexts.add(findViewById(R.id.discount_text4));
        discountTexts.add(findViewById(R.id.discount_text5));
        discountTexts.add(findViewById(R.id.discount_text6));

        fetchFlashSaleData();
    }

    private void fetchFlashSaleData() {
        FirebaseDatabase.getInstance("https://shoppie-a1b51-default-rtdb.firebaseio.com/")
                .getReference("FlashSale")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int index = 0;
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            if (index >= imageViews.size()) break;
                            int discount = dataSnapshot.child("Discount").getValue(Integer.class);
                            String imageUrl = dataSnapshot.child("Imageurl").getValue(String.class);

                            Glide.with(flashSale_MainActivity.this).load(imageUrl).into(imageViews.get(index));
                            discountTexts.get(index).setText(discount + "%");
                            index++;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(flashSale_MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
