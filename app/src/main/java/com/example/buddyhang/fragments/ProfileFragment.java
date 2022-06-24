package com.example.buddyhang.fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buddyhang.LoginActivity;
import com.example.buddyhang.MainActivity;
import com.example.buddyhang.R;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@ParseClassName("Profile")
public class ProfileFragment extends Fragment {

    Button logoutButton;
    TextView welcome;
    EditText username;
    EditText bio;
    EditText hobbies;
    EditText name;
    Button buttonUpdateInfo;
    private ImageView profilePhoto;


    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logoutButton = view.findViewById(R.id.logoutButton);
        username = view.findViewById(R.id.username);
        welcome = view.findViewById(R.id.welcome);


        ParseUser user = ParseUser.getCurrentUser();
        welcome.setText("Hello " + user.get("username"));

        name = view.findViewById(R.id.name);
        hobbies = view.findViewById(R.id.hobbies);
        bio = view.findViewById(R.id.bio);
        buttonUpdateInfo = view.findViewById(R.id.buttonUpdateInfo);


        // updating info
        if(user.get("name") == null) {
            name.setText("Name");
        }   else {
            name.setText(user.get("name").toString());
        }

        if(user.get("bio") == null) {
            bio.setText("Bio");
        }   else {
            bio.setText(user.get("bio").toString());
        }

        if(user.get("username") == null) {
            username.setText("Username");
        }   else {
            username.setText(user.get("username").toString());
        }

        if(user.get("hobbies") == null) {
            hobbies.setText("Hobbies");
        }   else {
            hobbies.setText(user.get("hobbies").toString());
        }

        buttonUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.put("name", name.getText().toString());
                user.put("bio", bio.getText().toString());
                user.put("hobbies", hobbies.getText().toString());
                user.put("username", username.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Updating New Info");
                progressDialog.show();

                user.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null)
                        {
                            Toast.makeText(getContext(), "Info Updates", Toast.LENGTH_SHORT).show();

                        } else {

                        }
                        progressDialog.dismiss();
                    }
                });

            }
        });


        // logout button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
                ParseUser.logOut();

            }
        });


    }


}