package com.example.flashntag.modeller;

import java.util.ArrayList;
import java.util.List;

public class Picture {
    private String fileName;
    private String[] tags;

    private int pictureID;


    public Picture(int pictureID,String fileName, String[] tags ) {
        this.fileName = fileName;
        this.tags = tags;

        this.pictureID = pictureID;
    }
//TODO, finne ut hvordan vi skal lagre data og så lage get data fra dette

    //Generere data basert på databasen, ikke ferdig for ikke enig om database
    public static List<Picture> getData() {
        //GOING TO BE EDITED LATER
        int databaseLength = 0;
        String[] HOLDER = {"Ani", "Sam", " Joe"};

        ArrayList<Picture> dataList = new ArrayList<>();

        for(int i = 0; i < databaseLength; i++){
            Picture picture = new Picture(i,"HOLDER",HOLDER);
            dataList.add(picture);
        }


        return  dataList;
    }

    public int getPictureID() {
        return pictureID;
    }

    public void setPictureID(String pictureID) {
        this.pictureID = pictureID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public  String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    


}

