package com.test.komachi.chiasenhac;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TopFragment extends Fragment {
RecyclerView recyclerView;
AdapterMusic adapterMusic;
ArrayList<Music> musics;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        recyclerView = view.findViewById(R.id.recycle_view);
        musics = new ArrayList<Music>();
        adapterMusic = new AdapterMusic(getContext(), musics);
        recyclerView.setAdapter(adapterMusic);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent p = new Intent(getContext(),ListenActivity.class);
                p.putExtra("music",musics.get(position));
                startActivity(p);
            }
        });
        fetchMusics();
        return view;
    }
    private void fetchMusics() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://khanhnv.xyz/Son/home.php",null,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                ArrayList<Music> temp = Music.fromJson(response);
                musics.clear();
                musics.addAll(temp);
                adapterMusic.notifyDataSetChanged();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,JSONObject response){
                super.onFailure(statusCode, headers, throwable, response);
            }
        });

    }
}