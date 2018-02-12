package p.martsulg.data;


import io.reactivex.Observable;
import okhttp3.MultipartBody;
import p.martsulg.data.models.ListAnswers;
import p.martsulg.data.models.ListComments;
import p.martsulg.data.models.UserModel;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface RestApi {

    @Headers("Accept: application/json")
    @POST("api/v1/auth/login")
    Observable<UserModel> logUser(@Query("email") String email, @Query("password") String password);

    @Headers("Accept: application/json")
    @POST("api/v1/auth/register")
    Observable<UserModel> regUser(@Query("email") String email, @Query("password") String password,
                                  @Query("name") String name, @Body MultipartBody.Part part);

    @Headers("Accept: application/json")
    @GET("api/v1/comment")
    Observable<ListComments> getComments(@Query("api_token") String token);

    @Headers("Accept: application/json")
    @POST("api/v1/comment")
    Observable<Object> addComment(@Query("title") String title,
                                  @Query("message") String message, @Query("api_token") String token);

    @Headers("Accept: application/json")
    @DELETE("api/v1/comment/{comment}")
    Observable<Object> delComment(@Path("comment") int comment, @Query("api_token") String token);


    @Headers("Accept: application/json")
    @GET("api/v1/comment/{comment}/answer")
    Observable<ListAnswers> getAnswers(@Path("comment") int comment, @Query("api_token") String token);

    @Headers("Accept: application/json")
    @POST("api/v1/comment/{comment}/answer")
    Observable<Object> addAnswer(@Path("comment") int comment, @Query("message") String message, @Query("api_token") String token);


    @Headers("Accept: application/json")
    @DELETE("api/v1/comment/{comment}/answer/{answer}")
    Observable<Object> delAnswer(@Path("comment") int comment, @Path("answer") int answer, @Query("api_token") String token);


}