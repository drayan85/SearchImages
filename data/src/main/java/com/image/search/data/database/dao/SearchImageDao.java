package com.image.search.data.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.image.search.data.database.entity.ImageModelEntity;
import com.image.search.data.database.table_def.IImageModel;

import io.reactivex.Maybe;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
@Dao
public interface SearchImageDao {

    @Query("SELECT * FROM " + IImageModel.TABLE_NAME + " WHERE " + IImageModel.Columns.TITLE + " LIKE '%' || :query || '%'"
            + " LIMIT :per_page OFFSET :offSet")
    Maybe<ImageModelEntity[]> getPaginatedImagesBasedOnQuery(int per_page, int offSet, String query);

    @Query("SELECT COUNT(*) FROM " + IImageModel.TABLE_NAME + " WHERE " + IImageModel.Columns.TITLE + " LIKE '%' || :query || '%'")
    Maybe<Integer> getTotalNumberOfItemsForGivenSearchQuery(String query);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertImageModelEntities(ImageModelEntity... entities);
}
