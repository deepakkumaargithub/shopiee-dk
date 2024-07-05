package com.example.shoppie.Repository;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppie.ModelClass.Banner;
import com.example.shoppie.ModelClass.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class AppRepository {
    private final DatabaseReference bannerRef;
    private final DatabaseReference categoryRef;

    public AppRepository() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        bannerRef = firebaseDatabase.getReference("HomeBanner");
        categoryRef = firebaseDatabase.getReference("CategoryFilter");
    }

    public LiveData<List<Banner>> getBannerImages() {
        MutableLiveData<List<Banner>> data = new MutableLiveData<>();
        bannerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Banner> banners = new ArrayList<>();
                for (DataSnapshot bannerSnapshot : snapshot.getChildren()) {
                    Banner banner = bannerSnapshot.getValue(Banner.class);
                    banners.add(banner);
                }
                data.setValue(banners);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle error
            }
        });
        return data;
    }

    public LiveData<List<Category>> getCategories() {
        MutableLiveData<List<Category>> data = new MutableLiveData<>();
        categoryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Category> categories = new ArrayList<>();
                for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                    Category category = categorySnapshot.getValue(Category.class);
                    categories.add(category);
                }
                data.setValue(categories);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle error
            }
        });
        return data;
    }
}
