package com.example.arjun_mu.a1retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arjun_mu on 5/3/2017.
 */

public class ServiceGeneratorWithLoggiing {


    private static final String BASE_URL = "https://api.github.com";

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass) {

        if (!httpClient.interceptors().contains(logging)) {

            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();

        }

        return retrofit.create(serviceClass);
    }
}
