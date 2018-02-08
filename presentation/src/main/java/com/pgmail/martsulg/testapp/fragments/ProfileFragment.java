package com.pgmail.martsulg.testapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by g_washingt0n on 07.02.2018.
 */

public class ProfileFragment  extends Fragment {
    public static ProfileFragment newInstance(FragmentManager fragmentManager){

        ProfileFragment profileFragment = new ProfileFragment();

        return profileFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
