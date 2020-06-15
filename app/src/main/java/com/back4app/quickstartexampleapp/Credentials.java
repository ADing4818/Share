package com.back4app.quickstartexampleapp;

class Credentials {
    MainActivity main_activity;

    public Credentials(MainActivity main_activity) {
        this.main_activity = main_activity;
    }

    /* Checks if either the username or password fields are empty */
    public boolean isEmpty() {
        return (main_activity.username.toString().isEmpty()
                || main_activity.password.toString().isEmpty());
    }


}
