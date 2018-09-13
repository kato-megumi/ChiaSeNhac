package com.test.komachi.chiasenhac;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by komachi on 1/31/18.
 */

public class Music implements Serializable {
    public String rank;
    public String avatar;
    public String name;
    public String artist;
    public String view;
    public String quality;
    public String cover;
    public String link;
    public  Music(String rank,String artist, String view){
        this.rank=rank;
        this.artist=artist;
        this.view=view;
    }
    public Music(String name, String artist,String link,String cover){
        this.name=name;
        this.artist=artist;
        this.link=link;
        this.cover=cover;
    }
    public Music(){

    };
    public static Music fromJson(JSONObject jsonObject) {
        Music b = new Music();
        try {
            // Deserialize json into object fields
            b.link=jsonObject.getString("link");
            b.rank = jsonObject.getString("rank");
            b.avatar = jsonObject.getString("avatar");
            b.name = jsonObject.getString("name");
            b.view = jsonObject.getString("view");
            b.cover = jsonObject.getString("cover");
            b.quality = jsonObject.getString("quality");
            b.artist = jsonObject.getString("artist");
            }
         catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return b;
    }
    public static ArrayList<Music> fromJson(JSONArray jsonArray) {
        ArrayList<Music> musics = new ArrayList<Music>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject musicsJson = null;
            try {
                musicsJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Music music = Music.fromJson(musicsJson);
            if (musics != null) {
                musics.add(music);
            }
        }

        return musics;
    }

}
