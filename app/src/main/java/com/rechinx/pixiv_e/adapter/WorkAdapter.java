package com.rechinx.pixiv_e.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rechinx.pixiv_e.R;
import com.rechinx.pixiv_e.activity.WorkDetailsActivity;
import com.rechinx.pixiv_e.fragment.RankFragment.OnFragmentInteractionListener;
import com.rechinx.pixiv_e.model.ImageUrlsModel;
import com.rechinx.pixiv_e.model.WorkModel;
import com.rechinx.pixiv_e.model.WorksModel;

import java.util.List;


public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.ViewHolder> {

    private List<WorksModel> mWorks;
    private OnFragmentInteractionListener mListener;
    private Context mContext;

    public WorkAdapter(List<WorksModel> items, OnFragmentInteractionListener listener, Context context) {
        mWorks = items;
        mListener = listener;
        mContext = context;
    }

    public WorkAdapter(List<WorksModel> items, OnFragmentInteractionListener listener) {
        mWorks = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_rank_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final WorkModel work = mWorks.get(position).getWork();
        ImageUrlsModel imageUrlsModel = work.image_urls;

        Glide.with(mContext).load(imageUrlsModel.px_128x128).into(holder.mImage);
        holder.mName.setText(work.getTitle());

        holder.mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mContext, WorkDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("illust_id", work.getId());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
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

        public View mView;
        public ImageView mImage;
        public TextView mName;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImage = (ImageView) view.findViewById(R.id.work_image);
            mName = (TextView) view.findViewById(R.id.author_name);
        }

    }


}
