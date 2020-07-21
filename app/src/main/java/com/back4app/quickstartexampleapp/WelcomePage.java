/* Owner: Allen Ding
 *
 * This file contains the implementation of the welcome screen of the app, an
 * activity that welcomes new users.
 */

package com.back4app.quickstartexampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
    }
}