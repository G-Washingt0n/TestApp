package p.martsulg.data;



import java.io.File;

import io.reactivex.Observable;
import p.martsulg.data.models.Profile;
import p.martsulg.data.models.ServerResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface RestApi {

    @POST("api/v1/auth/login")
    Observable<ServerResponse> logUser(@Query("email") String email,@Query("password") String password);

    @POST("api/v1/auth/register")
    Observable<ServerResponse> regUser(@Query("email") String email,@Query("password") String password,
                                       @Query("name") String name,@Query("avatar") File avatar);

}
