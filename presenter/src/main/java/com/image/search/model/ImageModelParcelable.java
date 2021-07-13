package com.image.search.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.image.search.domain.model.Provider;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
public class ImageModelParcelable implements Parcelable {

    private String url;
    private int height;
    private int width;
    private String thumbnail;
    private int thumbnailHeight;
    private int thumbnailWidth;
    private String name;
    private String title;
    private Provider provider;
    private String imageWebSearchUrl;
    private String webpageUrl;

    public ImageModelParcelable() {
    }

    protected ImageModelParcelable(Parcel in) {
        url = in.readString();
        height = in.readInt();
        width = in.readInt();
        thumbnail = in.readString();
        thumbnailHeight = in.readInt();
        thumbnailWidth = in.readInt();
        name = in.readString();
        title = in.readString();
        imageWebSearchUrl = in.readString();
        webpageUrl = in.readString();
    }

    public static final Creator<ImageModelParcelable> CREATOR = new Creator<ImageModelParcelable>() {
        @Override
        public ImageModelParcelable createFromParcel(Parcel in) {
            return new ImageModelParcelable(in);
        }

        @Override
        public ImageModelParcelable[] newArray(int size) {
            return new ImageModelParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeInt(height);
        dest.writeInt(width);
        dest.writeString(thumbnail);
        dest.writeInt(thumbnailHeight);
        dest.writeInt(thumbnailWidth);
        dest.writeString(name);
        dest.writeString(title);
        dest.writeString(imageWebSearchUrl);
        dest.writeString(webpageUrl);
    }




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

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
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
