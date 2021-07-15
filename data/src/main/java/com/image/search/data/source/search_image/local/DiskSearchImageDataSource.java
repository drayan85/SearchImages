package com.image.search.data.source.search_image.local;

import com.image.search.data.database.AppDatabase;
import com.image.search.data.database.entity.ImageModelEntity;
import com.image.search.data.database.entity.mapper.ImageModelEntityMapper;
import com.image.search.data.source.search_image.SearchImageDataSource;
import com.image.search.domain.executor.ThreadExecutor;
import com.image.search.domain.model.ImageModel;
import com.image.search.domain.model.http.response.ImageSearchResponse;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
public class DiskSearchImageDataSource implements SearchImageDataSource {

    private final AppDatabase mAppDatabase;
    private final ThreadExecutor mThreadExecutor;
    private final ImageModelEntityMapper mapper;

    public DiskSearchImageDataSource(final AppDatabase appDatabase,
                                     final ThreadExecutor threadExecutor) {
        this.mAppDatabase = appDatabase;
        this.mThreadExecutor = threadExecutor;
        mapper = new ImageModelEntityMapper();;
    }

    @Override
    public Observable<ImageSearchResponse> getSearchImagesBasedOnQuery(final String query, final int page, final int per_page) {
        int offset = (page - 1) * per_page;
        return mAppDatabase.searchImageDao().getPaginatedImagesBasedOnQuery(per_page, offset, query)
                .defaultIfEmpty(new ImageModelEntity[0])
                .toObservable()
                .map(imageModelEntities -> {
                    ImageSearchResponse imageSearchResponse = new ImageSearchResponse();
                    imageSearchResponse.set_type("_images");
                    imageSearchResponse.setValue(mapper.transForm(imageModelEntities));
                    imageSearchResponse.setTotalCount(mAppDatabase.searchImageDao().getTotalNumberOfItemsForGivenSearchQuery(query).blockingGet(0));
                    return imageSearchResponse;
                })
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(Schedulers.from(mThreadExecutor));
    }

    @Override
    public void saveImageModels(final ImageModel[] imageModels) {
        Completable.fromAction(() ->
                mAppDatabase.searchImageDao().insertImageModelEntities(mapper.transForm(imageModels)))
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .subscribe();
    }
}
