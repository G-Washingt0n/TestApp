package p.martsulg.domain;


import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.Profile;
import p.martsulg.data.models.UserModel;

public class LogProfileUseCase extends UseCase<Profile,UserModel> {




    @Override
    protected Observable<UserModel> buildUseCase(Profile param) {

        return RestService.getInstance().logUser(param);


    }
}
