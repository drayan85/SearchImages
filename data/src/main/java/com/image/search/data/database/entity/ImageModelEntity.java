package com.image.search.domain.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.image.search.domain.database.table_def.IImageModel;

/**
 * @author Paramanathan Ilandrayan <theebankala@gmail.com>
 * @version 2.0.0
 * @since 12th of July 2021
 */
@Entity(tableName = IImageModel.TABLE_NAME)
public class ImageModelEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = IImageModel.Columns._ID)
    public int base_id;

    private String url;
    private String name;
    private String title;
    private String provider;
    private String image_url;
    private String image_height;
    private String image_width;
    private String thumbnail;
    private String thumbnail_height;
    private String thumbnail_width;


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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_height() {
        return image_height;
    }

    public void setImage_height(String image_height) {
        this.image_height = image_height;
    }

    public String getImage_width() {
        return image_width;
    }

    public void setImage_width(String image_width) {
        this.image_width = image_width;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail_height() {
        return thumbnail_height;
    }

    public void setThumbnail_height(String thumbnail_height) {
        this.thumbnail_height = thumbnail_height;
    }

    public String getThumbnail_width() {
        return thumbnail_width;
    }

    public void setThumbnail_width(String thumbnail_width) {
        this.thumbnail_width = thumbnail_width;
    }
}
