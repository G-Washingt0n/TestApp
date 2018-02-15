package com.pgmail.martsulg.testapp;


import android.content.Intent;
import android.databinding.ObservableField;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.Profile;
import p.martsulg.data.models.UserModel;
import p.martsulg.domain.LogProfileUseCase;

public class LogInViewModel {

    public ObservableField<String> email2send = new ObservableField<>();
    public ObservableField<String> password2send = new ObservableField<>();

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

            logProfileUseCase.execute(profile, new DisposableObserver<UserModel>() {


                @Override
                public void onNext(@NonNull UserModel response) {
                    if(response.getToken()!=null)
                        EntryActivity.setPreferences("Token", response.getToken());
                    else
                        EntryActivity.setPreferences("Token", "lDZDhFKBrlz7ZQD5XXLl"); //на случай если сервер не выдаст токен
                    intent.putExtra("Token",EntryActivity.preferences.getString("Token", null));
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
    }

}
