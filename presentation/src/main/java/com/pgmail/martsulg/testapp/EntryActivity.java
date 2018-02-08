package com.pgmail.martsulg.testapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.pgmail.martsulg.testapp.fragments.LogInFragment;
import com.pgmail.martsulg.testapp.fragments.RegistryFragment;

public class EntryActivity extends FragmentActivity {
    public static SharedPreferences preferences;

    public static final String SHARED_PREF_NAME = "sharedname";
    public static final String TOKEN_NAME = "Token";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        preferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);


        if(savedInstanceState==null) {
            try { //автологин
                if (!preferences.getString(TOKEN_NAME, null).isEmpty()) {
                    Intent intent = new Intent(EntryActivity.this, NavigationActivity.class);
                    startActivity(intent);
                }
            } catch(Exception e) {

            }

                showFragment(getSupportFragmentManager(), new LogInFragment());
        }

        findViewById(R.id.logInButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showFragment(getSupportFragmentManager(), new LogInFragment());
                }
            });


        findViewById(R.id.registryButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showFragment(getSupportFragmentManager(), new RegistryFragment());
                }
            });

    }

    public static void setPreferences(String key, String value ){
        preferences.edit()
                .putString(key, value)
                .apply();
    }

    public static void showFragment(FragmentManager fragmentManager, Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.entryContainer,fragment, fragment.getClass().getName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
