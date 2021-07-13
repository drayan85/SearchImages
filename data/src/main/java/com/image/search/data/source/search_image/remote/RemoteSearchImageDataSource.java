package com.image.search.data.source.search_image.remote;

import com.image.search.data.BuildConfig;
import com.image.search.data.api.ApiServiceInterface;
import com.image.search.data.source.search_image.SearchImageDataSource;
import com.image.search.domain.model.ImageModel;
import com.image.search.domain.model.http.response.ImageSearchResponse;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
public class RemoteSearchImageDataSource implements SearchImageDataSource {

    private final ApiServiceInterface mApiServiceInterface;

    public RemoteSearchImageDataSource(final Retrofit retrofit) {
        this.mApiServiceInterface = retrofit.create(ApiServiceInterface.class);
    }

    @Override
    public Observable<ImageSearchResponse> getSearchImagesBasedOnQuery(String query, int page, int perSize) {
        return mApiServiceInterface.getSearchImagesBasedOnQuery(BuildConfig.RAPID_API_HOST,
                BuildConfig.API_KEY, query, page, perSize, true, false);
    }

    @Override
    public void saveImageModels(ImageModel[] imageModels) {
        //TODO nothing only for DiskDataSource
    }
}
