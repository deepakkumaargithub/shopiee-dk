package com.example.shoppie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateAccountActivity extends AppCompatActivity {

    EditText createEmail, createPhoneNo, createPassword;
    Button cancelButton, doneButton;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        createEmail = findViewById(R.id.email);
        createPhoneNo = findViewById(R.id.phone_number);
        createPassword = findViewById(R.id.password_text);
        doneButton = findViewById(R.id.btnDone);
        cancelButton = findViewById(R.id.btnCancleCreateAccount);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                //taking input text from user through EditText
                String email = createEmail.getText().toString();
                String phoneNo = createPhoneNo.getText().toString();
                String password = createPassword.getText().toString();

                //checking user details- email,phoneNo,password cannot be empty.
                if( validateCreateAccountDetails(email,phoneNo,password)){
                    checkPhoneNumberAndCreateAccount(email, phoneNo, password);
                }
                else {
                    Toast.makeText(CreateAccountActivity.this, "Enter correct user details.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_to_splashScreen = new Intent(CreateAccountActivity.this, SplashScreen.class);
                startActivity(go_to_splashScreen);
            }
        });
    }

    public Boolean validateCreateAccountDetails(String email, String phoneNo, String password){

        if(email.isEmpty()) {
            createEmail.setError("Email cannot be empty");
            return false;
        }
        else if(phoneNo.isEmpty()){
            createPhoneNo.setError("PhoneNo cannot be empty");
            return false;
        }
        else if(password.isEmpty()){
            createPassword.setError("Password cannot be empty");
            return false;
        }
        else return true;  // if all okay.
    }

    private void checkPhoneNumberAndCreateAccount(final String email, final String phoneNo, final String password) {
        reference.orderByChild("phoneNo").equalTo(phoneNo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Phone number already exists
                    Toast.makeText(CreateAccountActivity.this, "Phone number already registered", Toast.LENGTH_SHORT).show();
                } else {
                    // Phone number does not exist, create new account
                    HelperClass helperClass = new HelperClass(email, phoneNo, password);
                    reference.child(phoneNo).setValue(helperClass);

                    Toast.makeText(CreateAccountActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                    Intent go_to_loginActivity = new Intent(CreateAccountActivity.this, LoginActivity.class);
                    startActivity(go_to_loginActivity);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(CreateAccountActivity.this, "Database Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
