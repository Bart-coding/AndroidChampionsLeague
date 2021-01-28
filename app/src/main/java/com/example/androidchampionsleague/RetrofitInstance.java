package com.example.androidchampionsleague;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    public static final String FOOTBALL_API_URL = "https://api.football-data.org/";

    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request request = chain.request().newBuilder()
                                    .addHeader("X-Auth-Token", "fc23b99dfbe1435bae465f0b137fa7ca")
                                    .addHeader("Cache-Control", "max-age=640000")
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .addInterceptor(interceptor)
                    .build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(FOOTBALL_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
