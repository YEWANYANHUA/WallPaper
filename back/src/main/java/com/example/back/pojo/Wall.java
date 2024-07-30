package com.example.back.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


public class Wall {

    private Integer WallpaperID;
    private String title;
    private String image_path;
    private String uploaded_at;

    public Wall() {
    }

    public Wall(Integer WallpaperID, String title, String image_path, String uploaded_at) {
        this.WallpaperID = WallpaperID;
        this.title = title;
        this.image_path = image_path;
        this.uploaded_at = uploaded_at;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getWallpaperID() {
        return WallpaperID;
    }

    /**
     * 设置
     * @param WallpaperID
     */
    public void setWallpaperID(Integer WallpaperID) {
        this.WallpaperID = WallpaperID;
    }

    /**
     * 获取
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取
     * @return image_path
     */
    public String getImage_path() {
        return image_path;
    }

    /**
     * 设置
     * @param image_path
     */
    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    /**
     * 获取
     * @return uploaded_at
     */
    public String getUploaded_at() {
        return uploaded_at;
    }

    /**
     * 设置
     * @param uploaded_at
     */
    public void setUploaded_at(String uploaded_at) {
        this.uploaded_at = uploaded_at;
    }

    public String toString() {
        return "Wall{id = " + WallpaperID + ", title = " + title + ", image_path = " + image_path + ", uploaded_at = " + uploaded_at + "}";
    }
}
