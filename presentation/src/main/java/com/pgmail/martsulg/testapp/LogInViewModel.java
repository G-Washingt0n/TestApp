package com.pgmail.martsulg.testapp;


import android.content.Intent;
import android.databinding.ObservableField;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.pgmail.martsulg.testapp.EntryActivity;
import com.pgmail.martsulg.testapp.NavigationActivity;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.Profile;
import p.martsulg.data.models.ServerResponse;
import p.martsulg.domain.LogProfileUseCase;

public class LogInViewModel {

    public ObservableField<String> email2send = new ObservableField<>();
    public ObservableField<String> password2send = new ObservableField<>();
    private int status;
    private String token;


    FragmentActivity activity;
    Intent intent;

    public LogInViewModel(FragmentActivity activity) {
        this.activity = activity;
        intent = new Intent(activity, NavigationActivity.class);
    }

    public LogProfileUseCase logProfileUseCase = new LogProfileUseCase();


    public void onSignInClick() {
        try {

            Profile profile = new Profile();
            profile.setEmail(email2send.get());
            profile.setPassword(password2send.get());

            logProfileUseCase.execute(profile, new DisposableObserver<ServerResponse>() {


                @Override
                public void onNext(@NonNull ServerResponse response) {

                    status = response.getStatus();

                    // EntryActivity.setPreferences("Token",token);


                    Log.e("Shared Token:", EntryActivity.preferences.getString("Token", null));
                    //intent.putExtra("Token",token);
                    //intent.putExtra("Login", login);
                    activity.startActivity(intent);


                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.e("Logging-in Error", e.toString());
                }

                @Override
                public void onComplete() {
                    logProfileUseCase.dispose();
                }
            });
        } catch (Exception e) {
            Log.e("Error", e.toString());

        }

        activity.startActivity(intent); //временно для тестов
    }


}
