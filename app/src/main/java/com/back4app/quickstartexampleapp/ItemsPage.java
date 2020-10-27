package com.back4app.quickstartexampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ItemsPage extends AppCompatActivity {
    Intent intent_to_welcome_page;

    EditText receive_textBox;
    EditText donate_textBox;

    TextView receive_counter;
    TextView donate_counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_page);

        intent_to_welcome_page = new Intent(getApplicationContext(), WelcomePage.class);
        receive_textBox = findViewById(R.id.receive_textbox);
        donate_textBox = findViewById(R.id.donate_textbox);

        receive_counter = findViewById(R.id.receive_counter);
        donate_counter = findViewById(R.id.donate_counter);

        /* Setting up a TextWatcher to show the number of characters the user is up to */
        TextWatcher receive_counter_watcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                receive_counter.setText(String.valueOf(s.length()));
                int s_len = s.length();

                if (s_len < 150) receive_counter.setTextColor(Color.WHITE);
                if (s_len >= 150 && s_len < 175) receive_counter.setTextColor(Color.YELLOW);
                if (s_len >= 175) receive_counter.setTextColor(Color.RED);
            }

            public void afterTextChanged(Editable s) {
            }
        };

        TextWatcher donate_counter_watcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                donate_counter.setText(String.valueOf(s.length()));
                int s_len = s.length();

                if (s_len < 150) donate_counter.setTextColor(Color.WHITE);
                if (s_len >= 150 && s_len < 175) donate_counter.setTextColor(Color.YELLOW);
                if (s_len >= 175) donate_counter.setTextColor(Color.RED);
            }

            public void afterTextChanged(Editable s) {
            }
        };

        receive_textBox.addTextChangedListener(receive_counter_watcher);
        donate_textBox.addTextChangedListener(donate_counter_watcher);

        receive_textBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receive_textBox.setCursorVisible(true);
            }
        });

        donate_textBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donate_textBox.setCursorVisible(true);
            }
        });

        finishHideKeyboard(receive_textBox);
        finishHideKeyboard(donate_textBox);
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

    public void toWelcome(View v) {
        ParseUser user = ParseUser.getCurrentUser();

        /* Placing the receive/donate preferences in the Parse DB */
        user.put("receive_preferences", receive_textBox.getText().toString());
        user.put("donate_preferences", donate_textBox.getText().toString());
        user.saveInBackground();

        startActivity(intent_to_welcome_page);
    }
}