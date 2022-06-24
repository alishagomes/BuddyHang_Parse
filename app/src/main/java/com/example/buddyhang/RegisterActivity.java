package com.example.buddyhang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;


import android.os.Handler;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private EditText userUsername;
    private EditText userPassword;
    private Button buttonSignUp;
    private EditText name;
    private EditText email;
    private EditText phone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // hide action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        userUsername = findViewById(R.id.username);
        userPassword = findViewById(R.id.password);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick signup button");
                String username = userUsername.getText().toString();
                String password = userPassword.getText().toString();
                createAccount(username, password);
            }
        });
    }


    private void createAccount(String username, String password) {
        ParseUser user = new ParseUser();

        user.put("username", username);
        user.put("password", password);

        user.put("name",name.getText().toString());
        user.put("email",email.getText().toString());
        user.put("phone", phone.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Log.i(TAG, "Signed in");
                } else {
                    Log.i(TAG, "Not signed in");
                }
            }
        });

        user.saveInBackground();

        user.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Log.i(TAG, "Saved");
                } else {
                    Log.i(TAG, "Did not save");
                }
            }
        });



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 2000);

    }

}