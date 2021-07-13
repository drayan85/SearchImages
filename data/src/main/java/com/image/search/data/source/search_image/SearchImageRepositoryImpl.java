package com.image.search.data.source.search_image;

import com.image.search.data.source.Remote;
import com.image.search.data.source.Local;
import com.image.search.domain.model.http.response.ImageSearchResponse;
import com.image.search.domain.repository.SearchImageRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
public class SearchImageRepositoryImpl implements SearchImageRepository {

    private final SearchImageDataSource mDiskSearchImageDataSource;
    private final SearchImageDataSource mRemoteSearchImageDataSource;

    @Inject
    public SearchImageRepositoryImpl(@Local SearchImageDataSource diskSearchImageDataSource,
                                     @Remote SearchImageDataSource remoteSearchImageDataSource) {
        this.mDiskSearchImageDataSource = diskSearchImageDataSource;
        this.mRemoteSearchImageDataSource = remoteSearchImageDataSource;
    }

    @Override
    public Observable<ImageSearchResponse> getSearchImagesBasedOnQuery(String query, int page, int perSize, boolean isInternetAvailable) {
        if (isInternetAvailable) {
            return mRemoteSearchImageDataSource.getSearchImagesBasedOnQuery(query, page, perSize)
                    .doOnNext(imageSearchResponse -> mDiskSearchImageDataSource.saveImageModels(imageSearchResponse.getValue()));
        }
        return mDiskSearchImageDataSource.getSearchImagesBasedOnQuery(query, page, perSize);
    }
}
