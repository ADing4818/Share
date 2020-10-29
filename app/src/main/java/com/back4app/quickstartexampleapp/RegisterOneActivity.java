/* Owner: Allen Ding
 *
 * This file contains the implementation of the first registration page of the app
 * (filling out your first and last name, email address, your city, and your state)
 */

package com.back4app.quickstartexampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterOneActivity extends AppCompatActivity {
    Intent next_register;

    EditText firstName;
    EditText lastName;
    EditText email;
    EditText city;
    EditText state;

    TextView fNameError;
    TextView lNameError;
    TextView emailError;
    TextView cityError;
    TextView stateError;

    HelperFunctions helper_functions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_one);

        next_register = new Intent(getApplicationContext(), RegisterTwoActivity.class);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);

        fNameError = findViewById(R.id.firstNameError);
        lNameError = findViewById(R.id.lastNameError);
        emailError = findViewById(R.id.emailAddressError);
        cityError = findViewById(R.id.cityError);
        stateError = findViewById(R.id.stateError);

        helper_functions = new HelperFunctions(this);

        helper_functions.finishHideKeyboard(firstName);
        helper_functions.finishHideKeyboard(lastName);
        helper_functions.finishHideKeyboard(email);
        helper_functions.finishHideKeyboard(city);
        helper_functions.finishHideKeyboard(state);
    }

    public void nextPage(View v) {
        String fName = firstName.getText().toString();
        String lName = lastName.getText().toString();
        String email_address = email.getText().toString();
        String city_string = city.getText().toString();
        String state_string = state.getText().toString();
        String[] keys = {fName, lName, email_address, city_string, state_string};
        EditText[] values = {firstName, lastName, email, city, state};
        TextView[] errors = {fNameError, lNameError, emailError, cityError, stateError};
        int count = 0;
        int i = 0;

        /* Showing the error messages */
        for (String key : keys) {
            EditText value = values[i];
            TextView error_message = errors[i];
            if (key.isEmpty()) {
                value.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                error_message.setAlpha(1);
                count++;
            }
            if (value == email) {
                if (!Patterns.EMAIL_ADDRESS.matcher(value.getText()).matches()) {
                    value.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                    error_message.setAlpha(1);
                    count++;
                }
            }
            else if (!key.isEmpty()) {
                value.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_ATOP);
                error_message.animate().alpha(0).setDuration(1000);
            }
            i++;
        }
        /* Sending the information below to the next register page */
        if (count == 0) {
            next_register.putExtra("first name", fName);
            next_register.putExtra("last name", lName);
            next_register.putExtra("email address", email_address);
            next_register.putExtra("city", city_string);
            next_register.putExtra("state", state_string);
            startActivity(next_register);
        }
    }
}