package com.pgmail.martsulg.testapp;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.Profile;
import p.martsulg.data.models.UserModel;
import p.martsulg.domain.RegProfileUseCase;

public class RegistryViewModel {

    public ObservableField<String> email2reg = new ObservableField<>();
    public ObservableField<String> password2reg = new ObservableField<>();
    public ObservableField<String> name2reg = new ObservableField<>();
    private File avatar2reg;
    private Context context;

    public RegProfileUseCase regProfileUseCase = new RegProfileUseCase();

    public RegistryViewModel(Context context) {
        this.context = context;
    }


    public void onSignUpClick() {

        Profile profile = new Profile();
        profile.setEmail(email2reg.get());
        profile.setPassword(password2reg.get());
        profile.setName(name2reg.get());
        try {
            avatar2reg = new File(context.getFilesDir(), "robot.jpg");
            InputStream inputStream = getClass().getResourceAsStream("/assets/robot.jpg");
            OutputStream out = new FileOutputStream(avatar2reg);
            byte buf[] = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0)
                out.write(buf, 0, len);
            out.close();
            inputStream.close();
            profile.setAvatar(avatar2reg);
        } catch (Exception e) {
            Log.e("File error", e.toString());
        }


        regProfileUseCase.execute(profile, new DisposableObserver<UserModel>() {


            @Override
            public void onNext(@NonNull UserModel response) {

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
