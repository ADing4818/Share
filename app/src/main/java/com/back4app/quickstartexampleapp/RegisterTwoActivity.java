/* Owner: Allen Ding
 *
 * This file contains the implementation of the second registration page of the app. Within this file,
 * users can set their username and password, then register.
 */

package com.back4app.quickstartexampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterTwoActivity extends AppCompatActivity {
    EditText username;
    EditText password;

    Intent intent;
    Intent intent_to_welcome_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_two);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        intent = getIntent();
        intent_to_welcome_page = new Intent(getApplicationContext(), WelcomePage.class);

        finishHideKeyboard(username);
        finishHideKeyboard(password);
    }

    /* From StackOverflow */
    public void finishHideKeyboard(View v) {
        v.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }

    /* From StackOverflow */
    public void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public void register(View v) {
        ParseUser new_user = new ParseUser();

        /* Setting the user's username, password, and email in the "User" class */
        new_user.setUsername(username.getText().toString());
        new_user.setPassword(password.getText().toString());
        new_user.setEmail(intent.getStringExtra("email address"));

        /* Adding the user's username, first name, last name, city, state to the "Info" class */
        new_user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    ParseObject user_info = new ParseObject("Info");
                    user_info.add("username", username.getText().toString());
                    user_info.add("firstName", intent.getStringExtra("first name"));
                    user_info.add("lastName", intent.getStringExtra("last name"));
                    user_info.add("city", intent.getStringExtra("city"));
                    user_info.add("state", intent.getStringExtra("state"));
                    user_info.saveInBackground();

                    startActivity(intent_to_welcome_page);
                }
                /* Fix message below later */
                else {
                    Toast.makeText(RegisterTwoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}