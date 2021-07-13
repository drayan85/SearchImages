package com.image.search.data.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomWarnings;

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

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM " + IImageModel.TABLE_NAME + " WHERE " + IImageModel.Columns.NAME + " = :query"
            + " LIMIT :page OFFSET :offSet")
    Maybe<ImageModelEntity[]> getPaginatedImagesBasedOnQuery(int page, int offSet, String query);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertImageModelEntities(ImageModelEntity... entities);
}
