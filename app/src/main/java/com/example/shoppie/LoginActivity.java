package com.example.shoppie;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    EditText loginEmailOrPhoneNo, loginPassword;
    Button cancelLoginBtn, signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmailOrPhoneNo = findViewById(R.id.email_phone_Number_text);
        loginPassword = findViewById(R.id.password_text);
        signInButton = findViewById(R.id.sign_in_btn);
        cancelLoginBtn = findViewById(R.id.btnCancleLogin);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameOrPhone = loginEmailOrPhoneNo.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();
                loginUser(usernameOrPhone, password);
            }
        });

        cancelLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_to_SplashScreen = new Intent(LoginActivity.this, SplashScreen.class);
                startActivity(go_to_SplashScreen);
                finish(); // Optional: Close LoginActivity to prevent going back to it from SplashScreen
            }
        });
    }

    private void loginUser(String usernameOrPhone, String password) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query query = reference.orderByChild("phoneNo").equalTo(usernameOrPhone);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(TAG,"check user exists");
                if (snapshot.exists()) {
                    Log.d(TAG,"user exists");
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        String passwordFromDB = userSnapshot.child("password").getValue(String.class);
                        if (password.equals(passwordFromDB)) {
                            // Passwords match, authentication successful
                            Log.d(TAG," Passwords match, authentication successful");
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, UserLocationPermissionActivity.class);
                            startActivity(intent);
                            finish(); // Close LoginActivity after successful login
                        } else {
                            // Passwords do not match
                            Log.d(TAG," Password do not match");
                            Toast.makeText(LoginActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                        }
                        return;
                    }
                } else {
                    // User does not exist
                    Log.d(TAG," User does not exist");
                    Toast.makeText(LoginActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
                Log.e(TAG, "Database Error: " + error.getMessage());
                Toast.makeText(LoginActivity.this, "Database Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
