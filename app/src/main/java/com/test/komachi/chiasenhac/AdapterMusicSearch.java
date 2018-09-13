package com.test.komachi.chiasenhac;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterMusicSearch extends RecyclerView.Adapter<AdapterMusicSearch.ViewHolder> {
    private ArrayList<MusicSearch> mMusics;
    private Context mContext;

    public AdapterMusicSearch(Context context, ArrayList<MusicSearch> musics) {
        mMusics = musics;
        mContext = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTop;
        TextView mCount;
        TextView mQuality;
        TextView mName;
        TextView mArtist;
        TextView mAlbum;
        TextView mGenre;
        TextView mTime;
        ViewHolder(View itemView) {
            super(itemView);
            mTop = itemView.findViewById(R.id.stt);
            mCount = itemView.findViewById(R.id.count);
            mArtist = itemView.findViewById(R.id.artist);
            mQuality = itemView.findViewById(R.id.quality);
            mName = itemView.findViewById(R.id.name);
            mAlbum=itemView.findViewById(R.id.album);
            mGenre=itemView.findViewById(R.id.genre);
            //layout = itemView.findViewById(R.id.item_layout);
            mTime=itemView.findViewById(R.id.time);
        }
    }

    @Override
    public int getItemCount() {
        return mMusics.size();
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public AdapterMusicSearch.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View noteView = inflater.inflate(R.layout.recycler_search, parent, false);
        ViewHolder viewHolder = new ViewHolder(noteView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterMusicSearch.ViewHolder viewHolder, int position) {
        MusicSearch music = mMusics.get(position);
        //viewHolder.mImage.setImageURI();
        //Picasso.with(getContext()).load(music.avatar).into(viewHolder.mImage);
        viewHolder.mName.setText(music.name);
        viewHolder.mQuality.setText(music.quality);
        viewHolder.mCount.setText(music.view);
        viewHolder.mArtist.setText(music.artist);
        viewHolder.mTop.setText(Integer.toString(position+1));
        viewHolder.mAlbum.setText(music.album);
        viewHolder.mGenre.setText(music.genre);
        viewHolder.mTime.setText(music.duration);
    }
}
