package com.rechinx.pixiv_e.fragment;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.rechinx.pixiv_e.R;
import com.rechinx.pixiv_e.adapter.FollowingUserAdapter;
import com.rechinx.pixiv_e.adapter.SimpleWorkAdapter;
import com.rechinx.pixiv_e.api.MeFavoriteWorksApi;
import com.rechinx.pixiv_e.api.MeFollowingApi;
import com.rechinx.pixiv_e.model.UserModel;
import com.rechinx.pixiv_e.model.WorksModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chin on 2016/7/28.
 */
public class FollowingFragment extends Fragment{
    private int MODE;
    private RecyclerView mRecyclerView;
    private FollowingUserAdapter mAdapter;

    public FollowingFragment() {

    }

    @SuppressLint("ValidFragment")
    public FollowingFragment(int mode) {
        this.MODE = mode;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_following, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.list_following);
        mAdapter = new FollowingUserAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        refresh();
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

    private void refresh() {
        switch (MODE) {
            case 0:new GetFollowingUsersTask().execute("1", "30", "public");break;
            case 1:new GetFollowingUsersTask().execute("1", "30", "private");break;
        }
    }

    class GetFollowingUsersTask extends AsyncTask<String, Void, List<UserModel>> {

        @Override
        protected List<UserModel> doInBackground(String... strings) {

            List<UserModel> userModelList = new ArrayList<UserModel>();

            try {
                Gson gson = new Gson();
                String jsonData = MeFollowingApi.meFollowing(strings[0], strings[1], strings[2], getActivity());
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONArray jsonArray = jsonObject.optJSONArray("response");
                UserModel userModel = new UserModel();
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = (JSONObject) jsonArray.get(i);
                    userModel = gson.fromJson(item.toString(), UserModel.class);
                    userModelList.add(userModel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return userModelList;
        }

        @Override
        protected void onPostExecute(List<UserModel> userModelList) {
            super.onPostExecute(userModelList);
            mAdapter.updateData(userModelList);
            mAdapter.notifyDataSetChanged();
        }
    }
}
