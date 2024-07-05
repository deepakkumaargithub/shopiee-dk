package com.example.shoppie.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shoppie.ModelClass.Banner;
import com.example.shoppie.ModelClass.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<Category>> categoryList;
    private MutableLiveData<List<Banner>> bannerList;

    public HomeViewModel() {
        categoryList = new MutableLiveData<>();
        bannerList = new MutableLiveData<>();

        fetchBanners();
        fetchCategories();
    }

    public MutableLiveData<List<Category>> getCategoryList() {
        return categoryList;
    }

    public MutableLiveData<List<Banner>> getBannerList() {
        return bannerList;
    }

    private void fetchCategories() {
        DatabaseReference categoryRef = FirebaseDatabase.getInstance().getReference("CategoryFilter");
        categoryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Category> categories = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.d("FirebaseData", "Snapshot key: " + snapshot.getKey());
                    String id = snapshot.getKey();
                    String type = snapshot.child("type").getValue(String.class);
                    Log.d("FirebaseData", "type: " + type);
                    Category category = new Category(id, type);
                    categories.add(category);
                }
                categoryList.setValue(categories);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
                Log.e("HomeViewModel", "Failed to fetch categories: " + databaseError.getMessage());
                // You can also show a toast or update UI to notify the user about the error
            }
        });
    }

    public void fetchBanners() {
        DatabaseReference bannerRef = FirebaseDatabase.getInstance().getReference("HomeBanner");
        bannerRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Banner> banners = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.d("FirebaseData", "Snapshot key: " + snapshot.getKey());
                    String imageUrl = snapshot.child("url").getValue(String.class);
                    Log.d("FirebaseData", "imageURl: " + imageUrl);
                    Banner banner = new Banner(imageUrl);
                    banners.add(banner);
                }
                bannerList.setValue(banners);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
                Log.e("HomeViewModel", "Failed to fetch banners: " + databaseError.getMessage());
                // You can also show a toast or update UI to notify the user about the error
            }
        });
    }
}
