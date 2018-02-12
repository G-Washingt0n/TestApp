package com.pgmail.martsulg.testapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.pgmail.martsulg.testapp.fragments.CommentsFragment;
import com.pgmail.martsulg.testapp.fragments.MainFragment;
import com.pgmail.martsulg.testapp.fragments.ProfileFragment;

public class NavigationActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            showMain();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_main:
                showMain();
                return true;
            case R.id.navigation_comments:
                showComments();
                return true;
            case R.id.navigation_profile:
                showProfile();
                return true;
        }
        return false;
    }

    private void showMain() {
        showFragment(getSupportFragmentManager(), new MainFragment().newInstance(getSupportFragmentManager()));
    }

    private void showComments() {
        showFragment(getSupportFragmentManager(), new CommentsFragment().newInstance(getSupportFragmentManager()));

    }

    private void showProfile() {
        showFragment(getSupportFragmentManager(), new ProfileFragment().newInstance(getSupportFragmentManager()));

    }


    public static void showFragment(FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_screen_fragment, fragment, fragment.getClass().getName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
