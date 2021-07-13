package com.image.search.domain.model;

/**
 * @author Paramanathan Ilandrayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
public class Provider {

    private String name;
    private String favIcon;
    private String favIconBase64Encoding;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavIcon() {
        return favIcon;
    }

    public void setFavIcon(String favIcon) {
        this.favIcon = favIcon;
    }

    public String getFavIconBase64Encoding() {
        return favIconBase64Encoding;
    }

    public void setFavIconBase64Encoding(String favIconBase64Encoding) {
        this.favIconBase64Encoding = favIconBase64Encoding;
    }
}
