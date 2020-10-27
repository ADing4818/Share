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

import java.util.Objects;

public class RegisterTwoActivity extends AppCompatActivity {
    EditText username;
    EditText password;

    Intent intent;
    Intent intent_to_items_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_two);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        intent = getIntent();
        intent_to_items_page = new Intent(getApplicationContext(), ItemsPage.class);

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

        /* Setting the user's information in the "User" class */
        new_user.setUsername(username.getText().toString());
        new_user.setPassword(password.getText().toString());
        new_user.setEmail(intent.getStringExtra("email address"));
        new_user.put("username", username.getText().toString());
        new_user.put("firstName", Objects.requireNonNull(intent.getStringExtra("first name")));
        new_user.put("lastName", Objects.requireNonNull(intent.getStringExtra("last name")));
        new_user.put("city", Objects.requireNonNull(intent.getStringExtra("city")));
        new_user.put("state", Objects.requireNonNull(intent.getStringExtra("state")));
        new_user.saveInBackground();

        new_user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) startActivity(intent_to_items_page);
                /* Fix message below later */
                else Toast.makeText(RegisterTwoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}