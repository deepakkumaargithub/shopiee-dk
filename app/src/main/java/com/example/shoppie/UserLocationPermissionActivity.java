package com.example.shoppie;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class UserLocationPermissionActivity extends AppCompatActivity {

    // Location Manager and Location listener object
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_location_permission);

        b1 = findViewById(R.id.btnGetUserLocation);

        // Initialize the location manager class
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Use location listener to get location updates
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                // Logic to display current location of user
                Toast.makeText(getApplicationContext(), "Latitude: " + location.getLatitude() + "\nLongitude: " + location.getLongitude(), Toast.LENGTH_LONG).show();
            }
        };

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if location permissions are granted
                if (ActivityCompat.checkSelfPermission(UserLocationPermissionActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(UserLocationPermissionActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // Request the missing permissions
                    ActivityCompat.requestPermissions(UserLocationPermissionActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                            MY_PERMISSIONS_REQUEST_LOCATION);
                } else {
                    // Permissions already granted, start listening for location updates
                    startLocationUpdates();
                }
            }
        });
    }

    // Method to start location updates
    private void startLocationUpdates() {
        try {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0, // 0 seconds interval for updates
                    0, // 0 meters minimum distance change for updates
                    locationListener
            );
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    // Constant for permission request
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 123;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted, start location updates
                startLocationUpdates();
            } else {
                // Permission denied, show a toast or handle accordingly
                Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
            }
            return;
            // Other cases for other permissions your app might request
        }
    }

}
