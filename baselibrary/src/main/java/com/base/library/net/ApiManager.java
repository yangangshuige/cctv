package com.base.library.net;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by yg on 2018/12/11.
 */
public class ApiManager {
    private HashMap<Class, Retrofit> mRetrofitServiceHashMap = new HashMap<>();

    private Retrofit mApiRetrofit;
    private ConcurrentHashMap<Class, Object> cachedApis = new ConcurrentHashMap<>();
    private static ApiManager apiManager;
    public static ApiManager getInstence() {
        if (apiManager == null) {
            apiManager = new ApiManager();
        }
        return apiManager;
    }

    public ApiManager() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // init cache
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(55, TimeUnit.SECONDS)
                .addInterceptor(new CustomInterceptor())
                .addInterceptor(logInterceptor)
//                .addInterceptor(RetrofitConfig.sQueryParameterInterceptor)
                .addInterceptor(RetrofitConfig.sLoggingInterceptor)
//                .cookieJar(cookieJar)
                .build();

        mApiRetrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL_NEW)
                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(RetrofitConfig.converter)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mRetrofitServiceHashMap.put(TestService.class, mApiRetrofit);
    }

    public <T> void addService(Class<T> clz) {
        mRetrofitServiceHashMap.put(clz, mApiRetrofit);
    }

    public <T> T getService(Class<T> clz) {
        Object obj = cachedApis.get(clz);
        if (obj != null) {
            return (T) obj;
        } else {
            Retrofit retrofit = mRetrofitServiceHashMap.get(clz);
            if (retrofit != null) {
                T service = retrofit.create(clz);
                cachedApis.put(clz, service);
                return service;
            } else {
                return null;
            }
        }
    }

}
