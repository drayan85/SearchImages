package com.image.search.data.api;

import com.image.search.domain.model.http.response.ImageSearchResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * This interface will describe service methods.
 *
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
public interface ApiServiceInterface {

    @GET("/api/Search/ImageSearchAPI")
    Observable<ImageSearchResponse> getSearchImagesBasedOnQuery(@Header("x-rapidapi-host") String rapidApiHost,
                                                                @Header("x-rapidapi-key") String rapidApiKey,
                                                                @Query("q") String query,
                                                                @Query("pageNumber") int page,
                                                                @Query("pageSize") int perSize,
                                                                @Query("autoCorrect") boolean autoCorrect,
                                                                @Query("safeSearch") boolean safeSearch);
}
