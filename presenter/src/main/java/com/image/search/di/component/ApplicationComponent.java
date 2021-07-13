package com.image.search.di.component;

import android.content.Context;

import com.google.gson.Gson;
import com.image.search.data.database.AppDatabase;
import com.image.search.di.module.ApplicationModule;
import com.image.search.domain.executor.PostExecutionThread;
import com.image.search.domain.executor.ThreadExecutor;
import com.image.search.utils.ImageLoadingHelper;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * A component whose lifetime is the life of the application.
 *
 * @author Pramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Retrofit exposeRetrofit();

    AppDatabase exposeAppDatabase();

    Gson exposeGson();

    ImageLoadingHelper exposeImageLoadingHelper();

    ThreadExecutor exposeThreadExecutor();

    PostExecutionThread exposePostExecutionThread();

    Context exposeContext();
}
