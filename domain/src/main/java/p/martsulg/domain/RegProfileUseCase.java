package p.martsulg.domain;


import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.Profile;
import p.martsulg.data.models.ServerResponse;

public class RegProfileUseCase extends UseCase<Profile,ServerResponse> {




    @Override
    protected Observable<ServerResponse> buildUseCase(Profile param) {

        Profile profile = new Profile();

        profile.setEmail(param.getEmail());
        profile.setPassword(param.getPassword());
        profile.setName(param.getName());
        profile.setAvatar(param.getAvatar());
        return RestService.getInstance().regUser(profile);

    }
}
