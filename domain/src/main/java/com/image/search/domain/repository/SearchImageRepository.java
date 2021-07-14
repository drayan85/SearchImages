package com.image.search.domain.repository;

import com.image.search.domain.model.http.response.ImageSearchResponse;

import io.reactivex.Observable;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
public interface SearchImageRepository {

    Observable<ImageSearchResponse> getSearchImagesBasedOnQuery(String query, int page, int perSize, boolean isInternetAvailable);
}
