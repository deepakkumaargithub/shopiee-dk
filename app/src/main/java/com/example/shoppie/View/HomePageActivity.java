package com.example.shoppie.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.shoppie.ModelClass.Banner;
import com.example.shoppie.ModelClass.Category;
import com.example.shoppie.R;
import com.example.shoppie.ViewModel.HomeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * HomePageActivity is the main activity for the home page of the Shoppie application.
 * It initializes UI components, sets up Firebase Database references, and handles
 * navigation through a BottomNavigationView.
 */
public class HomePageActivity extends AppCompatActivity {

    // Constant for logging
    private static final String TAG = "HomePageActivity";

    // UI elements
    private BottomNavigationView nav;
    private ImageView profileImage;
    private ImageView notificationIcon;
    private ImageView homeImage;
    private TextView userName;

    // ViewModel and Firebase Database reference
    private HomeViewModel homeViewModel;
    private DatabaseReference databaseReference;

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this contains the most recently supplied data.
     */
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

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Fetch and set profile image URL and username from Firebase Realtime Database
        fetchUserProfile();

        // Fetch and set home image URL from Firebase Realtime Database
        fetchHomeImage();

        // Initialize ViewModel and set up LiveData observers
        setupViewModel();

        // Handle click events for the notification icon
        setupNotificationIconClick();

        // Set up BottomNavigationView item selection listener
        setupBottomNavigationView();
    }

    /**
     * Fetch user profile information from Firebase Realtime Database and update UI.
     */
    private void fetchUserProfile() {
        // References to Firebase Realtime Database nodes for user profile image URL and username
        DatabaseReference profileImageRef = databaseReference.child("UserProfile").child("profileImageUrl");
        DatabaseReference userNameRef = databaseReference.child("UserProfile").child("userName");

        // Fetch and set username
        userNameRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Retrieve username from the database snapshot
                String username = dataSnapshot.getValue(String.class);
                if (username != null) {
                    userName.setText(username); // Set username in the TextView
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Log any errors encountered while retrieving username
                Log.w(TAG, "loadUsername:onCancelled", databaseError.toException());
            }
        });

        // Fetch and set profile image
        profileImageRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Retrieve profile image URL from the database snapshot
                String profileImageUrl = dataSnapshot.getValue(String.class);
                if (profileImageUrl != null) {
                    // Load profile image using Picasso library
                    Picasso.get().load(profileImageUrl).into(profileImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Log any errors encountered while retrieving profile image
                Log.w(TAG, "loadProfileImage:onCancelled", databaseError.toException());
            }
        });
    }

    /**
     * Fetch home image URL from Firebase Realtime Database and update UI.
     */
    private void fetchHomeImage() {
        // Reference to Firebase Realtime Database node for home image URL
        DatabaseReference homeImageRef = databaseReference.child("homePageBannerImage");
        homeImageRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Retrieve home image URL from the database snapshot
                String homeImageUrl = dataSnapshot.getValue(String.class);
                if (homeImageUrl != null) {
                    // Load home image using Picasso library
                    Picasso.get().load(homeImageUrl).into(homeImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Log any errors encountered while retrieving home image
                Log.w(TAG, "loadHomeImage:onCancelled", databaseError.toException());
            }
        });
    }

    /**
     * Initialize ViewModel and observe LiveData for banners and categories.
     */
    private void setupViewModel() {
        // Initialize HomeViewModel
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // Observe LiveData for banners
        homeViewModel.getBannerList().observe(this, new Observer<List<Banner>>() {
            @Override
            public void onChanged(List<Banner> banners) {
                // Implement banner loading logic here
            }
        });

        // Observe LiveData for categories
        homeViewModel.getCategoryList().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                // Implement category loading logic here
            }
        });
    }

    /**
     * Set up click listener for the notification icon.
     */
    private void setupNotificationIconClick() {
        notificationIcon.setOnClickListener(v -> {
            // Implement notification click logic here
        });
    }

    /**
     * Set up item selection listener for BottomNavigationView.
     */
    private void setupBottomNavigationView() {
        nav = findViewById(R.id.nav_bar);

        // Set item selection listener for BottomNavigationView
        nav.setOnItemSelectedListener(menuItem -> {
            // Handle item selection
            int itemId = menuItem.getItemId();
            Intent intent;
            if(itemId == R.id.home) {
                // Navigate to HomePageActivity
                intent = new Intent(HomePageActivity.this, HomePageActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.wishlist) {
                // Navigate to WishlistActivity
                intent = new Intent(HomePageActivity.this, WishlistActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.cart) {
                // Navigate to CartActivity
                intent = new Intent(HomePageActivity.this, CartActivity.class);
                startActivity(intent);
                return true;
            }  else if (itemId == R.id.userprofile) {
                // Navigate to UserProfileActivity
                intent = new Intent(HomePageActivity.this, UserProfileActivity.class);
                startActivity(intent);
                return true;
            }
            return false;

        });
    }
}
