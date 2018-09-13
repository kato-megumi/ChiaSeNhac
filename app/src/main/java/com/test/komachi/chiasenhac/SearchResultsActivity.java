package com.test.komachi.chiasenhac;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class SearchResultsActivity extends Activity {

    RecyclerView recyclerView;
    ArrayList<MusicSearch> musics;
    AdapterMusicSearch adapterMusic;
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        textView = findViewById(R.id.tvSearch);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        String query = intent.getStringExtra(SearchManager.QUERY);
        textView.setText("Search result for "+query);
        recyclerView = findViewById(R.id.recycle_view);
        musics = new ArrayList<MusicSearch>();
        adapterMusic = new AdapterMusicSearch(this, musics);
        recyclerView.setAdapter(adapterMusic);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                playMusic(musics.get(position));
            }
        });
        fetchMusics(query);
    }
    public void playMusic(MusicSearch ms){
        Intent p = new Intent(this, ListenActivity.class);
        p.putExtra("music", new Music(ms.name,ms.artist,ms.link,ms.cover));
        startActivity(p);
    }
    private void fetchMusics(String query) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        requestParams.add("s", query);
        client.get("http://khanhnv.xyz/Son/search.php", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                ArrayList<MusicSearch> temp = MusicSearch.fromJson(response);
                musics.clear();
                musics.addAll(temp);
                adapterMusic.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                super.onFailure(statusCode, headers, throwable, response);
            }
        });

    }
}