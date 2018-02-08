package com.pgmail.martsulg.testapp;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.Toast;


import java.io.File;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.Profile;
import p.martsulg.data.models.ServerResponse;
import p.martsulg.domain.RegProfileUseCase;

public class RegistryViewModel {

    public ObservableField<String> email2reg = new ObservableField<>();
    public ObservableField<String> password2reg = new ObservableField<>();
    public ObservableField<String> name2reg = new ObservableField<>();
    public ObservableField<File> avatar2reg = new ObservableField<>();

    public RegProfileUseCase regProfileUseCase = new RegProfileUseCase();


    public void onSignUpClick() {

        Profile profile = new Profile();
        profile.setEmail(email2reg.get());
        profile.setPassword(password2reg.get());
        profile.setName(name2reg.get());
        profile.setAvatar(avatar2reg.get());


        regProfileUseCase.execute(profile, new DisposableObserver<ServerResponse>() {


            @Override
            public void onNext(@NonNull ServerResponse response) {

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("Registry Error", e.toString());
            }

            @Override
            public void onComplete() {
                regProfileUseCase.dispose();
            }
        });


    }
}
