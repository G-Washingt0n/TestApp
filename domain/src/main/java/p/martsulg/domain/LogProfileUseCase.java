package p.martsulg.domain;


import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import p.martsulg.data.RestService;
import p.martsulg.data.models.Profile;
import p.martsulg.data.models.ServerResponse;

public class LogProfileUseCase extends UseCase<Profile,ServerResponse> {




    @Override
    protected Observable<ServerResponse> buildUseCase(Profile param) {

        Profile profile = new Profile();

        profile.setEmail(param.getEmail());
        profile.setPassword(param.getPassword());

        return RestService.getInstance().logUser(profile);


    }
}
