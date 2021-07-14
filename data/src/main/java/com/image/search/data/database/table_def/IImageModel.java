package com.image.search.data.database.table_def;

import android.provider.BaseColumns;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
public interface IImageModel extends IBaseTable {

    String TABLE_NAME = "tbl_image";

    interface Columns extends BaseColumns {
        String URL = "url";
        String HEIGHT = "height";
        String WIDTH = "width";
        String THUMBNAIL = "thumbnail";
        String THUMBNAIL_HEIGHT = "thumbnail_height";
        String THUMBNAIL_WIDTH = "thumbnail_width";
        String NAME = "name";
        String TITLE = "title";
        String PROVIDER = "provider";
        String IMAGE_WEB_SEARCH_URL = "image_web_search_url";
        String WEB_PAGE_URL = "web_page_url";
    }

    String CREATE_TABLE_QUERY = CREATE_TABLE_IF_NOT_EXISTS
            + TABLE_NAME + " ("
            + Columns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + Columns.URL + " TEXT NOT NULL, "
            + Columns.HEIGHT + " INTEGER NOT NULL DEFAULT 0, "
            + Columns.WIDTH + " INTEGER NOT NULL DEFAULT 0, "
            + Columns.THUMBNAIL + " TEXT, "
            + Columns.THUMBNAIL_HEIGHT + " INTEGER NOT NULL DEFAULT 0, "
            + Columns.THUMBNAIL_WIDTH + " INTEGER NOT NULL DEFAULT 0, "
            + Columns.NAME + " TEXT , "
            + Columns.TITLE + " TEXT, "
            + Columns.PROVIDER + " TEXT, "
            + Columns.IMAGE_WEB_SEARCH_URL + " TEXT, "
            + Columns.WEB_PAGE_URL + " TEXT )";

    String CREATE_TABLE_INDEX_URL = CREATE_UNIQUE_INDEX_IF_NOT_EXISTS + "index_" + TABLE_NAME + "_" + Columns.URL
            + " ON " + TABLE_NAME + "(" + Columns.URL + ")";
}
