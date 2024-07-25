package com.example.shoppie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    Button button;
    ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


//        //checking the login state.
//
//        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
//        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
//
//        if (isLoggedIn) {
//            // User is logged in, direct them to the home screen
//            Intent intent = new Intent(this, HomePageActivity.class);
//            startActivity(intent);
//            finish(); // Close the current activity
//        }
//        else {
//            // User is not logged in, direct them to the login screen
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//            finish(); // Close the current activity
//        }

        ///////code for logout used when needed. or logout function made ////
//
//        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putBoolean("isLoggedIn", false);
//        editor.apply();
//
//// Redirect to login screen
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
//        finish();


        button = findViewById(R.id.get_started_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateAccountActivity();
            }
        });

        img = findViewById(R.id.go_to_login_screen);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });
    }

    private void openCreateAccountActivity() {
        Intent intent = new Intent(SplashScreen.this, CreateAccountActivity.class);
        startActivity(intent);
        finish(); // Finish splash screen activity to prevent returning to it
    }

    private void openLoginActivity() {
        Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Finish splash screen activity to prevent returning to it
    }
}
