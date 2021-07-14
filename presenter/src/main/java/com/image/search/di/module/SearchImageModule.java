package com.image.search.di.module;

import com.image.search.data.database.AppDatabase;
import com.image.search.data.source.Local;
import com.image.search.data.source.Remote;
import com.image.search.data.source.search_image.SearchImageDataSource;
import com.image.search.data.source.search_image.SearchImageRepositoryImpl;
import com.image.search.data.source.search_image.local.DiskSearchImageDataSource;
import com.image.search.data.source.search_image.remote.RemoteSearchImageDataSource;
import com.image.search.di.PerActivity;
import com.image.search.domain.executor.ThreadExecutor;
import com.image.search.domain.repository.SearchImageRepository;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
@Module
public final class SearchImageModule {

    @Local
    @PerActivity
    @Provides
    SearchImageDataSource provideDiskSearchImageDataSource(final AppDatabase appDatabase, final ThreadExecutor threadExecutor) {
        return new DiskSearchImageDataSource(appDatabase, threadExecutor);
    }

    @Remote
    @PerActivity
    @Provides
    SearchImageDataSource provideRemoteSearchImageDataSource(final Retrofit retrofit) {
        return new RemoteSearchImageDataSource(retrofit);
    }

    @PerActivity
    @Provides
    SearchImageRepository provideSearchImageRepository(final SearchImageRepositoryImpl searchImageRepository) {
        return searchImageRepository;
    }
}
