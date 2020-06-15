package com.back4app.quickstartexampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {
    Intent intent_register_one;

    EditText username;
    EditText password;

    Credentials credentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseUser.getCurrentUser();
        ParseUser.logOut();
        ParseInstallation.getCurrentInstallation().saveInBackground();

        /* Defining variables */
        intent_register_one = new Intent(getApplicationContext(), RegisterOneActivity.class);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        credentials = new Credentials(this);
    }

    /* When clicked, the user goes to the first out of two registration pages. */
    public void sign_up(View v) {
        startActivity(intent_register_one);
    }
}
