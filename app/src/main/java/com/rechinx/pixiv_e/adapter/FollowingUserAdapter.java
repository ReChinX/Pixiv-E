package com.rechinx.pixiv_e.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rechinx.pixiv_e.R;
import com.rechinx.pixiv_e.model.ImageUrlsModel;
import com.rechinx.pixiv_e.model.ProfileImageUrlsModel;
import com.rechinx.pixiv_e.model.UserModel;
import com.rechinx.pixiv_e.model.WorkModel;
import com.rechinx.pixiv_e.model.WorksModel;
import com.rechinx.pixiv_e.support.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chin on 2016/7/28.
 */
public class FollowingUserAdapter extends RecyclerView.Adapter<FollowingUserAdapter.ViewHolder> {

    private List<UserModel> mUsers = new ArrayList<UserModel>();
    private Context mContext;

    public FollowingUserAdapter(Context context) {
        mContext = context;
    }

    public FollowingUserAdapter(List<UserModel> userModelList, Context context) {
        mUsers = userModelList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_following_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserModel userModel = mUsers.get(position);
        ProfileImageUrlsModel profileImageUrlsModel = userModel.getProfile_image_urls();
        holder.mName.setText(userModel.getName());
        Glide.with(mContext).load(Utility.constructGlideUrl(profileImageUrlsModel.getPx_50x50())).into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void updateData(List<UserModel> userModelList) {
        mUsers = userModelList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImage;
        private TextView mName;

        public ViewHolder(View itemView) {
            super(itemView);
            mImage  = (ImageView) itemView.findViewById(R.id.img_user_following);
            mName = (TextView) itemView.findViewById(R.id.text_following_name);
        }
    }
}
