package com.example.flashntag.modeller;

import com.example.flashntag.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Picture {

    private String uID;
    private String fileURL;
    private String[] tags ;
    private Date date;
    private int pictureID;

    private static ArrayList<String> allTags = new ArrayList<String>();
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Picture(){


    }

    //ImageID, Type, Descrition
    public Picture(int pictureID,  Date date, String[] tags, String fileURL ) {
        this.tags = tags;
        this.date = date;
        this.pictureID = pictureID;
        this.fileURL = fileURL;
    }
//TODO, finne ut hvordan vi skal lagre data og så lage get data fra dette






    //Generere data basert på databasen, ikke ferdig for ikke enig om database

    public static List<Picture> getData(String type, String targetTag) {



        String[] tagged ={};
        String[] tagged1 =  new String[]{"#ani", "#aniiken skywalker", "#joe", "#jreedom", "#throwback", "ez", "a"};
        String[] tagged2 =  new String[]{"danni", "yolo", "#sadLife", "#Freedom", "#ThrowBack", "a", "holder1", "jobb", "sjokolade",
        "ferie", "lang", "who cares", "wohooo"};
        ArrayList<Picture> dataList = new ArrayList<>();

        int[] images = getImages();

        for(int i = 0; i < images.length; i ++){
            //generates different tags for every second one
            if(i%2 == 0){
                tagged = tagged2;
            }
            else{
                tagged= tagged1;
            }
            Date d1 = new Date();
            Picture picture = new Picture(images[i], d1,  tagged, "");
            addtagListToallTags(tagged);
            dataList.add(picture);
        }

        ArrayList<Picture> holder = new ArrayList<>();

      // switch(type){

            //case "tag":
           //check for tags and add it to a holding list
           if(type.equals("tag")){
               for (Picture pictures : dataList) {
                   String[] tags = pictures.getTags();
                   for (int i = 0; i < tags.length; i++) {
                       if (tags[i].equals(targetTag)) {
                           holder.add(pictures);
                       }
                   }

               }
               dataList = holder;
           }
            //break;

            //favorited should be a reserved tag
            //case "favorite" :
                              //check for tags and add it to a holding list
         else if(type.equals("favorited")) {
            for (Picture pictures : dataList) {
                String[] tags = pictures.getTags();
                for (int i = 0; i < tags.length; i++) {
                    if (tags[i].equals("favorited")) {
                        holder.add(pictures);
                    }
                }
            }
            dataList = holder;
        }
                //break;

            else if(type.equals("date")) {

                //case "date" :
                //check for tags and add it to a holding list
                for (Picture pictures : dataList) {
                    String date = pictures.getDate().toString();

                    if (date.equals(targetTag)) {
                        holder.add(pictures);
                    }
                }
                dataList = holder;
                //break;
            }
            //default:break;
       //}

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

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public  String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }


    private void addTag(Picture pic, String tag    ){

        String[] tags = pic.getTags();

        List valid = Arrays.asList(tags);

        if(valid.contains(tag)){
            return;
        }

        else{
            for(int i = 0; i < tags.length; i++){
                if(tags[i] == ""){
                    tags[i] = tag;

                    pic.setTags(tags);
                    return;

                }
            }
        }
    }




    private static void addTagToAllList(String tag) {
       if (!allTags.contains(tag)){
            allTags.add((tag));
            return;
      }
    }

    public static ArrayList<String> getAllTags(){
        getData("","");

        return  allTags;
    }

    private static void addtagListToallTags(String[] tags){


        for(int i = 0; i < tags.length; i++){
            String checker = tags[i];
            if(!checker.equals("") ||!(checker == "")){
                addTagToAllList(checker);
            }
        }

    }

}

