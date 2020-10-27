/* Owner: Allen Ding
 *
 * This file contains the implementation of the welcome screen of the app, an
 * activity that welcomes new users.
 */

package com.back4app.quickstartexampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomePage extends AppCompatActivity {
    Intent intent_where_to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        intent_where_to = new Intent(getApplicationContext(), WhereTo.class);
    }

    public void whereTo(View v) {
        startActivity(intent_where_to);
    }
}