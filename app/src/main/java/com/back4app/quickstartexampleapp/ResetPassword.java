/* Owner: Allen Ding
 *
 * This file contains the implementation of allowing users to reset their password
 */

package com.back4app.quickstartexampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;

public class ResetPassword extends AppCompatActivity {
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        email = findViewById(R.id.email);

        finishHideKeyboard(email);
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

    /* This function sends an email with a link that allows the user to reset their password */
    public void sendEmail(View v) {
        String email_string = email.getText().toString();
        try {
            ParseUser.requestPasswordReset(email_string);
            Toast.makeText(this, "Email was sent!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}