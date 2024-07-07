package com.example.shoppie;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.shoppie.View.HomePageActivity;

public class UserLocationPermissionActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_location_permission);

        // Simulating successful sign-in
        onSignInSuccess();
    }

    private void showLocationPermissionDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_user_location_permission);
        dialog.setCancelable(false);

        Button btnAllow = dialog.findViewById(R.id.btnAllow);
        Button btnExit = dialog.findViewById(R.id.btnExit);

        btnAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check and request location permissions
                if (ActivityCompat.checkSelfPermission(UserLocationPermissionActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(UserLocationPermissionActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(UserLocationPermissionActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                            MY_PERMISSIONS_REQUEST_LOCATION);
                } else {
                    // Permissions are already granted, proceed to the home screen
                    moveToHomeScreen();
                }
                dialog.dismiss();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Exit the app or go back to the sign-in screen
                finish(); // or navigate to another activity if needed
            }
        });

        dialog.show();
    }

    // Simulate successful sign-in
    private void onSignInSuccess() {
        showLocationPermissionDialog();
    }

    private void moveToHomeScreen() {
        Intent intent = new Intent(UserLocationPermissionActivity.this, HomePageActivity.class);
        startActivity(intent);
        finish(); // Close current activity
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted, proceed to the home screen
                moveToHomeScreen();
            } else {
                // Permission denied, show a toast or handle accordingly
                Toast.makeText(this, "Location permission is required for this app to function properly.", Toast.LENGTH_LONG).show();
                // Optionally, you can show the dialog again or handle it as per your requirement
            }
        }
    }
}