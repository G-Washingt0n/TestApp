package p.martsulg.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import p.martsulg.data.models.Profile;
import p.martsulg.data.models.ServerResponse;
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

        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pusher.cpl.by/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient).build();

        restApi = retrofit.create(RestApi.class);
    }

    public Observable<ServerResponse> logUser(Profile profile) {
        return restApi.logUser(profile.getEmail(),profile.getPassword());
    }

    public Observable<ServerResponse> regUser(Profile profile) {
        return restApi.regUser(profile.getEmail(),profile.getPassword(),profile.getName(),profile.getAvatar());
    }

}
