package com.rechinx.pixiv_e.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rechinx.pixiv_e.R;
import com.rechinx.pixiv_e.activity.WorkDetailsActivity;
import com.rechinx.pixiv_e.model.ImageUrlsModel;
import com.rechinx.pixiv_e.model.WorkModel;
import com.rechinx.pixiv_e.model.WorksModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chin on 2016/7/28.
 */
public class SimpleWorkAdapter extends RecyclerView.Adapter<SimpleWorkAdapter.ViewHolder> {

    private List<WorksModel> mWorks = new ArrayList<WorksModel>();
    private Context mContext;

    public SimpleWorkAdapter(Context context) {
        mContext = context;
    }

    public SimpleWorkAdapter(List<WorksModel> worksModelList, Context context) {
        mWorks = worksModelList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_favorite_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final WorkModel work = mWorks.get(position).getWork();
        ImageUrlsModel imageUrlsModel = work.image_urls;
        Glide.with(mContext).load(imageUrlsModel.px_128x128).into(holder.mImage);

    }

    @Override
    public int getItemCount() {
        return mWorks.size();
    }

    public void updateData(List<WorksModel> worksModelList) {
        mWorks = worksModelList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImage;

        public ViewHolder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.img_fav);
        }
    }
}
