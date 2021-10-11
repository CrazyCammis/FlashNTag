package com.example.flashntag.modeller;

public class Picture {
    private String fileName;
    private String[] tags;
    private  boolean favorited;
    private int pictureID;


    public Picture(String fileName, String[] tags, boolean favorited, int pictureID) {
        this.fileName = fileName;
        this.tags = tags;
        this.favorited = favorited;
        this.pictureID = pictureID;
    }

    public int getPictureID() {
        return pictureID;
    }

    public void setPictureID(int pictureID) {
        this.pictureID = pictureID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }
}
