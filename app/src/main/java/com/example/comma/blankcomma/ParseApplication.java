package com.example.comma.blankcomma;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import android.app.Application;
 
public class ParseApplication extends Application {
    private static final String APPLICATION_ID = "ACMQVqSfW1zU5GDFC1wp19d97XW81ZXFj7TLJ45y";
    private static final String CLIENT_KEY = "blZu60bPV89C2tNMuvZZ2ii9UNVdNa8OwxzENKMS";


    @Override
    public void onCreate() {
        super.onCreate();
 
        // Add your initialization code here
        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
 
        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(false);
        ParseACL.setDefaultACL(defaultACL, true);
    }
 
}