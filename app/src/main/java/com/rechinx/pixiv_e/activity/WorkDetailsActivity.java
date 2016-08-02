package com.rechinx.pixiv_e.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.rechinx.pixiv_e.R;
import com.rechinx.pixiv_e.api.WorksApi;
import com.rechinx.pixiv_e.listener.AppBarStateChangeListener;
import com.rechinx.pixiv_e.model.WorkModel;
import com.rechinx.pixiv_e.support.Utility;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.zip.Inflater;

public class WorkDetailsActivity extends AppCompatActivity {

    private enum CollapsingToolbarLayoutState{
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    private CollapsingToolbarLayoutState state;
    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ImageView mMediumImage;
    private TextView mName;
    private TextView mAuthorName;
    private TextView mDescription;
    private TextView mViewed;
    private TextView mLiked;
    private TextView mInformations;
    private ImageView mAuthorImage;
    private TagFlowLayout mTags;
    private int illust_id;
    private String work_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        illust_id = intent.getIntExtra("illust_id", 0);

        setContentView(R.layout.activity_work_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mMediumImage = (ImageView) findViewById(R.id.work_details_image);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mName = (TextView) findViewById(R.id.work_name);
        mAuthorName = (TextView) findViewById(R.id.work_author_name);
        mDescription = (TextView) findViewById(R.id.work_description);
        mViewed = (TextView) findViewById(R.id.work_viewed_count);
        mLiked = (TextView) findViewById(R.id.work_liked_count);
        mInformations = (TextView) findViewById(R.id.work_informations);
        mAuthorImage = (ImageView) findViewById(R.id.author_image);
        mTags = (TagFlowLayout) findViewById(R.id.taglayout);
        mCollapsingToolbarLayout.setTitle("");
        setTitle(null);

        new GetWorkDetailTask().execute(illust_id);
        
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;
                        //mCollapsingToolbarLayout.setTitle("EXPANDED");
                        mCollapsingToolbarLayout.setTitle("");
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        mCollapsingToolbarLayout.setTitle(work_name);
                        state = CollapsingToolbarLayoutState.COLLAPSED;
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if(state == CollapsingToolbarLayoutState.COLLAPSED){

                        }
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;
                    }
                }

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:onBackPressed();break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class GetWorkDetailTask extends AsyncTask<Integer, Void, WorkModel> {

        @Override
        protected WorkModel doInBackground(Integer... integers) {
            WorkModel workModel = new WorkModel();
            try {
                String jsonData = WorksApi.Works(integers[0], WorkDetailsActivity.this);
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONArray jsonArray = jsonObject.optJSONArray("response");
                JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                Gson gson = new Gson();
                workModel = gson.fromJson(jsonObject1.toString(), WorkModel.class);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return workModel;
        }

        @Override
        protected void onPostExecute(final WorkModel workModel) {
            super.onPostExecute(workModel);
            Glide.with(WorkDetailsActivity.this).load(Utility.constructGlideUrl(workModel.getImage_urls().getMedium())).into(mMediumImage);
            work_name = workModel.getTitle();
            mName.setText(workModel.getTitle());
            mAuthorName.setText(workModel.getUser().getName());
            Glide.with(WorkDetailsActivity.this).load(Utility.constructGlideUrl(workModel.getUser().getProfile_image_urls().getPx_50x50())).into(mAuthorImage);
            mDescription.setText(workModel.getCaption());
            mViewed.setText(workModel.getStats().getViews_count() + "");
            mLiked.setText(workModel.getStats().getFavorited_count().getPublic_int() + workModel.getStats().getFavorited_count().getPrivate_int() + "");
            mInformations.setText(workModel.getReuploaded_time());

            mTags.setAdapter(new TagAdapter<String>(workModel.getTags()) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView textView = (TextView) LayoutInflater.from(WorkDetailsActivity.this).inflate(R.layout.content_tags, mTags, false);
                    textView.setText(s);
                    return textView;
                }

            });

            mMediumImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(WorkDetailsActivity.this, PhotoViewDetailsActivity.class);
                    intent.setAction(Intent.ACTION_MAIN);
                    intent.putExtra("url", workModel.getImage_urls().getLarge());
                    intent.putExtra("title", workModel.getTitle());
                    startActivity(intent);
                }
            });
        }
    }
}
