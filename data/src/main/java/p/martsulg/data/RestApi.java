package p.martsulg.data;



import io.reactivex.Observable;
import p.martsulg.data.models.Profile;
import p.martsulg.data.models.ServerResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;



public interface RestApi {

    @POST("api/v1/auth/login")
    Observable<ServerResponse> logUser(@Body Profile profile);

    @POST("api/v1/auth/register")
    Observable<ServerResponse> regUser(@Body Profile profile);

}
