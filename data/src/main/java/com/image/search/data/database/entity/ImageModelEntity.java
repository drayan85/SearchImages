package com.image.search.data.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.image.search.data.database.table_def.IImageModel;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
@Entity(tableName = IImageModel.TABLE_NAME,
        indices = @Index(value = IImageModel.Columns.URL, unique = true)
)
public class ImageModelEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = IImageModel.Columns._ID)
    public int base_id;

    private String url;
    private int height;
    private int width;
    private String thumbnail;
    private int thumbnailHeight;
    private int thumbnailWidth;
    private String name;
    private String title;
    private String provider;
    private String imageWebSearchUrl;
    private String webpageUrl;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getImageWebSearchUrl() {
        return imageWebSearchUrl;
    }

    public void setImageWebSearchUrl(String imageWebSearchUrl) {
        this.imageWebSearchUrl = imageWebSearchUrl;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getThumbnailHeight() {
        return thumbnailHeight;
    }

    public void setThumbnailHeight(int thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }

    public int getThumbnailWidth() {
        return thumbnailWidth;
    }

    public void setThumbnailWidth(int thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }

    public String getWebpageUrl() {
        return webpageUrl;
    }

    public void setWebpageUrl(String webpageUrl) {
        this.webpageUrl = webpageUrl;
    }
}
