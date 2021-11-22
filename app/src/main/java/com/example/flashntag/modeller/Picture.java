package com.example.flashntag.modeller;

import com.example.flashntag.R;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Picture {
    private String fileName;
    private String[] tags ;
    private Date date;
    private int pictureID;


    private static ArrayList<String> allTags ;




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

    public static List<Picture> getData(String type, String targetTag) {


        String[] tagged =  new String[]{"Ani", "Sam", "Joe"};
        ArrayList<Picture> dataList = new ArrayList<>();

        int[] images = getImages();

        for(int i = 0; i < images.length; i ++){
            Date d1 = new Date();
            Picture picture = new Picture(images[i], d1,  tagged);
            dataList.add(picture);
        }

        ArrayList<Picture> holder = new ArrayList<>();

        switch(type){
            case "tag":
                //check for tags and add it to a holding list
                for (Picture pictures : dataList) {
                    String[] tags = pictures.getTags();
                    for(int i = 0; i < tags.length; i++){
                        if(tags[i].equals(targetTag)){
                            holder.add(pictures);
                        }
                    }

                }
                dataList = holder;

            break;
            //favorited should be a reserved tag
            case "favorite" :
                              //check for tags and add it to a holding list
                for (Picture pictures : dataList) {
                    String[] tags = pictures.getTags();
                    for(int i = 0; i < tags.length; i++){
                        if(tags[i].equals("favorited")){
                            holder.add(pictures);
                        }
                    }
                }
                dataList = holder;
                break;

            case "date" :
                //check for tags and add it to a holding list
                for (Picture pictures : dataList) {
                    String date = pictures.getDate().toString();

                        if(date.equals(targetTag)){
                            holder.add(pictures);
                    }
                }
                dataList = holder;

                break;
            default:break;
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


    private void addTag(Picture pika, String tag    ){

        String[] tags = pika.getTags();
        List valid = Arrays.asList(tags);

        if(valid.contains(tag)){
            return;
        }

        else{
            for(int i = 0; i < tags.length; i++){
                if(tags[i] == ""){
                    tags[i] = tag;

                    pika.setTags(tags);
                    return;

                }
            }
        }

    }





private static void addNewTagToTagList(String tag) {

    if (!allTags.contains(tag)){
        allTags.add((tag));
        return;
    }

}

public static ArrayList<String> getAllTags(){
    addNewTagToTagList("addNewTagToTagList");
    addNewTagToTagList("addNewTagToTsswagList");
    addNewTagToTagList("s");
    return  allTags;
}


}

