package com.example.shoppie.View;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.shoppie.ModelClass.Banner;
import com.example.shoppie.ModelClass.Category;
import com.example.shoppie.R;
import com.example.shoppie.ViewModel.HomeViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private static final String TAG = "HomePageActivity";

    private ImageView profileImage;
    private ImageView notificationIcon;
    private ImageView homeImage;
    private TextView userName;

    private HomeViewModel homeViewModel;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Log.d(TAG, "onCreate: Home page started");

        // Initialize UI elements
        userName = findViewById(R.id.username);
        profileImage = findViewById(R.id.profileImage);
        notificationIcon = findViewById(R.id.notificationIcon);
        homeImage = findViewById(R.id.home_image);

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Fetch profile image URL  and username from Firebase Realtime Database
        DatabaseReference profileImageRef = databaseReference.child("UserProfile").child("profileImageUrl");
        DatabaseReference userNameRef = databaseReference.child("UserProfile").child("userName");

        // Fetch and set username
        userNameRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String username = dataSnapshot.getValue(String.class);
                if (username != null) {
                    userName.setText(username);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadUsername:onCancelled", databaseError.toException());
            }
        });

        // Fetch and set profile image
        profileImageRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String profileImageUrl = dataSnapshot.getValue(String.class);
                if (profileImageUrl != null) {
                    Picasso.get().load(profileImageUrl).into(profileImage);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadProfileImage:onCancelled", databaseError.toException());
            }
        });

        // Fetch home image URL from Firebase Realtime Database
        DatabaseReference homeImageRef = databaseReference.child("homePageBannerImage");
        homeImageRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String homeImageUrl = dataSnapshot.getValue(String.class);
                if (homeImageUrl != null) {
                    Picasso.get().load(homeImageUrl).into(homeImage);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadHomeImage:onCancelled", databaseError.toException());
            }
        });

        // Initialize ViewModel
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // Observe LiveData
        homeViewModel.getBannerList().observe(this, new Observer<List<Banner>>() {
            @Override
            public void onChanged(List<Banner> banners) {
                // loadBannerImages(banners);
            }
        });

        homeViewModel.getCategoryList().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                // loadCategories(categories);
            }
        });

        // Notification icon click event
        notificationIcon.setOnClickListener(v -> {
            // Show notifications
        });
    }
}
