package p.martsulg.data;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import p.martsulg.data.models.AnswersParams;
import p.martsulg.data.models.CommentParams;
import p.martsulg.data.models.ListAnswers;
import p.martsulg.data.models.ListComments;
import p.martsulg.data.models.Profile;
import p.martsulg.data.models.UserModel;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestService {

    private static final RestService instance = new RestService();

    private RestApi restApi;

    private RestService() {
        init();
    }

    public static RestService getInstance() {
        return instance;
    }

    private void init() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pusher.cpl.by/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient).build();

        restApi = retrofit.create(RestApi.class);
    }

    public Observable<ListComments> getComments(CommentParams params) {
        return restApi.getComments(params.getToken());
    }

    public Observable<Object> addComment(CommentParams params) {
        return restApi.addComment(params.getTitle(), params.getMessage(), params.getToken());
    }

    public Observable<Object> delComment(CommentParams params) {
        return restApi.delComment(params.getCommentId(), params.getToken());
    }

    public Observable<UserModel> logUser(Profile profile) {
        return restApi.logUser(profile.getEmail(), profile.getPassword());
    }

    public Observable<UserModel> regUser(Profile profile) {

        RequestBody email =
                RequestBody.create(MediaType.parse("multipart/form-data"), profile.getEmail());
        RequestBody password =
                RequestBody.create(MediaType.parse("multipart/form-data"), profile.getPassword());
        RequestBody name =
                RequestBody.create(MediaType.parse("multipart/form-data"), profile.getName());
        RequestBody avatar =
                RequestBody.create(MediaType.parse("multipart/form-data"), profile.getAvatar());

        return restApi.regUser(email, password, name, avatar);
    }

    public Observable<ListAnswers> getAnswers(AnswersParams params) {
        return restApi.getAnswers(params.getCommentId(), params.getToken());
    }

    public Observable<Object> delAnswer(AnswersParams params) {
        return restApi.delAnswer(params.getCommentId(), params.getAnswerId(), params.getToken());
    }

    public Observable<Object> addAnswer(AnswersParams params) {
        return restApi.addAnswer(params.getCommentId(), params.getMessage(), params.getToken());
    }
}
