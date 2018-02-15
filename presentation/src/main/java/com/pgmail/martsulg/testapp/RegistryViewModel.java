package com.pgmail.martsulg.testapp;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.Profile;
import p.martsulg.data.models.UserModel;
import p.martsulg.domain.RegProfileUseCase;

public class RegistryViewModel {

    public ObservableField<String> email2reg = new ObservableField<>();
    public ObservableField<String> password2reg = new ObservableField<>();
    public ObservableField<String> name2reg = new ObservableField<>();
    public RegProfileUseCase regProfileUseCase = new RegProfileUseCase();
    private Context context;

    public RegistryViewModel(Context context) {
        this.context = context;
    }


    public void onSignUpClick() {

        Profile profile = new Profile();
        profile.setEmail(email2reg.get());
        profile.setPassword(password2reg.get());
        profile.setName(name2reg.get());
        try {
            File file = new File(context.getFilesDir(), "robot.jpg");
            byte[] data = FileUtils.readFileToByteArray(file);
            String avatar = Base64.encodeToString(data, Base64.NO_WRAP);
            Log.e("Avatar", avatar);

            profile.setAvatar(avatar);
        } catch (Exception e) {
            Log.e("File error", e.toString());
        }


        regProfileUseCase.execute(profile, new DisposableObserver<UserModel>() {
            @Override
            public void onNext(@NonNull UserModel response) {
                Toast.makeText(context, "Registered successfully! ",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("Registry Error", e.toString());
            }

            @Override
            public void onComplete() {
                email2reg.set(null);
                name2reg.set(null);
                password2reg.set(null);
                regProfileUseCase.dispose();
            }
        });

    }
}
