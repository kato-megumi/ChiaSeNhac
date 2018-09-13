package com.test.komachi.chiasenhac;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by komachi on 1/31/18.
 */

public class MusicSearch implements Serializable {
    public String album;
    public String genre;
    public String duration;
    public String name;
    public String artist;
    public String view;
    public String quality;
    public String cover;
    public String link;
    public MusicSearch( String artist, String view){
        this.artist=artist;
        this.view=view;
    }
    public MusicSearch(){

    };
    public static MusicSearch fromJson(JSONObject jsonObject) {
        MusicSearch b = new MusicSearch();
        try {
            // Deserialize json into object fields
            b.link=jsonObject.getString("link");
            b.album = jsonObject.getString("album");
            b.name = jsonObject.getString("name");
            b.view = jsonObject.getString("view");
            b.cover = jsonObject.getString("cover");
            b.quality = jsonObject.getString("quality");
            b.artist = jsonObject.getString("artist");
            b.genre = jsonObject.getString("genre");
            b.duration = jsonObject.getString("duration");
            }
         catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return b;
    }
    public static ArrayList<MusicSearch> fromJson(JSONArray jsonArray) {
        ArrayList<MusicSearch> musics = new ArrayList<MusicSearch>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject musicsJson = null;
            try {
                musicsJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            MusicSearch music = MusicSearch.fromJson(musicsJson);
            if (musics != null) {
                musics.add(music);
            }
        }

        return musics;
    }

}
