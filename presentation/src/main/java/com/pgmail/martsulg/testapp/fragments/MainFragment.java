package com.pgmail.martsulg.testapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgmail.martsulg.testapp.R;


/**
 * Created by g_washingt0n on 07.02.2018.
 */

public class MainFragment extends Fragment {

    public static MainFragment newInstance(FragmentManager fragmentManager){

        MainFragment mainFragment = new MainFragment();

        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container,false);

        return view;

    }
}
