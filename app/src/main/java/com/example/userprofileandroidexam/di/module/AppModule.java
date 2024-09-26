package com.example.userprofileandroidexam.di.module;

import static com.example.userprofileandroidexam.helper.Constants.API_WEB_HOST;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.userprofileandroidexam.data.local.AppDatabase;
import com.example.userprofileandroidexam.data.remote.ApiClientInterface;
import com.example.userprofileandroidexam.di.DatabaseInfo;
import com.example.userprofileandroidexam.helper.Constants;
import com.example.userprofileandroidexam.helper.Utils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    public static Context provideApplicationContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    public static ApiClientInterface provideApiClientInterface(Retrofit retrofit) {
        return retrofit.create(ApiClientInterface.class);
    }

    @Provides
    @Singleton
    public static Cache provideCache(Context context) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    public static OkHttpClient provideOkHttpClient(Interceptor interceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
    public static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(API_WEB_HOST)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public static AppDatabase providerAppDatabase(Context context, @DatabaseInfo String databaseName) {
        return Room.databaseBuilder(context, AppDatabase.class, databaseName)
                .allowMainThreadQueries().build();
    }

    @Provides
    @DatabaseInfo
    public static String provideDatabaseName() {
        return Constants.DATABASE_NAME;
    }

    @Provides
    @Singleton
    public static Interceptor provideInterceptor(Context context) {
        return chain -> {
            Request request = chain.request();
            if (!Utils.hasNetworkConnection(context)) {
                int maxStale = 60 * 60 * 24 * 28;
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale" + maxStale)
                        .build();
            } else {
                int maxAge = 5;
                request.newBuilder()
                        .header("Cache-Control", "public, max-age - " + maxAge)
                        .build();
            }
            return chain.proceed(request);
        };
    }

}
