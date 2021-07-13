package com.image.search.data.source.search_image;

import com.image.search.domain.model.ImageModel;
import com.image.search.domain.model.http.response.ImageSearchResponse;

import io.reactivex.Observable;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
public interface SearchImageDataSource {

    Observable<ImageSearchResponse> getSearchImagesBasedOnQuery(String query, int page, int perSize);

    void saveImageModels(ImageModel[] imageModels);
}
