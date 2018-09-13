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

public class AdapterMusic extends RecyclerView.Adapter<AdapterMusic.ViewHolder> {
    private ArrayList<Music> mMusics;
    private Context mContext;

    public AdapterMusic(Context context, ArrayList<Music> musics) {
        mMusics = musics;
        mContext = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTop;
        TextView mCount;
        TextView mQuality;
        TextView mName;
        TextView mArtist;
        ImageView mImage;

        ViewHolder(View itemView) {
            super(itemView);
            mTop = itemView.findViewById(R.id.top);
            mCount = itemView.findViewById(R.id.count);
            mArtist = itemView.findViewById(R.id.artist);
            mQuality = itemView.findViewById(R.id.quality);
            mName = itemView.findViewById(R.id.name);
            mImage = itemView.findViewById(R.id.avatar);
            //layout = itemView.findViewById(R.id.item_layout);
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
    public AdapterMusic.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View noteView = inflater.inflate(R.layout.recycler_top, parent, false);
        ViewHolder viewHolder = new ViewHolder(noteView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterMusic.ViewHolder viewHolder, int position) {
        Music music = mMusics.get(position);
        //viewHolder.mImage.setImageURI();
        Picasso.with(getContext()).load(music.avatar).into(viewHolder.mImage);
        viewHolder.mName.setText(music.name);
        viewHolder.mQuality.setText(music.quality);
        viewHolder.mCount.setText(music.view);
        viewHolder.mArtist.setText(music.artist);
        viewHolder.mTop.setText(music.rank);
    }
}
