package com.pgmail.martsulg.testapp.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgmail.martsulg.testapp.R;
import com.pgmail.martsulg.testapp.databinding.FragmentRegistryBinding;
import com.pgmail.martsulg.testapp.RegistryViewModel;


public class RegistryFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RegistryViewModel viewModel = new RegistryViewModel(getContext());
        FragmentRegistryBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_registry, container, false);
        binding.setViewModel(viewModel);


        return binding.getRoot();
    }
}
