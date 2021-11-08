package com.example.flashntag.modeller;

import com.example.flashntag.R;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Picture {
    private String fileName;
    private String[] tags ;
    private Date date;
    private int pictureID;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    //ImageID, Type, Descrition
    public Picture(int pictureID, /*String fileName*/ Date date, String[] tags ) {
       // this.fileName = fileName;
        this.tags = tags;
        this.date = date;
        this.pictureID = pictureID;
    }
//TODO, finne ut hvordan vi skal lagre data og så lage get data fra dette

    //Generere data basert på databasen, ikke ferdig for ikke enig om database
    public static List<Picture> getData() {


        String[] tagged =  new String[]{"Ani", "Sam", "Joe"};
        ArrayList<Picture> dataList = new ArrayList<>();

        int[] images = getImages();

        for(int i = 0; i < images.length; i ++){
            Date d1 = new Date();
            Picture picture = new Picture(images[i], d1,  tagged);
            dataList.add(picture);
        }

        return  dataList;
    }

    private static  int[] getImages() {
        return new int[]{
                R.drawable.captain_america_civil_war,
                R.drawable.donnie_darko,
                R.drawable.iron_man_3,
                R.drawable.spirited_away,
                R.drawable.star_wars_the_force_awakens,
                R.drawable.the_hobbit,
                R.drawable.up,
                R.drawable.pulp_fiction,
                R.drawable.coco,
                R.drawable.deadpool,
                R.drawable.inside_out,
                R.drawable.into_the_wild,
                R.drawable.the_hateful_eight,
                R.drawable.the_intouchables,
                R.drawable.the_lion_king
        };

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

    public  String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }




}

