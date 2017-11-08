package com.oscar.nat_at.Data.Retrofit;

import com.oscar.nat_at.Data.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by oemy9 on 07/11/2017.
 */
public class GoogleRetrofitClient {

    protected APIGoogleService apiService;

    public GoogleRetrofitClient(){
        this.initRetrofit();
    }

    private void initRetrofit(){
        Retrofit retrofit=retrofitBuilder();
        apiService=retrofit.create(getService());
    }

    private Retrofit retrofitBuilder(){
        return new Retrofit.Builder()
                .baseUrl(Constants.URL_BASE)
                .client(getHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(2, TimeUnit.SECONDS);
        httpClient.readTimeout(2,TimeUnit.SECONDS);
        httpClient.hostnameVerifier(hostnameVerifierGoogle());
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original=chain.request();
                HttpUrl originalHttp=original.url();
                HttpUrl url=originalHttp.newBuilder()
                        .addQueryParameter("key", Constants.API_KEY)
                        .addEncodedQueryParameter("type",Constants.TYPE_SEARCH).build();
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);
                Request request=requestBuilder.build();
                return chain.proceed(request);
            }
        });
        return httpClient.build();

    }

    private Class<APIGoogleService>getService(){
        return APIGoogleService.class;
    }

    private  HostnameVerifier hostnameVerifierGoogle() {
        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                HostnameVerifier hv =
                        HttpsURLConnection.getDefaultHostnameVerifier();
                return hv.verify("maps.googleapis.com", session);
            }
        };

        return hostnameVerifier;

    }

}

