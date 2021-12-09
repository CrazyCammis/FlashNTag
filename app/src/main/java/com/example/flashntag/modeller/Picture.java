package com.example.flashntag.modeller;

import com.example.flashntag.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Picture {
    private String[] tags;
    private Date date;
    private int pictureID;

    private static ArrayList<String> allTags = new ArrayList<String>();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Picture(int pictureID,  Date date, String[] tags) {
        this.tags = tags;
        this.date = date;
        this.pictureID = pictureID;
    }


    public static List<Picture> getData(String type, String targetTag) {
        String[] tagged = {};
        String[] tagged1 = new String[]{"#ani", "#aniiken skywalker", "#joe", "#jreedom", "#throwback", "ez", "a"};
        String[] tagged2 = new String[]{"danni", "yolo", "#sadLife", "#Freedom", "#ThrowBack", "a", "holder1", "jobb", "sjokolade",
                "ferie", "lang", "who cares", "wohooo"};
        ArrayList<Picture> dataList = new ArrayList<>();

        int[] images = getImages();

        for (int i = 0; i < images.length; i++) {

            if (i % 2 == 0) {tagged = tagged2; }
            else {tagged = tagged1;}

            Date d1 = new Date();
            Picture picture = new Picture(images[i], d1, tagged);
            addtagListToallTags(tagged);
            dataList.add(picture);
        }

        ArrayList<Picture> holder = new ArrayList<>();

        switch (type) {
            case "tag":
                for (Picture pictures : dataList) {
                    String[] tags = pictures.getTags();
                    for (String tag : tags) {
                        if (tag.equals(targetTag)) {
                            holder.add(pictures);
                        }
                    }

                }
                dataList = holder;
                break;

            case "favorited":
                for (Picture pictures : dataList) {
                    String[] tags = pictures.getTags();
                    for (String tag : tags) {
                        if (tag.equals("favorited")) {
                            holder.add(pictures);
                        }
                    }
                }
                dataList = holder;
                break;

            case "date":


                for (Picture pictures : dataList) {
                    String date = pictures.getDate().toString();

                    if (date.equals(targetTag)) {
                        holder.add(pictures);
                    }
                }
                dataList = holder;

                break;

            default:
                break;
        }
        return dataList;
    }

    private static int[] getImages() {
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

      public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }


    private void addTag(Picture pic, String tag) {
        String[] tags = pic.getTags();
        List valid = Arrays.asList(tags);

        if (!valid.contains(tag)) {
            for (int i = 0; i < tags.length; i++) {
                if (tags[i].equals("")) {
                    tags[i] = tag;

                    pic.setTags(tags);
                    return;
                }
            }
        }
    }

    private static void addTagToAllList(String tag) {
        if (!allTags.contains(tag)) {
            allTags.add((tag));
        }
    }

    public static ArrayList<String> getAllTags() {
        getData("", "");

        return allTags;
    }

    private static void addtagListToallTags(String[] tags) {
        for (String checker : tags) {
            if (!checker.equals("")) {
                addTagToAllList(checker);
            }
        }
    }
}

