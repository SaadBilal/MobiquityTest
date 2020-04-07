package com.mobiquity.mobiquityproducts.restclient;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobiquity.mobiquityproducts.utils.Urls;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static Retrofit retrofit = null;
    private static String baseUrl = null;

    public static Retrofit getClient() {


        if (retrofit == null) {

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            //DataManager.getInstance().getUserLanguageLocale()
                            Request original = chain.request();
                            Request request = original.newBuilder()
                                    .header("Accept-Language","en")
                                    .header("Accept", "application/json")
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .readTimeout(200, TimeUnit.SECONDS)
                    .addInterceptor(getInterceptorSquare())
                    .build();


                baseUrl = Urls.AMAZON_BASE_URL;

            Gson gson = new GsonBuilder()
                    .setLenient().create();

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseUrl/*"http://192.168.1.107:8001/"*/)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    private static HttpLoggingInterceptor getInterceptorSquare() {
        HttpLoggingInterceptor interceptorSquare = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String s) {
                Log.d("ApiResponse", s);
            }
        });
        interceptorSquare.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptorSquare;
    }
}
