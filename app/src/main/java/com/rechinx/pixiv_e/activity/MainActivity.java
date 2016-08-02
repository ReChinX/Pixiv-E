package com.rechinx.pixiv_e.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rechinx.pixiv_e.R;
import com.rechinx.pixiv_e.api.BaseApi;
import com.rechinx.pixiv_e.support.SettingProvider;
import com.rechinx.pixiv_e.support.Utility;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText user_name;
    private EditText user_passwd;
    private Button login;
    private SettingProvider settingProvider;
    private String name, passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settingProvider = SettingProvider.getInstance(this);

        if(!needLogin(this)) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.setClass(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        user_name = (EditText) findViewById(R.id.user_name);
        user_passwd = (EditText) findViewById(R.id.user_passwd);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user_name.getText().length() < 1) {
                    Toast.makeText(
                            getApplicationContext(),
                            getString(R.string.toast_empty_username),
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }
                if (user_passwd.getText().length() < 1) {
                    Toast.makeText(
                            getApplicationContext(),
                            getString(R.string.toast_empty_password),
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }
                new LoginTask().execute(user_name.getText().toString(), user_passwd.getText().toString());
            }
        });
    }

    private boolean needLogin(Context context) {
        return BaseApi.getAccessToken(context) == null || Utility.isTokenExpired(BaseApi.getExpireDate(context));
    }

    private class LoginTask extends AsyncTask<String, Void, Boolean> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage(getResources().getString(R.string.plz_wait));
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            progressDialog.dismiss();
            if (BaseApi.getAccessToken(MainActivity.this) != null) {
                Log.d(TAG, BaseApi.getAccessToken(MainActivity.this));
                new AlertDialog.Builder(MainActivity.this)
                        .setCancelable(false)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_MAIN);
                                intent.setClass(MainActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .create()
                        .show();
            }

        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                BaseApi.login(strings[0], strings[1], MainActivity.this);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

    }

}
