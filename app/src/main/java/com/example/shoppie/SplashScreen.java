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
