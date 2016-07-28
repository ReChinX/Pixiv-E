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
import android.widget.ImageView;

import com.google.gson.Gson;
import com.rechinx.pixiv_e.R;
import com.rechinx.pixiv_e.adapter.FavoritePagerAdapter;
import com.rechinx.pixiv_e.adapter.SimpleWorkAdapter;
import com.rechinx.pixiv_e.api.MeFavoriteWorksApi;
import com.rechinx.pixiv_e.model.WorksModel;
import com.rechinx.pixiv_e.support.HttpHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chin on 2016/7/28.
 */
public class FavoriteFragment extends Fragment {

    private int MODE;
    private RecyclerView mRecyclerView;
    private SimpleWorkAdapter mAdapter;

    public FavoriteFragment() {

    }

    @SuppressLint("ValidFragment")
    public FavoriteFragment(int mode) {
        this.MODE = mode;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorite, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.list_fav);
        mAdapter = new SimpleWorkAdapter(getActivity());
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        refresh();
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

    private void refresh() {
        switch (MODE) {
            case 0:new GetFavoriteWorksTask().execute("1", "50", "public");break;
            case 1:new GetFavoriteWorksTask().execute("1", "50", "private");break;
        }
    }

    class GetFavoriteWorksTask extends AsyncTask<String, Void, List<WorksModel>> {

        @Override
        protected List<WorksModel> doInBackground(String... strings) {

            List<WorksModel> worksModelList = new ArrayList<WorksModel>();

            try {
                Gson gson = new Gson();
                String jsonData = MeFavoriteWorksApi.meFavoriteWorks(strings[0], strings[1], strings[2], getActivity());
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONArray jsonArray = jsonObject.optJSONArray("response");
                WorksModel worksModel;
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = (JSONObject) jsonArray.get(i);
                    worksModel = gson.fromJson(item.toString(), WorksModel.class);
                    worksModelList.add(worksModel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return worksModelList;
        }

        @Override
        protected void onPostExecute(List<WorksModel> worksModelList) {
            super.onPostExecute(worksModelList);
            mAdapter.updateData(worksModelList);
            mAdapter.notifyDataSetChanged();
        }
    }
}
