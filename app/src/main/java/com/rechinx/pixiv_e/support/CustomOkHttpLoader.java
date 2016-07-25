package com.rechinx.pixiv_e.support;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.file_descriptor.FileDescriptorFileLoader;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * Created by Chin on 2016/7/25.
 */
public class CustomOkHttpLoader implements ModelLoader<GlideUrl, InputStream> {

    private OkHttpClient okHttpClient;

    public CustomOkHttpLoader(OkHttpClient client) {
        this.okHttpClient = client;
    }

    @Override
    public DataFetcher<InputStream> getResourceFetcher(GlideUrl model, int width, int height) {
        return new OkHttpStreamFetcher(okHttpClient, model);
    }

    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {

        private static volatile OkHttpClient internalClient;
        private OkHttpClient client;


        private static OkHttpClient getInternalClient() {
            if(internalClient == null) {
                synchronized (Factory.class) {
                    if (internalClient == null) {
                        internalClient = new OkHttpClient();
                    }
                }
            }
            return internalClient;
        }

        public Factory() {
            this.getInternalClient();
        }

        public Factory(OkHttpClient okHttpClient) {
            this.client = okHttpClient;
        }

        @Override
        public ModelLoader<GlideUrl, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new CustomOkHttpLoader(client);
        }

        @Override
        public void teardown() {

        }
    }
}
