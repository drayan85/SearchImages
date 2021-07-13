package com.image.search.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.image.search.BuildConfig;
import com.image.search.UIThread;
import com.image.search.domain.database.AppDatabase;
import com.image.search.domain.exception.RxErrorHandlingCallAdapterFactory;
import com.image.search.domain.executor.JobExecutor;
import com.image.search.domain.executor.PostExecutionThread;
import com.image.search.domain.executor.ThreadExecutor;
import com.image.search.utils.ImageLoadingHelper;
import com.image.search.utils.JsonFormatHttLogging;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 *
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
@Module
public class ApplicationModule {

    private final String mBaseUrl;
    private final Context mContext;

    public ApplicationModule(final Context context) {
        this.mContext = context;
        mBaseUrl = BuildConfig.API_BASE_URL;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(final Context context) {
        return AppDatabase.getInstance(context);
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(final JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(final UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    Cache provideHttpCache(final Context context) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .create();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory(final Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new JsonFormatHttLogging())
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient1(final Cache cache,
                                      final HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor);
        }

        builder.followSslRedirects(true);
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

    @Provides
    @Singleton
    CallAdapter.Factory provideRxJavaCallAdapterFactory() {
        return RxErrorHandlingCallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideServerRetrofit(final OkHttpClient client,
                                   final GsonConverterFactory converterFactory,
                                   final CallAdapter.Factory adapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl + "/")
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    ImageLoadingHelper provideImageLoadingHelper(final Context context) {
        return new ImageLoadingHelper(context);
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }
}
