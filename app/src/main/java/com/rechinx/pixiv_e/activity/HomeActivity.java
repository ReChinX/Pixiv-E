package com.rechinx.pixiv_e.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rechinx.pixiv_e.R;
import com.rechinx.pixiv_e.adapter.RankPagerAdapter;
import com.rechinx.pixiv_e.api.BaseApi;
import com.rechinx.pixiv_e.api.UsersApi;
import com.rechinx.pixiv_e.fragment.RankFragment;
import com.rechinx.pixiv_e.model.ProfileImageUrlsModel;
import com.rechinx.pixiv_e.model.UserModel;
import com.rechinx.pixiv_e.support.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RankFragment.OnFragmentInteractionListener {

    private TabLayout mTab;
    private ViewPager mPager;
    private RankPagerAdapter mAdapter;
    private ImageView mUserImage;
    private TextView mUserName;
    private NavigationView mNavigationView;
    private View mNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_rank);
        setSupportActionBar(toolbar);

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNav = mNavigationView.getHeaderView(0);
        mUserImage = (ImageView) mNav.findViewById(R.id.user_image);
        mUserName = (TextView) mNav.findViewById(R.id.user_showed_name);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mTab = (TabLayout) findViewById(R.id.tab_title);
        mPager = (ViewPager) findViewById(R.id.viewpager_rank);
        mAdapter = new RankPagerAdapter(fragmentManager);
        mTab.setTabsFromPagerAdapter(mAdapter);
        mPager.setAdapter(mAdapter);
        mTab.setupWithViewPager(mPager);

        Log.e("aafsda", " "+Integer.valueOf(BaseApi.getId(this)).intValue());
        new GetUserImageAndNameTask().execute(Integer.valueOf(BaseApi.getId(this)).intValue());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_following:{
                startActivity(new Intent(this, FollowingActivity.class));
            }break;
            case R.id.nav_fav:{
                startActivity(new Intent(this, FavoriteActivity.class));
            }break;
            case R.id.nav_history:{

            }break;
            case R.id.nav_about:{

            }break;
            case R.id.nav_setting:{
                startActivity(new Intent(this, SettingsActivity.class));
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    class GetUserImageAndNameTask extends AsyncTask<Integer, Void, UserModel> {

        @Override
        protected void onPostExecute(UserModel userModel) {
            super.onPostExecute(userModel);
            Glide.with(HomeActivity.this).load(Utility.constructGlideUrl(userModel.getProfile_image_urls().getPx_170x170())).into(mUserImage);
            mUserName.setText(userModel.getName());
        }

        @Override
        protected UserModel doInBackground(Integer... integers) {

            UserModel userModel = new UserModel();
            try {
                String jsonData = UsersApi.users(integers[0], HomeActivity.this);
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONArray jsonArray = jsonObject.optJSONArray("response");
                JSONObject jsonObject1 = jsonArray.optJSONObject(0);

                String name = jsonObject1.optString("name");

                JSONObject profile = jsonObject1.optJSONObject("profile_image_urls");
                String url = profile.getString("px_170x170");

                userModel.setName(name);

                ProfileImageUrlsModel profileImageUrlsModel = new ProfileImageUrlsModel();
                profileImageUrlsModel.setPx_170x170(url);
                userModel.setProfile_image_urls(profileImageUrlsModel);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return userModel;
        }
    }
}
