/* Owner: Allen Ding
 *
 * This file contains the implementation of the home screen of the app. Within this file,
 * users can sign in, sign up, and reset their password.
 */

package com.back4app.quickstartexampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {
    Intent intent_register_one;
    Intent reset_password;

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseUser.getCurrentUser();
        ParseUser.logOut();
        ParseInstallation.getCurrentInstallation().saveInBackground();

        /* Defining variables */
        intent_register_one = new Intent(getApplicationContext(), RegisterOneActivity.class);
        reset_password = new Intent(getApplicationContext(), ResetPassword.class);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        /* Hiding soft keyboard */
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

    /* When clicked, the user goes to the first out of two registration pages. */
    public void sign_up(View v) {
        startActivity(intent_register_one);
    }

    public void showToast(String comment) {
        Toast message = Toast.makeText(this, comment, Toast.LENGTH_LONG);
        message.setGravity(Gravity.BOTTOM, 0, 250);
        message.show();
    }

    /* Called when the user signs in */
    public void sign_in(View v) {
        String username_string = username.getText().toString();
        String password_string = password.getText().toString();

        ParseUser.logInInBackground(username_string, password_string, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null) {
                    showToast("Log In Successful!");
                    /* Go to "Where To" screen */
                }
                else showToast(e.getMessage());
            }
        });
    }

    /* Resetting password */
    public void resetPassword(View v) {
        startActivity(reset_password);
    }
}
