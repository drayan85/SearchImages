package com.image.search.domain.database.table_def;

import android.provider.BaseColumns;

/**
 * @author Paramanathan Ilandrayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
public interface IImageModel extends IBaseTable {

    String TABLE_NAME = "tbl_image";

    interface Columns extends BaseColumns {
        String URL = "url";
        String NAME = "name";
        String TITLE = "title";
        String PROVIDER = "provider";
        String IMAGE_URL = "image_url";
        String IMAGE_HEIGHT = "image_height";
        String IMAGE_WIDTH = "image_width";
        String THUMBNAIL = "thumbnail";
        String THUMBNAIL_HEIGHT = "thumbnail_height";
        String THUMBNAIL_WIDTH = "thumbnail_width";
    }

    String CREATE_TABLE_QUERY = CREATE_TABLE_IF_NOT_EXISTS
            + TABLE_NAME + " ("
            + Columns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + Columns.URL + " TEXT, "
            + Columns.NAME + " TEXT , "
            + Columns.TITLE + " TEXT, "
            + Columns.PROVIDER + " TEXT, "
            + Columns.IMAGE_URL + " TEXT, "
            + Columns.IMAGE_HEIGHT + " TEXT, "
            + Columns.IMAGE_WIDTH + " TEXT, "
            + Columns.THUMBNAIL + " TEXT, "
            + Columns.THUMBNAIL_HEIGHT + " TEXT, "
            + Columns.THUMBNAIL_WIDTH + " TEXT )";
}
