package com.rechinx.pixiv_e.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.rechinx.pixiv_e.R;
import com.rechinx.pixiv_e.adapter.WorkAdapter;
import com.rechinx.pixiv_e.api.RankApi;
import com.rechinx.pixiv_e.model.WorksModel;
import com.rechinx.pixiv_e.support.SpacesItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class RankFragment extends Fragment {

    private int MODE = 0;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private WorkAdapter mAdapter;
    private List<WorksModel> mWorksList = new ArrayList<WorksModel>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RankFragment() {
    }

    @SuppressLint("ValidFragment")
    public RankFragment(int mode) {
        this.MODE = mode;
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RankFragment newInstance(int columnCount) {
        RankFragment fragment = new RankFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rank_list, container, false);

        // Set the adapter
        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.rank_recycler_view);
        mAdapter = new WorkAdapter(mWorksList, mListener, getActivity());
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        Refresh();
        recyclerView.setAdapter(mAdapter);
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Refresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setMode(int mode) {
        this.MODE = mode;
        Refresh();
    }

    private void Refresh() {
        switch (MODE) {
            case 0:new GetRankAllTask(getActivity()).execute("px_128x128,px_480mw,large", "true", "1", "px_170x170,px_50x50", "daily", "true", "50");break;
            case 1:new GetRankAllTask(getActivity()).execute("px_128x128,px_480mw,large", "true", "1", "px_170x170,px_50x50", "weekly", "true", "50");break;
            case 2:new GetRankAllTask(getActivity()).execute("px_128x128,px_480mw,large", "true", "1", "px_170x170,px_50x50", "monthly", "true", "50");break;
        }

    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class GetRankAllTask extends AsyncTask<String, Void, Void> {

        private  Context mContext;
        private List<WorksModel> worksModelList = new ArrayList<WorksModel>();
        public GetRankAllTask(Context context) {
            mContext = context;
        }

        @Override
        protected Void doInBackground(String... strings) {
            try {
                Gson gson = new Gson();
                String jsonData = RankApi.RankAll(strings[0], strings[1], strings[2], strings[3], strings[4], strings[5], strings[6], mContext);
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONArray jsonArray = jsonObject.optJSONArray("response");
                JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                JSONArray jsonArray1 = jsonObject1.optJSONArray("works");
                WorksModel worksModel;
                for(int i = 0; i < jsonArray1.length(); i++) {
                    JSONObject item = (JSONObject) jsonArray1.get(i);
                    worksModel = gson.fromJson(item.toString(), WorksModel.class);
                    worksModelList.add(worksModel);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mAdapter.updateData(worksModelList);
            mAdapter.notifyDataSetChanged();
        }
    }

}
