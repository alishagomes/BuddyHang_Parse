package com.example.buddyhang;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {


    private EditText userUsername;
    private EditText userPassword;
    private Button buttonLogin;
    private TextView registerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        // implement user persistence
        if (ParseUser.getCurrentUser() != null) {
            goMainActivity();
        }

        // hide action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        userUsername = findViewById(R.id.username);
        userPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.buttonLogin);
        registerLink = findViewById(R.id.registerLink);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userUsername.getText().toString();
                String password = userPassword.getText().toString();
                loginUser(username, password);
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void loginUser(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Toast.makeText(LoginActivity.this, "Issue with login", Toast.LENGTH_SHORT).show();
                    return;
                }
                goMainActivity();
            }
        });

    }
    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }



}